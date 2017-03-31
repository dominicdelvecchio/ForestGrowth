import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Dominic Del Vecchio and Sai Gowthami Bojja on 3/27/2017.
 */
public class Forrest
{
  private int forestSize = 252;
  private Cell [][] forrest = new Cell[forestSize][forestSize];
  private int wall = 9;
  private int bioMass;
  private int gridSpace =1;
  public Group grid = new Group();
  private int count = 0;
  private int sum =0;
  private int growthProb;
  private int fireDep = 0;
  private Random rand = new Random();
  private double size = 2;
  Forrest (int growthProb)
  {
    this.growthProb = growthProb;
    this.fireDep = fireDep;
    clearForrest();
    
  }
  
   public void hireFireFighters(int n)
   {
     fireDep = fireDep + n;
   }
   
  private void clearForrest()
  {
    for(int y=0; y <252; y++)
    {
      for (int x = 0; x < 252; x++)
      {
        Cell cell = new Cell(x, y, growthProb,size,size);
        forrest[y][x] = cell;
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
        forrest[y][x].setFill(Color.BLACK);
        //System.out.println(forrest[y][x].gety());
        //System.out.println(forrest[y][x].getx());
      }
    }
    for(int y=1; y<251; y++)
    {
      for (int x=0; x<252; x+=251)
      {
        forrest[y][x].setStatus(wall);
        forrest[y][x].setFill(Color.BLACK);
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
  public double avgGrowth(int sum)
  {
    return sum/5000;
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
        if(fireDep >= rand.nextInt(1000))
        {
          forrest[y][x].setStatus(6);
          forrest[y][x].setFill(Color.BLUE);
        }
        else
        {
          forrest[y][x].setStatus(3);
          forrest[y][x].setFill(Color.RED);
        }
      }
      else if(forrest[y][x].getStatus()==4)
      {
        forrest[y][x].setStatus(0);
        forrest[y][x].setFill(Color.WHITE);
        
      }
      else if(forrest[y][x].getStatus()==5 || forrest[y][x].getStatus()==6)
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
          setFire(y, x);
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
  count++;
  sum = sum + sumGrowth();
  
  if(count == 5000)
  {
    System.out.println(avgGrowth(sum));
    Data data = new Data(avgGrowth(sum));
    count = 0;
    clearForrest();
    
    
  };
  //System.out.println(count);
  //System.out.println(sumGrowth());
}
}
