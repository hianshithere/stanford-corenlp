import edu.stanford.nlp.pipeline.*;
import java.util.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SentenceAnalyser {
	public static void main(String args[])throws IOException{
		String read;
		Annotation annotate;
		BufferedReader br=new BufferedReader(new FileReader("C:\\Users\\dupsm\\Desktop\\CoreNLP\\input.txt"));
		Properties prop=new Properties();
		prop.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		StanfordCoreNLP pipeline=new StanfordCoreNLP(prop);
		PrintWriter out = new PrintWriter ( new BufferedWriter ( new FileWriter ( "C:\\Users\\dupsm\\Desktop\\CoreNLP\\output.txt", false )  )  ) ;
		while  (( read = br.readLine ())  != null)   {
			annotate = new Annotation(read);
			 
		    pipeline.annotate(annotate);
		    List<CoreMap> sentences = annotate.get(SentencesAnnotation.class);
			String word="",pos="",ne="",lemma="";

			for(CoreMap sentence: sentences) {
			  // traversing the words in the current sentence
			  // a CoreLabel is a CoreMap with additional token-specific methods
			  for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
			    // this is the text of the token
			    word = token.get(TextAnnotation.class);
			    // this is the POS tag of the token
			    pos = token.get(PartOfSpeechAnnotation.class);
			    // this is the NER label of the token
			    ne = token.get(NamedEntityTagAnnotation.class);
			    lemma=token.get(LemmaAnnotation.class);
			    out.println("Word= " + word + " pos= " + pos + " NES= " + ne + " Lemma= " + lemma);
			  }
			}
		}
		br.close (  ) ;
	    out.close (  ) ;
	    System.out.println("Done...");
		}
	}
