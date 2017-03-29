import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 * Created by Dominic on 3/27/2017.
 */
public class Forrest
{
  private int forestSize = 252;
  private Cell [][] forrest = new Cell[forestSize][forestSize];
  private int wall = 9;
  private int bioMass;
  private int gridSpace =1;
  public Group grid = new Group();
  
  Forrest (int growthProb)
  {
    for(int y=0; y <252; y++)
    {
      for(int x=0; x <252; x++)
      {
        Cell cell = new Cell(x,y,growthProb);
        forrest [y][x] = cell;
        grid.getChildren().add(cell);
        cell.setTranslateY(y);
        cell.setTranslateX(x);
        
      }
    }
    setWall();
  }
  private void setWall()
  {
    for(int y=0; y<252; y+=251)
    {
      for(int x=0; x<252; x++)
      {
        forrest[y][x].setStatus(wall);
        //System.out.println(forrest[y][x].gety());
        //System.out.println(forrest[y][x].getx());
      }
    }
    for(int y=1; y<251; y++)
    {
      for (int x=0; x<252; x+=251)
      {
        forrest[y][x].setStatus(wall);
      }
    }
  }
  public int sumGrowth()
  {
    bioMass = 0;
    for(int y=0; y<252; y++)
    {
      for(int x=0; x<252; x++)
      {
        if(forrest[y][x].getStatus() == 1)
        {
          bioMass++;
        }
      }
    }
    return bioMass;
  }
  
  public void setFire(int y, int x)
  {
    for(int i=y-1; i<y+2; i++)
    {
      for(int j=x-1; j<x+2; j++)
      {
        if(forrest[i][j].getStatus()==1)
        {
          forrest[i][j].setStatus(2);
        }
      }
    }
    
  }
  
  public Cell getCell(int y, int x)
  {
    return forrest[y][x];
  }


public void simulate()
{
  for(int y=1; y<251; y++)
  {
    for(int x=1; x<251; x++)
    {
      if(forrest[y][x].getStatus() ==2)
      {
        forrest[y][x].setStatus(3);
        forrest[y][x].setFill(Color.RED);
      }
      else if(forrest[y][x].getStatus() ==4)
      {
        forrest[y][x].setStatus(0);
        forrest[y][x].setFill(Color.WHITE);
        
      }
      else if(forrest[y][x].getStatus() ==5)
      {
        forrest[y][x].setStatus(1);
        forrest[y][x].setFill(Color.GREEN);
      }
    }
  }
  for(int y=1; y<251; y++)
  {
    for(int x=1; x<251; x++)
    {
      if(forrest[y][x].getStatus() ==3)
      {
        setFire(y,x);
        forrest[y][x].setStatus(4);
      }
    }
  }
  
  for(int y=1; y<251; y++)
  {
    for(int x=1; x<251; x++)
    {
      forrest[y][x].growTree();
      forrest[y][x].lightning();
    }
  }
  System.out.println(sumGrowth());
}
}
