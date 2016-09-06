import java.io.*;
import java.util.*;

public class Token{
  public static void main(String[] args) throws IOException {
    BufferedReader br = null;
    StreamTokenizer str;
    try{
      String s;

      br = new BufferedReader(new FileReader("S1.s"));
      str = new StreamTokenizer(br);

      while(str.nextToken() != StreamTokenizer.TT_EOF){
        switch(str.ttype){

          case StreamTokenizer.TT_EOL:
            System.out.println("End of Line encountered.");
            break;

          case StreamTokenizer.TT_WORD:
            if(str.sval.contains("println")){
              System.out.println("Reserved : "+ str.sval);
              break;
            }

            System.out.println("ID : "+ str.sval);
            break;

          case StreamTokenizer.TT_NUMBER:
            System.out.println("Number : "+ str.nval);
            break;

          default:
            System.out.println("Other : " + (char) str.ttype);
        }
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
    finally{
      try{
        if(br != null){
          br.close();
        }
      }
      catch(Exception e){
        System.out.println(e);
      }
    }
  }
}
