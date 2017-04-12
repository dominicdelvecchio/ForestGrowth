import javax.swing.*;
import java.io.*;

/**
 * Created by Dominic on 3/30/2017.
 */
public class Data
{
  private String FILENAME;
    
    public Data(double data, String filename)
    {
      this.FILENAME = filename;
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
  
  public Data(int data, String filename)
  {
    this.FILENAME = filename;
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
