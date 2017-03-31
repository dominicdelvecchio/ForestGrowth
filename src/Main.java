/*Authors:
* Dominic Del Vecchio
* Sai Gowthami Bojja
*/

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application
{
  public static int timer = 0;
  public static int count =0;
  //public Group grid = new Group();
  private Group root = new Group();
  private final PerspectiveCamera camera = new PerspectiveCamera(false);
  final Timeline forestAnimation = new Timeline();
  private int animationTime = 10;
  private static final String FILENAME = "E:\\test\\filename.txt";
  @Override
  public void start(Stage stage) throws Exception
  {
    Forrest forest = new Forrest(5);
    BorderPane game = new BorderPane();
    HBox top = new HBox();
    top.getPadding();
    game.setCenter(forest.grid);
    //forest.grid.setLayoutX(250);
    //forest.grid.setLayoutY(250);
    //ScrollBar zoom = new ScrollBar();
    //zoom.setMin(0);
    //zoom.setMax(3);
    //zoom.setBlockIncrement(.05);
    //zoom.setUnitIncrement(.05);
    //forest.grid.scaleZProperty().bind(zoom.valueProperty());
    //Button to start game after selections have been made
    Button start = new Button();
    start.setText("START");
    start.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(ActionEvent event)
      {
        //Timmeline for the animation portion of the game
      
        KeyFrame life = new KeyFrame(Duration.millis(animationTime),
                new EventHandler<ActionEvent>()
                {
                  public void handle(ActionEvent event)
                  {
                    forest.simulate();
                  }
                });
        forestAnimation.getKeyFrames().add(life);
        forestAnimation.setCycleCount(5000);
        forestAnimation.play();
        
        
      }
    });
    //top.getChildren().addAll(zoom, R1, R2, R3, R4, random, preset1, preset2, preset3, preset4, start);
    top.getChildren().add(start);
    game.setTop(top);
    root.getChildren().add(game);
    Scene scene = new Scene(root, 500, 500, Color.ALICEBLUE);
    //root.setDepthTest(DepthTest.ENABLE);
    stage.setTitle("Forest Growth Simulation");
    stage.setScene(scene);
    stage.show();
    //scene.setCamera(camera);
  }
  
  public static void main(String[] args)
  {
    
    launch(args);
    
  }
}
