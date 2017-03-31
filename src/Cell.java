/**
 * Created by Dominic Del Vecchio and Sai Gowthami Bojja on 3/27/2017.
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;
/*The cell class represents each individual cell on the grid
* each cell can grow a tree, catch fire or be put out by a fire fighter*/
public class Cell extends Rectangle
{
  private int growth;
  private int status = 0;
  private Random rand = new Random();
  private int grow;
  private int fire;
  private int hSize = 3;
  private int wSize = 5;
  private int gorwthCount;
  
  private int fireCount;
  /*
  * Values for the current status of the cell
  * some are transitional so that cells in the same
  * timestep are not effecting each other with changes
  * until the next loop
  */
  //0=barren
  //1=alive
  //2=caught fire
  //3=on fire
  //4=dying fire
  //5=cominig alive
  //6=put out
  //9=wall
  
  Cell(int x, int y, int growth, double width, double heighth)
  {
    super(x,y,width,heighth);
    setFill(Color.WHITE);
     //this.x = x;
    //this.y = y;
    this.growth = growth;
  }
  
  public void growTree()
  {
    if(status == 0)
    {
      grow = rand.nextInt(1000) + 1;
      if (growth >= grow) this.status = 5;
    }
  }
  
  public void lightning()
  {
    if(status ==1)
    {
      fire = rand.nextInt(1000) + 1;
      if (fire <= 1) this.status = 2;
    }
  }
  
  
  
  int getStatus()
  {
    return this.status;
  }
  
  void setStatus(int z)
  {
    this.status = z;
  }
  
  //int getx(){return x;}
  //int gety(){return y;}
}
