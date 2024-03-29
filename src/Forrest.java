import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Random;
import static java.lang.Math.abs;
/**
 * Created by Dominic Del Vecchio and Sai Gowthami Bojja on 3/27/2017.
 */
//Forrest is the grid. It uses the cell class for each individual tree and controls the
//majority of the logic for the game.
public class Forrest
{
  //sets the size of the grid with room for a border
  private int forestSize = 252;
  private Cell [][] forrest = new Cell[forestSize][forestSize];
  //sets the cells status to wall so it will not be updated
  //this makes for easier array loops that can still run a check without going out of bounds
  private int wall = 9;
  //biomass for species one
  private int bioMass1;
  //biomass for species two
  private int bioMass2;
  public Group grid = new Group();
  private int count = 0;
  //sum of the biomass for the cycle for species one
  private int sumAvg1=0;
  //sum of the biomass for the cycle for species two
  private int sumAvg2=0;
  //growth probability for species one set by forest constructor
  private int growthProb1;
  //growth probability for species two set by forest constructor
  private int growthProb2;
  //number of firefighters present set initially to zero. Will only work if fire fighters are added
  private int fireDep = 0;
  private Random rand = new Random();
  private double size = 2;
  //death marker for longevity representing 5% of the forest
  private int death = 3125;
  //flag for if the forest dies
  private int deathFlag =0;
  //number of cycles that the forest has survived
  private int longevity =0;
  //number of complete cycles
  private int cycle =1;
  /*
  Creates a new forest with a growth probability for each species. If doing just
  one species the probability for the other species is set to 0. Additionally
  clears the forest by setting all cells to barren and marks the walls so they
  will be ignored during updates to the simulation
  */
  Forrest (int growthProb1, int growthProb2)
  {
    this.growthProb1 = growthProb1;
    this.growthProb2 = growthProb2;
    clearForrest();
  }
 
  //gets cycle count from main so program can be cut after runs are finished
  public int getCycle() {return cycle;}
  //clears the forrest setting all cells to blank and sets the grid for animation
  private void clearForrest()
  {
    for(int y=0; y <252; y++)
    {
      for (int x = 0; x < 252; x++)
      {
        Cell cell = new Cell(x, y, growthProb1,growthProb2,size,size);
        forrest[y][x] = cell;
        grid.getChildren().add(cell);
        cell.setTranslateY(y);
        cell.setTranslateX(x);
      }
    }
    setWall();
  }
  //sets the wall cells so that they will not update but can be used in the checking
  private void setWall()
  {
    for(int y=0; y<252; y+=251)
    {
      for(int x=0; x<252; x++)
      {
        forrest[y][x].setStatus(wall);
        forrest[y][x].setFill(Color.BLACK);
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
  //sums the total biomass each cycle by adding one point for each live cell
  //works for one or two species
  public void sumGrowth()
  {
    bioMass1 = 0;
    bioMass2 = 0;
    for(int y=0; y<252; y++)
    {
      for(int x=0; x<252; x++)
      {
        if(forrest[y][x].getStatus()==1)
        {
          if(forrest[y][x].getSpecies()==1)
          {
            bioMass1++;
          }
          else if(forrest[y][x].getSpecies()==2) bioMass2++;
        }
      }
    }
  }
  //commputes the average biomass for every cycle
  public double avgGrowth(int sum)
  {
    return sum/count;
  }
  //sets fire to neighbooring cells
  public void setFire(int y, int x)
  {
    for(int i=y-1; i<y+2; i++)
    {
      for(int j=x-1; j<x+2; j++)
      {
        if(forrest[i][j].getStatus()==1)
        {
          forrest[i][j].setStatus(2);
          //setFire(i,j);
        }
      }
    }
  }
// main logic for the game. Cycles through and sets the cells for animation,
//calls functions to set fires and grow cells depending on the cell status
public void simulate()
{
  /*first loop checks for cells that are in a phase change and sets them to the
  phase they are chnaging to by changing the status of the cell and the color fill.
  The animation and logic are handled in three different loops. The first handles
  any cells in a transition state. Cells iin a transition state are not effected by
  the logic checking for growth or fires. During the first loop they are updated into
  barren, life or fire status which makes them available to be checked by the logic.
  The second looks for fire cells and seets any alive cells in moores nighborhood
  on fire. The final loop checks for growth and random lighting strikes. Both of the final
  two loops set cells into transiton states.
  
  Finally the biomass of each species is summed and averaged and the a check is done to determinne
  if it is time to start a new cycle.
  */
  
  //loop to set transtion state from transiton to current states
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
        if(forrest[y][x].getSpecies() == 1) forrest[y][x].setFill(Color.LIMEGREEN);
        else forrest[y][x].setFill(Color.DARKGREEN);
      }
    }
  }
  //loop the fire portion of the logic. Any cells on fire will set fire to other
  //living cells near by.
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
  //logic portion that drives the random growth and random lighting stirkes
  for(int y=1; y<251; y++)
  {
    for(int x=1; x<251; x++)
    {
      forrest[y][x].growTree1();
      //forrest[y][x].growTree2();
      
      forrest[y][x].lightning();
    }
  }
  //portion of code to sum the running biomass
  count++;
  sumGrowth();
  sumAvg1 = sumAvg1 + bioMass1;
  sumAvg2 = sumAvg2 + bioMass2;
  //checks for premature death of the forest and sets the longevity to the time step(count)
  //if the forest dips below 5% once set the program will continue to run
  if(count > 10)
  {
    if(deathFlag == 0 && (bioMass1 <= death))
    {
      deathFlag =1;
      longevity = count;
    }
  }
  
    //stops program at a count of 1000 timesteps and resets the board for a new cycle
    //if the forest has not dipped below 5% sets the longevity for the length of the program
      if (count >= 1000)
      {
        if (deathFlag == 0)
        {
          longevity = count;
        }
        double d = 1000;
        /*
        various print statments and calls to data for recording results
        most are commented out
        */
        
        //Data data1 = new Data(avgGrowth(sumAvg1), "C:\\Users\\Dominic\\IdeaProjects\\Forrest Growth\\src\\Firefighters2.5%.txt");
        //Data data2 = new Data(avgGrowth(sumAvg2), "C:\\Users\\Dominic\\IdeaProjects\\Forrest Growth\\src\\species2.txt");
        //System.out.println(" Species1 Cycle count = " + cycle + "     GrowthProbability1 = " + growthProb1 / d + "    Biomass =    " + avgGrowth(sumAvg1));
        //System.out.println(" Species2 Cycle count = " + cycle + "     GrowthProbability2 = " + growthProb2 / d + "    Biomass =    " + avgGrowth(sumAvg2));
        //System.out.println("Count = " + count);
        System.out.println("Biomass =    " + avgGrowth(sumAvg1) + "   Longevity = " + longevity);
        deathFlag = 0;
        //System.out.println("Longevity = " + longevity);
        clearForrest();
        count = 0;
        sumAvg1 = 0;
        sumAvg2 = 0;
        longevity = 0;
        //growthProb1 = growthProb1 + 50;
        //growthProb2 = growthProb2 + 50;
        //fireDep = fireDep +50;
        cycle++;
      }
    }
  }
  //System.out.println(count);
  //System.out.println(bioMass1);

