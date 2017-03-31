import javax.swing.*;
import java.io.*;

/**
 * Created by Dominic on 3/30/2017.
 */
public class Data
{
  private static final String FILENAME = "C:\\Users\\Dominic\\IdeaProjects\\Forrest Growth\\src\\test.txt";
    
    public Data(int data)
    {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true)))
      {
  
        bw.write(String.valueOf(data));
        bw.newLine();
        //bw.close();
      } catch (IOException e)
      {
        e.printStackTrace();
      }
  
    }
  
  public Data(double data)
  {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true)))
    {
      
      bw.write(String.valueOf(data));
      bw.newLine();
      //bw.close();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    
  }
}
