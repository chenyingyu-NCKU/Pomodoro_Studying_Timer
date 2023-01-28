package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainPanelController implements Initializable{
	
	
	public Stage stage;
	private Scene scene;
	public static int wh=0, wm=0, bh=0, bm=0;
	public static String myTask = null; //it must be a static !!!!!!!
	
	@FXML private ChoiceBox<Integer> hoursInput_work;
	@FXML private ChoiceBox<Integer> minutesInput_work;
	@FXML private ChoiceBox<Integer> hoursInput_break;
	@FXML private ChoiceBox<Integer> minutesInput_break;
	@FXML private TextField taskName;
	
	
	private Integer[] hour = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	private Integer[] minute = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
	
	
	//in the StartPanel
	public void beginButton(ActionEvent event) throws IOException{
		myTask = taskName.getText();
		
		//switch to MainPanel.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/application/MainPanel.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();	
	}
	
	//in the MainPanel
	public void startButton(ActionEvent event) throws IOException{

		
		//switch to Timer.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/application/Timer.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		
		System.out.println(wh+" "+wm+" "+bh+" "+bm);
		System.out.println(myTask);		
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		/* if you don't use try-catch, the choiceboxes will become null when the start button is pushed down. 
		 * that is to say you cannot use getitem.(illegal)
		 * DON'T ASK, I HAVE BEEN STUCKED FOR FIVE HOURS :)
		 * */
		
		try {
			hoursInput_work.getItems().addAll(hour);
			hoursInput_work.setOnAction(this::getTime);
		}catch(Exception e) {
			
		}try {
			minutesInput_work.getItems().addAll(minute);
			minutesInput_work.setOnAction(this::getTime);
		}catch(Exception e) {
			
		}try {
			hoursInput_break.getItems().addAll(hour);
			hoursInput_break.setOnAction(this::getTime);
		}catch(Exception e) {
			
		}try {
			minutesInput_break.getItems().addAll(minute);
			minutesInput_break.setOnAction(this::getTime);
		}catch(Exception e) {
			
		}
		
	}
	
	public void getTime(ActionEvent e) {
		try {
			wh = hoursInput_work.getValue();
			
		}catch(Exception e1) {
			
		}try {
			wm = minutesInput_work.getValue();
			
		}catch(Exception e1) {
			
		}try {
			
			bh = hoursInput_break.getValue();
		}catch(Exception e1) {
			
		}try {
			
			bm = minutesInput_break.getValue();
		}catch(Exception e1) {
			
		}
	}
}
