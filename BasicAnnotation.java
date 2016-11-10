import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.pipeline.*;
import java.util.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
public class BasicAnnotation {
public static void main(String args[]){
	Properties prop=new Properties();
	prop.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,dcoref");
	StanfordCoreNLP pipeline=new StanfordCoreNLP(prop);
	String text="Hi my name is Deepesh Maskara and I live in West Bengal in India";
	Annotation annotation=new Annotation(text);
	pipeline.annotate(annotation);
	// these are all the sentences in this document
	// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
	List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
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
	    System.out.println("Word= " + word + " pos= " + pos + " NES= " + ne + " Lemma= " + lemma);
	  }

	  // this is the parse tree of the current sentence
	  Tree tree = sentence.get(TreeAnnotation.class);

	  // this is the Stanford dependency graph of the current sentence
	  SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
	}

	// This is the coreference link graph
	// Each chain stores a set of mentions that link to each other,
	// along with a method for getting the most representative mention
	// Both sentence and token offsets start at 1!
	Map<Integer, CorefChain> graph = 
	  annotation.get(CorefChainAnnotation.class);
	
	System.out.println("Completed");
}
}
