/**
 * Program Name: JavaFX_Listener_4_Lambda_Expression_1.java <br>
 * Purpose: second version that shows use of LAMBDA EXPRESSIONS to handle a button push event.
 *         REVISION: we put the code to be run into two separate methods and then just point
 *         the lambda expression to the needed method. If you had a button keypad with 10 or 12 
 *         buttons, this approach would just make the code a bit cleaner.  
 * Coder: Bill Pulling
 * Date: July 8, 2025
 * 
 */

//The Dave Clark Five
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;
//two more imports for listening
import javafx.event.*;
import javafx.geometry.Pos;


public class JavaFX_Listener_5_Lambda_Expression_2 extends Application
{
  //CLASS WIDE SCOPE AREA
	Button addBtn, subtractBtn;
	Label label;
	int counter;
	
	public void init()
	{
		//any initialization code,such as populating lists or combo boxes could go here.
		//initialize the counter variable here
		counter = 0;
		System.out.println("STUB: inside init() method, value of counter is: " + counter);
	}//end init
	
	//the start method is analogous to the constructor in a swing app.
	public void start(Stage myStage) throws Exception
	{
		//create some controls and manage the scene
		addBtn = new Button();
		addBtn.setText("Add");
		
		//REVISION HERE: use a LAMBDA EXPRESSION as the event handler but this time just
		//               point to the method that should be run.
		addBtn.setOnAction(e-> addOne()	);
		
	  //repeat for the second button	
		subtractBtn = new Button("Subtract");
		subtractBtn.setOnAction(e-> subtractOne() );		
		
		label = new Label();
		//convert the int value of counter to a String so that it can be displayed in the label
		label.setText(Integer.toString(counter));
		//quick and dirty way to display numerics in a label or text field is ""+
		
		//add a horizontal box pane as a container and make it the rootPane.
		HBox rootPane = new HBox(10);//the '10' specifies horizontal spacing between controls
		//set alignment property of the rootPane to CENTER
		rootPane.setAlignment(Pos.CENTER);
		
		//call the children in and add them to the pane
		rootPane.getChildren().addAll(label, addBtn, subtractBtn);
		
		//MAKE A SCENE object and pass it the rootPane, which has all the components on it
		Scene myScene = new Scene(rootPane, 450, 75);//width and height
		//put the Scene object on the Stage object
		myStage.setScene(myScene);
		myStage.setTitle("Using LAMBDA EXPRESSIONS VERSION 2 to handle Button Events");
		
		//RAISE THE CURTAIN!
		myStage.show();		
		
	}//end start
	
	//this method runs when the window is closed. This is the "janitor" method.
	public void stop()
	{
		//here you could put "clean up " code, such as closing streams, or Scanners, or DB links.
		System.out.println("STUB: Window has closed, inside the stop() method doing any clean-up...");
	}//end stop
	
	//REVISION HERE: put the operational code for the buttons here in two methods
	public void addOne()
	{
		counter++;
		label.setText(Integer.toString(counter));
	}//end addOne
	
	public void subtractOne()
	{
		counter--;
		label.setText(Integer.toString(counter));
	}//end subtractOne	
	
	public static void main(String[] args)
	{
    //just need one line here...Call the static method launch of the Application class.
		Application.launch(args);
	}	//end main
}//end class