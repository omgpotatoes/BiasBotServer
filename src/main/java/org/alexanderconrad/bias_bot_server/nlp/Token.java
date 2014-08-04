package org.alexanderconrad.bias_bot_server.nlp;

import edu.stanford.nlp.trees.Tree;

/**
 *
 * @author alexander.p.conrad@gmail.com
 */
public class Token {

  private String word;
  private String pos;
  private String nerLabel;
  private int sentimentClass;
  private Tree sentimentTree;

  public Token(String word, String pos, String nerLabel, int sentimentClass,
      Tree sentimentTree) {
    super();
    this.word = word;
    this.pos = pos;
    this.nerLabel = nerLabel;
    this.sentimentClass = sentimentClass;
    this.sentimentTree = sentimentTree;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getPos() {
    return pos;
  }

  public void setPos(String pos) {
    this.pos = pos;
  }

  public String getNerLabel() {
    return nerLabel;
  }

  public void setNerLabel(String nerLabel) {
    this.nerLabel = nerLabel;
  }

  public int getSentimentClass() {
    return sentimentClass;
  }

  public void setSentimentClass(int sentimentClass) {
    this.sentimentClass = sentimentClass;
  }

  public Tree getSentimentTree() {
    return sentimentTree;
  }

  public void setSentimentTree(Tree sentimentTree) {
    this.sentimentTree = sentimentTree;
  }

  @Override
  public String toString() {
    return "Token [word=" + word + ", pos=" + pos + ", nerLabel=" + nerLabel
        + ", sentimentClass=" + sentimentClass + ", sentimentTree="
        + sentimentTree + "]";
  }

  public String toJsonString() {
    // TODO add sentimentTree
    return "{ word: \"" + word + "\", pos: " + pos + ", nerLabel: " + nerLabel
        + ", sentimentClass: " + sentimentClass + "}";
  }

}
