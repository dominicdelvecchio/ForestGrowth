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
  private int growth1;
  private int growth2;
  private int status = 0;
  private Random rand = new Random();
  private int grow;
  private int fire;
  private int hSize = 3;
  private int wSize = 5;
  private int gorwthCount;
  private int species;
  //1=species 1 and 2=species2
  private int fireCount;
  /*
  * Values for the current status of the cell
  * some are transitional so that cells in the same
  * timestep are not effecting each other with changes
  * until the next loop
  */
  //0=barren
  //1=species1 alive
  //2=caught fire
  //3=on fire
  //4=dying fire
  //5=species1 cominig alive
  //6=put out
  //7=species2 cominig alive
  //8=species2 alive
  //9=wall
  
  //cell constructor sets the x and y location on the grid, growth rates for
  //each species and the width and heigth of the cell. Extends rectangle
  //for the animation
  Cell(int x, int y, int growth1,int growth2, double width, double heighth)
  {
    super(x,y,width,heighth);
    setFill(Color.WHITE);
    this.growth1 = growth1;
    this.growth2 = growth2;
  }
  //function for tree growth one species
  public void growTree1()
  {
    if(status == 0)
    {
      grow = rand.nextInt(1000) + 1;
      if (growth1 >= grow)
      {
        this.status = 5;
        this.species = 1;
      }
    }
  }
  //function for tree growth two species
  public void growTree2()
  {
    if(status == 0)
    {
      if(rand.nextInt(2) == 1)
      {
        grow = rand.nextInt(1000) + 1;
        if (growth1 >= grow) this.status = 5;
        this.species =1;
      }
      else
      {
        grow = rand.nextInt(1000) + 1;
        if (growth2 >= grow) this.status = 5;
        this.species =2;
      }
    }
  }
  //function for random lightning strikes
  public void lightning()
  {
    if(status ==1)
    {
      fire = rand.nextInt(1000) + 1;
      if (fire <= 1) this.status = 2;
    }
  }
  //function to check the cells status
  public int getStatus()
  {
    return this.status;
  }
  //function to set the cells status
  public void setStatus(int z)
  {
    this.status = z;
  }
  //function to get the cells species
  public int getSpecies(){return this.species;}
  
}
