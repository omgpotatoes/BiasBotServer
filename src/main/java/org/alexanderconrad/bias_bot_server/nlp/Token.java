package org.alexanderconrad.bias_bot_server.nlp;

import edu.stanford.nlp.trees.Tree;

/**
 *
 * @author alexander.p.conrad@gmail.com
 */
public class Token {

  // TODO move these to CSS, refine selections
  public static String negWeakHex = "#A00000";
  public static String negStrongHex = "#F00000";
  public static String posWeakHex = "#009000";
  public static String posStrongHex = "#00E000";

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

  public Token(String word, String pos, String nerLabel, int sentimentClass) {
    super();
    this.word = word;
    this.pos = pos;
    this.nerLabel = nerLabel;
    this.sentimentClass = sentimentClass;
    this.sentimentTree = null;
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

  /**
   *
   * @return
   */
  public String toHtmlFormattedString() {
    // TODO testme!
    switch (sentimentClass) {
      case 0:
        return "<font color=\""+negStrongHex+"\">"+word+"</font>";
      case 1:
        return "<font color=\""+negWeakHex+"\">"+word+"</font>";
      case 3:
        return "<font color=\""+posWeakHex+"\">"+word+"</font>";
      case 4:
        return "<font color=\""+posStrongHex+"\">"+word+"</font>";
      default:
        return word;
    }
  }

  @Override
  public String toString() {
    // TODO add sentimentTree
    if (sentimentTree == null) {
      return "Token [word=" + word + ", pos=" + pos + ", nerLabel=" + nerLabel
          + ", sentimentClass=" + sentimentClass + "]";
    }
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
