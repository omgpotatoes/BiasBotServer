package org.alexanderconrad.bias_bot_server.nlp;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexander.p.conrad@gmail.com
 */
public class StanfordEngineTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    StanfordEngine.getPipeline();
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link org.alexanderconrad.bias_bot_server.nlp.StanfordEngine#annotateText(java.lang.String)}.
   */
  @Test
  public void testAnnotateTextNormal1() {
    String sampleText = "Dick Cheney's latest hit single, \"I Hate Kittens and Rainbows\", is stupendous and awesome and fantastic!";
    List<Sentence> annotatedSentences = StanfordEngine.annotateText(sampleText);
    Assert.assertEquals(1, annotatedSentences.size());
    Assert.assertEquals(3, annotatedSentences.get(0).getSentimentClass());
    Assert.assertEquals(sampleText, annotatedSentences.get(0).getText());
  }

  /**
   * Test method for {@link org.alexanderconrad.bias_bot_server.nlp.StanfordEngine#annotateText(java.lang.String)}.
   */
  @Test
  public void testAnnotateTextNormal2() {
    String sampleText1 = "I hate Barak Obama's guts, that damn stinking facist commie muslim!";
    String sampleText2 = "He's also an ugly dumb atheist socialism!";
    String sampleText3 = "At least he makes some OK baklava.";
    List<Sentence> annotatedSentences = StanfordEngine.annotateText(sampleText1+" "+sampleText2+" "+sampleText3);
    Assert.assertEquals(3, annotatedSentences.size());
    Assert.assertEquals(0, annotatedSentences.get(0).getSentimentClass());
    Assert.assertEquals(sampleText1, annotatedSentences.get(0).getText());
    Assert.assertEquals(1, annotatedSentences.get(1).getSentimentClass());
    Assert.assertEquals(sampleText2, annotatedSentences.get(1).getText());
    Assert.assertEquals(2, annotatedSentences.get(2).getSentimentClass());
    Assert.assertEquals(sampleText3, annotatedSentences.get(2).getText());
  }

}
