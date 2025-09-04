/**
 * Program Name: JavaFX_My_Second_Show.java
 * Purpose: our first example of a JavaFX application, similar to our first Swing
 *          app called MyFirstFrame.java
 *          REVISION: just added a Label component to the rootNode object
 * Coder: Bill Pulling
 * Date: July 8, 2025
 */
//The Dave Clark Five
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;


public class JavaFX_My_Second_Show extends Application
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
		
		myStage.setTitle("JavaFX: Our very first show!");
		
		//often seen in JavaFX apps...create a ROOT NODE pane, which is like the parent
		// of all the other nodes in the app.
		FlowPane rootNode = new FlowPane();
		
		//make a Scene!
		Scene myScene = new Scene(rootNode, 500, 500);//sizes the scene
		
		//make a lable
		Label myLabel = new Label("Here is an example of a JavaFX Label object");
		
		//add this to the root node
		rootNode.getChildren().add(myLabel);
		
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