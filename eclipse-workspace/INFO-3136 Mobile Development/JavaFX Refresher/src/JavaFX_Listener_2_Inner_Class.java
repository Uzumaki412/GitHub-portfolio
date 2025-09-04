/**
 * Program Name: JavaFX_Listener_2_Inner_Class.java <br>
 * Purpose: shows using an INNER CLASS object as the listener for a button push event.
 * Coder: Bill Pulling
 * Date: July 8, 2025
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


public class JavaFX_Listener_2_Inner_Class extends Application
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
		//Create an object of our inner class and register it as a listener for this button
		ButtonListener nanny = new ButtonListener();
		addBtn.setOnAction(nanny); //in swing we use addActionListener()
		
		subtractBtn = new Button("Subtract");
		subtractBtn.setOnAction(nanny);
		
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
		myStage.setTitle("Using INNER CLASS Object as Listener for Button Events");
		
		//RAISE THE CURTAIN!
		myStage.show();		
		
	}//end start
	
	
	
	//this method runs when the window is closed. This is the "janitor" method.
	public void stop()
	{
		//here you could put "clean up " code, such as closing streams, or Scanners, or DB links.
		//reset the counter to zero here
		counter = 0;
		System.out.println("STUB: Window has closed, inside the stop() method doing any clean-up...");
	}//end stop
	
	//INNER CLASS GOES HERE
	private class ButtonListener implements EventHandler<ActionEvent>
	{
			@Override
		//here is our event handling method. In Swing, this would be the actionPerformed() method.
		public void handle(ActionEvent ev)
		{
			//find out which button was pushed
			if(ev.getSource() ==addBtn)
			{
				counter++;
			}
			else if(ev.getSource() == subtractBtn)
			{
				counter--;
			}
			//assign the value in the counter to the label
			label.setText(Integer.toString(counter));		
		}//end handle()
	}//end inner class	
	
	public static void main(String[] args)
	{
    //just need one line here...Call the static method launch of the Application class.
		Application.launch(args);
	}	//end main
}//end class