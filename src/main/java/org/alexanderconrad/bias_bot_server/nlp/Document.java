package org.alexanderconrad.bias_bot_server.nlp;

import java.util.ArrayList;

import edu.stanford.nlp.trees.Tree;

/**
 *
 * @author alexander
 *
 */
public class Document {

  private ArrayList<Sentence> sentences;
  private ArrayList<Tree> parseTrees;

  public Document(ArrayList<Sentence> sentences, ArrayList<Tree> parseTrees) {
    super();
    this.sentences = sentences;
    this.parseTrees = parseTrees;
  }

  public ArrayList<Sentence> getSentences() {
    return sentences;
  }

  public ArrayList<Tree> getParseTrees() {
    return parseTrees;
  }

}
