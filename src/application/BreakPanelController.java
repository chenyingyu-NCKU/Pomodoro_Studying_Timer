package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BreakPanelController {
	
	private Stage stage;
	private Scene scene;

	public void not_yet(ActionEvent event) throws IOException{
		
		//switch to Timer2.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/application/Timer2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();	
	}
	
	public void stop_finish_button(ActionEvent event) throws IOException{
		
		//switch to Conclusion.fxml
		Parent root = FXMLLoader.load(getClass().getResource("/application/Conclusion.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
		
		TimerController.total_work_time = TimerController.round_of_work*TimerController.total_second;
	}
}
