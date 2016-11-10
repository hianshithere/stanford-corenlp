import java.util.*;
import edu.stanford.nlp.pipeline.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class TestCoreNLP {

  public static void main(String[] args) throws IOException {
    PrintWriter out;
   out = new PrintWriter("C:\\Users\\dupsm\\Desktop\\stanford corenlp\\output1me.xml");
     
    Properties props=new Properties();
    props.setProperty("annotators","tokenize, ssplit, pos,lemma");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    Annotation annotation;  
    	String readString = null;
	    BufferedReader br = new BufferedReader ( new FileReader ( "C:\\Users\\dupsm\\Desktop\\stanford corenlp\\input.txt" )  ) ;
	    PrintWriter pw = new PrintWriter ( new BufferedWriter ( new FileWriter ( "C:\\Users\\dupsm\\Desktop\\stanford corenlp\\output.txt", false )  )  ) ;      
String x = null;
	    while  (( readString = br.readLine ())  != null)   {
	         pw.println ( readString ) ; String xx=readString;x=xx;//System.out.println("OKKKKK"); 
    	annotation = new Annotation(x);
 
    pipeline.annotate(annotation);
    //PrettyLine prints the properties of any annotation
    pipeline.prettyPrint(annotation, out);
	    }
	    br.close (  ) ;
    pw.close (  ) ;
    System.out.println("Done...");
   
       
  }
  
}