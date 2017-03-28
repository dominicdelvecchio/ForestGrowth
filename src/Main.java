import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
  public static int timer = 0;
  public static int count =0;
  
    public static void main(String[] args)
    {
      Forrest forrest = new Forrest(5);
      
      while(timer < 5000)
      {
        for(int y=1; y<251; y++)
        {
          for(int x=1; x<251; x++)
          {
            if(forrest.getCell(y,x).getStatus() ==2)
            {
              forrest.getCell(y,x).setStatus(3);
            }
            else if(forrest.getCell(y,x).getStatus() ==4)
            {
              forrest.getCell(y,x).setStatus(0);
            }
            else if(forrest.getCell(y,x).getStatus() ==5)
            {
              forrest.getCell(y,x).setStatus(1);
            }
          }
        }
        for(int y=1; y<251; y++)
        {
          for(int x=1; x<251; x++)
          {
            if(forrest.getCell(y,x).getStatus() ==3)
            {
              forrest.setFire(y,x);
              forrest.getCell(y,x).setStatus(4);
            }
          }
        }
        
        for(int y=1; y<251; y++)
        {
          for(int x=1; x<251; x++)
          {
            forrest.getCell(y,x).growTree();
            forrest.getCell(y,x).lightning();
          }
        }
        System.out.println(forrest.sumGrowth());
        timer++;
        //System.out.println(timer);
      }
      
    }
  
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    
  }
}
