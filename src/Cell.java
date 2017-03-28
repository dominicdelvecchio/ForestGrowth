/**
 * Created by Dominic on 3/27/2017.
 */
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;
public class Cell extends Rectangle
{
  //private int x;
  //private int y;
  private int growth;
  private int status = 0;
  private Random rand = new Random();
  private int grow;
  private int fire;
  private int size = 1;
  private int gorwthCount;
  private int fireCount;
  //0=barren
  //1=alive
  //2=caught fire
  //3=on fire
  //4=dying fire
  //cominig alive
  //9= wall
  
  Cell(int x, int y, int growth)
  {
    super(x,y,1,1);
    setFill(Color.BLACK);
     //this.x = x;
    //this.y = y;
    this.growth = growth;
  }
  
  public void growTree()
  {
    if(status == 0)
    {
      grow = rand.nextInt(100) + 1;
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
