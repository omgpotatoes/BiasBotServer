package org.alexanderconrad.bias_bot_server.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.AnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
 *
 * @author alexander.p.conrad@gmail.com
 */
public class StanfordEngine {

  private static Properties properties;
  private static StanfordCoreNLP stanfordCoreNlp;

  public static StanfordCoreNLP getPipeline() {
    if (stanfordCoreNlp == null) {
      properties =  new Properties();
      properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");
//      properties.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
      stanfordCoreNlp = new StanfordCoreNLP(properties);
    }
    return stanfordCoreNlp;
  }

  public static ArrayList<Sentence> annotateText(String text) {
    ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();
    Annotation document = new Annotation(text);
    getPipeline().annotate(document);
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    for(CoreMap sentence: sentences) {

      ArrayList<Token> tokenArray = new ArrayList<Token>();

      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
        // this is the text of the token
        String word = token.get(TextAnnotation.class);
        // this is the POS tag of the token
        String pos = token.get(PartOfSpeechAnnotation.class);
        // this is the NER label of the token
        String ne = token.get(NamedEntityTagAnnotation.class);
        // this is the sentiment class of the token
//        Tree tokenSentimentTree = token.get(AnnotatedTree.class);
//        System.out.println("DEBUG : tokenSentimentTree: "+ tokenSentimentTree);
//        int tokenSentimentClass = RNNCoreAnnotations.getPredictedClass(tokenSentimentTree);
//        tokenArray.add(new Token(word, pos, ne, tokenSentimentClass, tokenSentimentTree));
        tokenArray.add(new Token(word, pos, ne, 0, null));

        // try to re-annotate this single token for naive fine-grained sentiment??
        Annotation termAnnotate = new Annotation(word);
        try {
          getPipeline().annotate(termAnnotate);
          List<CoreMap> termSents = termAnnotate.get(SentencesAnnotation.class);
          for(CoreMap termSent: termSents) {
            Tree termAnnotateTree = termSent.get(AnnotatedTree.class);
            int termSentimentClass = RNNCoreAnnotations.getPredictedClass(termAnnotateTree);
            System.out.println("DEBUG : word="+word+", sentiment="+termSentimentClass);
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          //this could happen for some shorter tokens, ie, punctuation?
        }
      }

      // this is the parse tree of the current sentence
//      Tree tree = sentence.get(TreeAnnotation.class);

      // this is the Stanford dependency graph of the current sentence
//      SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);

      // this is the sentiment label for the sentence
      Tree sentimentTree = sentence.get(AnnotatedTree.class);
      // possible values are 0 = strong negative, 1 = weak negative, 2 = neutral, 3 = weak positive, 4 = strong positive
      int sentimentClass = RNNCoreAnnotations.getPredictedClass(sentimentTree);
      System.out.println("DEBUG : Tree sentimentTree:" + sentimentTree.toString());

      System.out.println("DEBUG : predictions : "+RNNCoreAnnotations.getPredictions(sentimentTree).toString());
      System.out.println("DEBUG : NodeVector : "+RNNCoreAnnotations.getNodeVector(sentimentTree).toString());

      // this is the text of the sentence (?)
      String sentenceText = sentence.get(TextAnnotation.class);

      sentenceList.add(new Sentence(tokenArray, sentenceText, sentimentClass));

    }

    // This is the coreference link graph
    // Each chain stores a set of mentions that link to each other,
    // along with a method for getting the most representative mention
    // Both sentence and token offsets start at 1!
    // (at this point, included only for s&g)
//    Map<Integer, CorefChain> graph =
//      document.get(CorefChainAnnotation.class);
//    System.out.println("DEBUG : Map<Integer, CorefChain> graph:" + graph.toString());

    return sentenceList;

  }



}
