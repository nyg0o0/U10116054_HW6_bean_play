/* 
********************** BeanGame java Assignment *********************
*	Strudent ID: U10116054											*
*	Strudent Name: Yu-Hsin Chen										*
*	Assign Date: 4/2												*
*	Content: This is a interface program for bean manchine.			*
*********************************************************************
*	--> BeanGame.java : Test class									*
*********************************************************************
*/


// import library
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import java.lang.Math;

// BeanGame class
public class BeanGame extends Application {
	public void start(Stage primaryStage) { 
	
		// stage size
		final double WIDTH = 400, HEIGHT = 400;
		double centerX = WIDTH / 2, centerY = HEIGHT /2;	
		
		// Create a new pane
		Pane pane = new Pane();
		
		/* Creat a new polyline */
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[]{	centerX- 0.05* WIDTH , centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.1* HEIGHT,
													centerX- 0.05* WIDTH , centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT,
													centerX- 0.35* WIDTH , centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT,
													centerX- 0.35* WIDTH , centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT + 0.15* HEIGHT,
													
													centerX+ 0.35* WIDTH , centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT + 0.15* HEIGHT,
													centerX+ 0.35* WIDTH , centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT,
													centerX+ 0.05* WIDTH , centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT,
													centerX+ 0.05* WIDTH , centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.1* HEIGHT
													});	// add the polyline with the coordinates
		pane.getChildren().add(polyline);	// add the polyline to the pane
		
		/* Creat a new line arrays */
		Line[] line = new Line[7];
		for(int i=0;i<7;i++){
			line[i] = new Line(	centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT ,
								centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT + 0.15* HEIGHT);	// add the line with the coordinates
			pane.getChildren().add(line[i]);	// add the line array to the pane
		}
		
		/* Creat a new circle arrays */
		Circle[] circle = new Circle[28];
			for(int j=7;j>0;j--){
				for(int i=0;i<j;i++){
					circle[i]=new Circle();
					circle[i].setRadius(5.0);	// set the radius
					circle[i].setLayoutX(centerX+ 0.0875* WIDTH*(i-3+0.5*(7-j)));	// set the circle's x coordinates
					circle[i].setLayoutY(centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.0875* WIDTH*(7-j)*0.85);	// set the circle's y coordinates
					pane.getChildren().add(circle[i]);// add the circle array to the pane
				}
			}

		/* Creat a scene and show it */
		Scene scene = new Scene(pane,WIDTH,HEIGHT);	// create a scene for the pane
		primaryStage.setTitle("Bean Machine Game interface");	// stage title
		primaryStage.setScene(scene);	// put the pane on the stage
		primaryStage.show();	// show it!
	}
}