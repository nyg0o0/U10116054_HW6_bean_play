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
		// numbers of beans, nodes, lines, bean's frame (const)
		final int NODE_NUMS = 28;
		final int SLOT_NUMS = 7;
		int beanNumsCurrent = 0;
		// stage size
		final double WIDTH = 400, HEIGHT = 400;
		double centerX = WIDTH / 2, centerY = HEIGHT /2;	
		double[][] nodeArr = new double[NODE_NUMS][2]; 
		
		// Create a new pane
		Pane pane = new Pane();
		
		/* mouse event for trigger the animatiton of the beans */
		pane.setOnMouseClicked(e -> {
			final int BEAN_NUMS = 50;
			final int BALL_FRAME_NUMS = 10;	
			
			// some variable for bean's animation
			double beanStartX = centerX;
			double beanStartY = centerY - (Math.sqrt(3)/2)* 0.3* HEIGHT - 0.1* HEIGHT;
			double dx = nodeArr[NODE_NUMS-1][0]-nodeArr[NODE_NUMS-2][0];
			double dy = nodeArr[NODE_NUMS-1][1]-nodeArr[NODE_NUMS-2][1];
			double currentX = 0.0, currentY = 0.0;
			
			// new a object for bean( circle with x, y, radius and colors. ) and add to the pane
			Circle beanObj = new Circle(beanStartX,beanStartY,4,Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
			pane.getChildren().add(beanObj);

			/* creat a new timeline for bean's animation */
			Timeline animation = new Timeline();
			KeyFrame[] bean = new KeyFrame[BALL_FRAME_NUMS];
			
			for(int i=0 ; i<BALL_FRAME_NUMS ; i++){
				int duration = 500*i;
				if(i==0){	// frame 0
					bean[i] = new KeyFrame(Duration.millis(duration),new KeyValue(beanObj.translateXProperty(), currentX), new KeyValue(beanObj.translateYProperty(), currentY));
				}
				else if(i==1){
					bean[i] = new KeyFrame(Duration.millis(duration),new KeyValue(beanObj.translateXProperty(), currentX), new KeyValue(beanObj.translateYProperty(), currentY=currentY+45)); 
				}
				else if(i==9){
					bean[i] = new KeyFrame(Duration.millis(duration),new KeyValue(beanObj.translateXProperty(), currentX), new KeyValue(beanObj.translateYProperty(), currentY=currentY+50)); 
				}
				else{
					if(((int)(Math.random()*2))==0){
						bean[i] = new KeyFrame(Duration.millis(duration),new KeyValue(beanObj.translateXProperty(), currentX = currentX + dx), new KeyValue(beanObj.translateYProperty(), currentY=currentY-dy));
					}
					else{
						bean[i] = new KeyFrame(Duration.millis(duration),new KeyValue(beanObj.translateXProperty(), currentX = currentX - dx), new KeyValue(beanObj.translateYProperty(), currentY=currentY-dy));		
					}
				}	
			}
			animation.getKeyFrames().addAll(bean[0],bean[1],bean[2],bean[3],bean[4],bean[5],bean[6],bean[7],bean[8],bean[9]);	// add the frames to the timeline
			animation.play();	// play the frames of a bean
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
		Line[] line = new Line[SLOT_NUMS];
		for(int i=0;i<SLOT_NUMS;i++){
			line[i] = new Line(	centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT ,
								centerX+ 0.0875* WIDTH*(i-3),centerY + (Math.sqrt(3)/2)* 0.3* HEIGHT + 0.15* HEIGHT);	// add the line with the coordinates
			pane.getChildren().add(line[i]);	// add the line array to the pane
		}
		
		/* Creat a new circle arrays */
		Circle[] circle = new Circle[NODE_NUMS];
		int nodeIndex = 0;
			for( int j = SLOT_NUMS ; j>0 ; j-- ){
				for(int i=0;i<j;i++){
					circle[i] = new Circle();
					circle[i].setRadius(5.0);	// set the radius
					nodeArr[nodeIndex][0]= centerX + 0.0875* WIDTH*(i-3+0.5*(SLOT_NUMS-j));
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
	
	/* Main method */
	public static void main(String[] args) {
		Application.launch(args);
	}
}

