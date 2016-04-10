/* 
********************** BeanGame Plsy java Assignment ****************
*	Strudent ID: U10116054											*
*	Strudent Name: Yu-Hsin Chen										*
*	Assign Date: 4/10												*
*	Content: This is a program for bean manchine.					*
*********************************************************************
*	--> BeanGame.java : Test class									*
*********************************************************************
*/


// import library
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import java.lang.Math;
import javafx.animation.*;
import javafx.util.Duration;

// BeanGame class
public class BeanGame extends Application {
	public void start(Stage primaryStage) { 
		// numbers of balls, nodes, lines, ball's frame (const)
		final int BALL_NUMS = 10;
		final int NODE_NUMS = 28;
		final int LINE_NUMS = 7;
		final int BALL_FRAME_NUMS = 10;	
		
		// stage size
		final double WIDTH = 400, HEIGHT = 400;
		double centerX = WIDTH / 2, centerY = HEIGHT /2;	
		double[][] nodeArr = new double[NODE_NUMS][2]; 

			
		// Create a new pane
		Pane pane = new Pane();
		
		/* mouse event for trigger the animatiton of the balls */
		pane.setOnMouseClicked(e -> {
			// some variable for ball's animation
			double beanStartX = centerX;
			double beanStartY = centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.1* HEIGHT;
			double dx = nodeArr[NODE_NUMS-1][0]-nodeArr[NODE_NUMS-2][0];
			double dy = nodeArr[NODE_NUMS-1][1]-nodeArr[NODE_NUMS-2][1];
			double currentX = 0.0, currentY = 0.0;
			
			Circle ballObj = new Circle(beanStartX,beanStartY,4,Color.RED);
			
			pane.getChildren().add(ballObj);

			/* creat a new timeline for ball's animation */
			Timeline animation = new Timeline();
			KeyFrame[] ball = new KeyFrame[BALL_FRAME_NUMS];
			
			for(int i=0 ; i<BALL_FRAME_NUMS ; i++){
				int duration = 500*i;
				if(i==0){	// frame 0
					ball[i] = new KeyFrame(Duration.millis(duration),new KeyValue(ballObj.translateXProperty(), currentX), new KeyValue(ballObj.translateYProperty(), currentY));
				}
				else if(i==1){
					ball[i] = new KeyFrame(Duration.millis(duration),new KeyValue(ballObj.translateXProperty(), currentX), new KeyValue(ballObj.translateYProperty(), currentY=currentY+45)); 
				}
				else if(i==9){
					ball[i] = new KeyFrame(Duration.millis(duration),new KeyValue(ballObj.translateXProperty(), currentX), new KeyValue(ballObj.translateYProperty(), currentY=currentY+50)); 
				}
				else{
					if(((int)(Math.random()*2))==0){
						ball[i] = new KeyFrame(Duration.millis(duration),new KeyValue(ballObj.translateXProperty(), currentX = currentX + dx), new KeyValue(ballObj.translateYProperty(), currentY=currentY-dy));
					}
					else{
						ball[i] = new KeyFrame(Duration.millis(duration),new KeyValue(ballObj.translateXProperty(), currentX = currentX - dx), new KeyValue(ballObj.translateYProperty(), currentY=currentY-dy));		
					}
				}	
			}
			animation.getKeyFrames().addAll(ball[0],ball[1],ball[2],ball[3],ball[4],ball[5],ball[6],ball[7],ball[8],ball[9]);
			animation.play();
		});

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
		Line[] line = new Line[LINE_NUMS];
		for(int i=0;i<LINE_NUMS;i++){
			line[i] = new Line(	centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT ,
								centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT + 0.15* HEIGHT);	// add the line with the coordinates
			pane.getChildren().add(line[i]);	// add the line array to the pane
		}
		
		/* Creat a new circle arrays */
		Circle[] circle = new Circle[NODE_NUMS];
		int nodeIndex = 0;
			for( int j = LINE_NUMS ; j>0 ; j-- ){
				for(int i=0;i<j;i++){
					circle[i] = new Circle();
					circle[i].setRadius(5.0);	// set the radius
					nodeArr[nodeIndex][0]= centerX + 0.0875* WIDTH*(i-3+0.5*(LINE_NUMS-j));
					nodeArr[nodeIndex][1]= centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.0875* WIDTH*(7-j)*0.85;
					circle[i].setLayoutX(nodeArr[nodeIndex][0]);	// set the circle's x coordinates
					circle[i].setLayoutY(nodeArr[nodeIndex][1]);	// set the circle's y coordinates
					pane.getChildren().add(circle[i]);// add the circle array to the pane
					nodeIndex++;
				}
			}

		/* Creat a scene and show it */
		Scene scene = new Scene(pane,WIDTH,HEIGHT);	// create a scene for the pane
		primaryStage.setTitle("Bean Machine Play");	// stage title
		primaryStage.setScene(scene);	// put the pane on the stage
		primaryStage.show();	// show it!
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
}

