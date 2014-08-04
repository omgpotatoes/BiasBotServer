package org.alexanderconrad.bias_bot_server.nlp;

import java.util.ArrayList;

/**
 *
 * @author alexander.p.conrad@gmail.com
 */
public class Sentence {

  private ArrayList<Token> tokenArray;
  private String text;
  private int sentimentClass;

  public Sentence(ArrayList<Token> tokenArray, String text, int sentimentClass) {
    super();
    this.tokenArray = tokenArray;
    this.text = text;
    this.sentimentClass = sentimentClass;
  }

  public ArrayList<Token> getTokenArray() {
    return tokenArray;
  }

  public void setTokenArray(ArrayList<Token> tokenArray) {
    this.tokenArray = tokenArray;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getSentimentClass() {
    return sentimentClass;
  }

  public void setSentimentClass(int sentimentClass) {
    this.sentimentClass = sentimentClass;
  }

  @Override
  public String toString() {
    return "Sentence [tokenArray=" + tokenArray + ", text=" + text
        + ", sentimentClass=" + sentimentClass + "]";
  }

  public String toJsonString() {
    String jsonString = "{ tokenArray: [\n";
    for (Token token: tokenArray) {
      jsonString += "  " + token.toJsonString() + ",\n";
    }
    jsonString += "  text: \"" + text + "\",\n  sentimentClass: " + sentimentClass + " }";
    return jsonString;
  }

}
