/*Authors:
* Dominic Del Vecchio
* Sai Gowthami Bojja
*/
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
Main exectures the animation loop, sets the stage and calls all of the functions for the simulation
*/

public class Main extends Application
{
  
  BorderPane root = new BorderPane();
  final Timeline forestAnimation = new Timeline();
  //animation time sets the number of frames per second of the animation
  private int animationTime = 60;
  //Sets the animmation stage, panes, timeline for animation and buttons
  @Override
  public void start(Stage stage) throws Exception
  {
    //Calls a new forest into instantiation and sets the grid
    Forrest forest = new Forrest(50,0);
    HBox top = new HBox();
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
                    if(forest.getCycle() <= 20)
                    {
                      forest.simulate();
                    }
                    else forestAnimation.stop();
                  }
                });
        forestAnimation.getKeyFrames().add(life);
        forestAnimation.setCycleCount(Animation.INDEFINITE);
        forestAnimation.play();
      }
    });
    //top.getChildren().addAll(zoom, R1, R2, R3, R4, random, preset1, preset2, preset3, preset4, start);
    top.getChildren().add(start);
    root.setTop(top);
    root.setCenter(forest.grid);
    root.setLeft(null);
    root.setRight(null);
    root.setBottom(null);
    Scene scene = new Scene(root, 504, 529, Color.BLACK);
    stage.setTitle("Forest Growth Simulation");
    stage.setScene(scene);
    stage.show();
  }
  
  public static void main(String[] args)
  {
    launch(args);
  }
}
