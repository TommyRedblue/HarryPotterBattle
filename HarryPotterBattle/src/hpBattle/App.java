package hpBattle;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import javafx.*;

public class App extends Application{

	public static void main(String[] args) {
		
		Hp_fileHandler fh = new Hp_fileHandler();
		
		String curse_filePath = ".\\files\\curses.in";
		String de_filePath = ".\\files\\death_eaters.in";
		String hog_filePath = ".\\files\\hogwarts.in";
		String mom_filePath = ".\\files\\ministry_of_magic.in";
		
		
		fh.curseReadWrite(curse_filePath);
		fh.wizReadWrite(de_filePath, hog_filePath, mom_filePath);
		
		launch(args);
		
	}

	public void start(Stage stageView) {
		
		// creating label for input and output files
		Text inputFileLabel = new Text("Input File: ");
		Text outputFileLabel = new Text("Output File: ");
		
		// creating controls for input file
		FileChooser inputFileChooser = new FileChooser();
		inputFileChooser.setTitle("Choose Input File");
		TextField inputTextField = new TextField();
		Button inputFileButton = new Button("Browse...");
		
		// creating controls for output file
		FileChooser outputFileChooser = new FileChooser();
		inputFileChooser.setTitle("Choose Output File");
		TextField outputTextField = new TextField();
		Button outputFileButton = new Button("Browse...");
		
		// creating text area to give output to user
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(4);
		
		// creating button to start battle and print results
		Button playButton = new Button("Play!");
		
		// set event handlers on buttons
		inputFileButton.setOnAction(event -> {
			File inputFile = inputFileChooser.showOpenDialog(stageView);
			if (inputFile != null) {
				String filePath = inputFile.getAbsolutePath();
				inputTextField.setText(filePath);
				textArea.setText("Opening: " + filePath.substring(filePath.lastIndexOf("\\")+1));
			}
		});
		
		outputFileButton.setOnAction(event -> {
			File outputFile = outputFileChooser.showOpenDialog(stageView);
			if (outputFile != null) {
				String filePath = outputFile.getAbsolutePath();
				outputTextField.setText(filePath);
				textArea.setText(textArea.getText() + "\nSaving: " + filePath.substring(filePath.lastIndexOf("\\")+1));
			}
		});
		
		playButton.setOnAction(event -> {
			
			if(!inputTextField.getText().equals("") && !outputTextField.getText().equals("")) {
			
				String battleFilePath = inputTextField.getText();
				
				String outputPath = outputTextField.getText();
				
				Battle battle = new Battle(battleFilePath, outputPath);
				
				battle.printTeamIntros();
				
				battle.startBattle();
				
				battle.printWinners();
				
				textArea.setText(textArea.getText() + "\nPlayed!");
			}
			
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Invalid paths");
				alert.setContentText("You must enter a path for the input file AND output file");
				alert.showAndWait();
			}
		});
		
		// create new grid layout
		GridPane gridPane = new GridPane();
		
		// add controls to gridpane
		gridPane.add(inputFileLabel, 0, 0);
		gridPane.add(outputFileLabel, 0, 1);
		gridPane.add(inputTextField, 1, 0);
		gridPane.add(inputFileButton, 2, 0);
		gridPane.add(outputTextField, 1, 1);
		gridPane.add(outputFileButton, 2, 1);
		gridPane.add(textArea, 0, 3, 2, 1);
		gridPane.add(playButton, 2, 3);
		
		// create space between columns and rows
		gridPane.setVgap(5);
		gridPane.setHgap(2);
		
		// create the scene object
		Scene scene = new Scene(gridPane);
		
		// set title at top of window
		stageView.setTitle("HarryPotterGUI");
		
		// add scene to stage
		stageView.setScene(scene);
		
		
		
		stageView.show();
	}
	
}
