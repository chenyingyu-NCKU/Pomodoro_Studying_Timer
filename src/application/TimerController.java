package application;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimerController {
	
	public static int round_of_work = 0;
	public static int currtime = 0;
	
	public static int total_work_time = 0;

	@FXML private Label hLabel;
	@FXML private Label mLabel;
	@FXML private Label sLabel;
	@FXML private AnchorPane timerPane;
	@FXML private ImageView myImageView;
	
	private Media media;
	private MediaPlayer mediaplayer;
	private File directory;
	private File[] files;
	private ArrayList<File> songs;
	
	Image image  = new Image(getClass().getResourceAsStream("/sources/studying.jpg"));
	
	int wmin = MainPanelController.wm;
	int whr = MainPanelController.wh;
	
	public static int total_second =  60*MainPanelController.wm + 3600*MainPanelController.wh;
	int elapseTime = total_second;  //second
	int seconds = 0;
	int minutes = wmin;
	int hours = whr;
	static boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	
	
	Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),e ->{
		elapseTime--;
		hours = elapseTime/3600;
		minutes = (elapseTime/60)%60;
		seconds = elapseTime%60;
		seconds_string = String.format("%02d", seconds);
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		hLabel.setText(hours_string);
		mLabel.setText(minutes_string);
		sLabel.setText(seconds_string);
		try {
			check();
		} catch (IOException e1) {}
		
	}));


	Stage stage;
	void check() throws IOException {
		if(elapseTime == 0) {
			timeline.stop();
			mediaplayer.stop();
			
			// "pop up" BreakPanel.fxml
			FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/application/BreakPanel.fxml"));
			Parent root = (Parent) fxmlloader.load();
			Stage stage2 = new Stage();
			Scene scene = new Scene(root);
			stage2.setScene(scene);
			stage2.centerOnScreen();
			stage2.show();
			
			//close Timer.fxml
			stage = (Stage) timerPane.getScene().getWindow();
			stage.close();
			
			round_of_work+=1;
			started = false;
		}
	}
	void watch() {
		hLabel.setText(hours_string);
		mLabel.setText(minutes_string);
		sLabel.setText(seconds_string);
		System.out.println(started);
		
	}
	
	
	void start() {
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	void stop() {
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.stop();
	}
	
	public void setMedia() {
		songs = new ArrayList<File>();
		directory = new File("music");
		files = directory.listFiles();
		
		if(files != null) {
			for(File file : files) {
				songs.add(file);
			}
		}
		
		media = new Media(songs.get(0).toURI().toString());
		mediaplayer = new MediaPlayer(media);
		
		mediaplayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				mediaplayer.seek(Duration.ZERO);
				
			}
			
		});
		
	}
	int mymedia = 0;
	public void stop_start_Button(ActionEvent event) throws IOException{
		myImageView.setImage(image);
		watch();
		if(mymedia==0) {
			setMedia();
			mymedia = 1;
		}
		
		
		if(started == false) {
			started = true;
			start();
			mediaplayer.play();

		}
		else {
			started = false;
			stop();
			mediaplayer.pause();
		}
	}
	public void finish_Button(ActionEvent event) throws IOException{
		stop();
		mediaplayer.stop();
		System.out.println(round_of_work);
		currtime = elapseTime;
		
		//switch to Conclusion.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/application/Conclusion.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		
		total_work_time = (total_second - currtime) + round_of_work*total_second;
	}
	
	boolean be_quiet = false;
	public void the_sound_button(ActionEvent event) {
		if(be_quiet == false) {
			mediaplayer.setVolume(0);
			be_quiet = true;
		}else {
			mediaplayer.setVolume(100);
			be_quiet = false;
		}
	}

	
}
