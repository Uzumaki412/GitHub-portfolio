/**
 * Program Name: JavaFX_Show_Text_Example.java
 * Purpose: our first example of a JavaFX application, similar to our first Swing
 *          app called MyFirstFrame.java
 * Coder: Bill Pulling
 * Date: July 8, 2025
 */
//The Dave Clark Five
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.Insets;


public class JavaFX_Show_Text_Example extends Application
{
	//the gang of three methods...init() start() stop()
	
	public void init()//can do set up here for other things in the program
	{
		System.out.println("Inside init() method...");
	}
	
	//next method is the start(Stage s) method. We override this, as it is analogous
	// to our JFrame constructor. 
	public void start(Stage myStage)
	{
		System.out.println("Inside start, this is where we would assemble components...");
		
		myStage.setTitle("JavaFX: Showing some Text in a Text Field!");
		TextField txtFld = new TextField();
		Button showBtn = new Button ("Click to see the entered text in the console window.");
		
		//handle event using ANONYMOUS INNER CLASS
		showBtn.setOnAction(new EventHandler<ActionEvent>() 
				{
					public void handle(ActionEvent ev)
					{
						//get the entered text from the text field and print to console
						System.out.println("Text entered was: " + txtFld.getText() );
					}
				}//end inner class				
				);
				
		//often seen in JavaFX apps...create a ROOT NODE pane, which is like the parent
		// of all the other nodes in the app.
		//REVISION: change to a BorderPane
		BorderPane rootNode = new BorderPane();
		rootNode.setPadding(new Insets(70) );
		
		VBox rootNodeCenter = new VBox();
		rootNodeCenter.setSpacing(10);
		rootNode.setCenter(rootNodeCenter);
		
		rootNodeCenter.getChildren().add(txtFld);
		rootNodeCenter.getChildren().add(showBtn);
		
		//make a Scene!
		Scene myScene = new Scene(rootNode, 500, 200);//sizes the scene
		
		//put the scene object on the stage.
		myStage.setScene(myScene);
		
		//last line..RAISE THE CURTAIN!
		myStage.show();
		
	}//end start
	
	//override the stop() method if necessary
	public void stop()
	{
		System.out.println("Inside the stop() method...");
	}
	

	public static void main(String[] args)
	{
		// only line of code you need in the main in a JavaFX program is this...
		launch(args);
	}
	//end main
}
//end class