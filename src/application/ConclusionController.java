package application;

import java.io.IOException;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConclusionController {

	int breakround = TimerController2.round_of_break;
	int break_ = TimerController2.total_second;

	
	
	String name = MainPanelController.myTask;
	
	@FXML private AnchorPane outcomepane;
	@FXML private AnchorPane buttonpane;
	@FXML Label myTaskName;
	@FXML Label total_time;
	@FXML Label actual_time;
	@FXML Label reward;
	@FXML ImageView myImageView;
	
	Image image;
	
	Stage stage;
	public void outcome_button(ActionEvent event) throws IOException{
		scrollUp();
		myTaskName.setText(name);
		
		long  actsecond = TimerController.total_work_time;
		long acthr = actsecond/3600;
		long actmin = (actsecond/60)%60;
		long actse = actsecond%60;
		String seconds_string = String.format("%02d", actse);
		String minutes_string = String.format("%02d", actmin);
		String hours_string = String.format("%02d", acthr);
		actual_time.setText(hours_string+":"+minutes_string+":"+seconds_string);
		
		long tsecond = actsecond + breakround*break_;
		long thr = tsecond/3600;
		long tmin = (tsecond/60)%60;
		long tse = tsecond%60;
		String s_string = String.format("%02d", tse);
		String min_string = String.format("%02d", tmin);
		String h_string = String.format("%02d", thr);
		total_time.setText(h_string+":"+min_string+":"+s_string);
		
		if(acthr>=5) {
			reward.setText("treat yourself a nice meal !");
			image = new Image(getClass().getResourceAsStream("/sources/meal.jpg"));
			myImageView.setImage(image);
		}else if (acthr >= 3) {
			reward.setText("You need a decent dessert.");
			image = new Image(getClass().getResourceAsStream("/sources/Dessert.jpg"));
			myImageView.setImage(image);

		}else if (acthr >= 1) {
			reward.setText("go to buy a cup of tapioca tea !");
			image = new Image(getClass().getResourceAsStream("/sources/Tapioca.jpg"));
			myImageView.setImage(image);

		}else if (acthr == 0) {
			reward.setText("a compliment from the bird :)");
			image = new Image(getClass().getResourceAsStream("/sources/You_good_good.jpg"));
			myImageView.setImage(image);

		}
		
		System.out.println(TimerController.total_second);
	}
	private void scrollUp() {
		TranslateTransition tr1 = new TranslateTransition(); 
		tr1.setDuration(Duration.millis(100));
		tr1.setToX(0);
		tr1.setToY(-700);
		tr1.setNode(buttonpane);
		TranslateTransition tr2 = new TranslateTransition(); 
		tr2.setDuration(Duration.millis(100));
		tr2.setFromX(0);
		tr2.setFromY(200);
		tr2.setToX(0);
		tr2.setToY(0);
		tr2.setNode(outcomepane);
		ParallelTransition pt = new ParallelTransition(tr1,tr2);
		pt.play();
	}
}
