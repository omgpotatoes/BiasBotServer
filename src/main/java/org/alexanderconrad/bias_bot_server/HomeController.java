package org.alexanderconrad.bias_bot_server;

import java.util.ArrayList;

import org.alexanderconrad.bias_bot_server.nlp.Document;
import org.alexanderconrad.bias_bot_server.nlp.RawText;
import org.alexanderconrad.bias_bot_server.nlp.Sentence;
import org.alexanderconrad.bias_bot_server.nlp.StanfordEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.stanford.nlp.trees.Tree;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  /**
   *
   */
  @RequestMapping(value = {"/nlp_processing_request", "/requestNlpProcess", "/"}, method = RequestMethod.GET)
  public ModelAndView requestNlpProcess() {
    return new ModelAndView("nlp_processing_request", "command", new RawText());
  }

  /**
   *
   */
  @RequestMapping(value = {"/nlp_document", "/executeNlpProcess"}, method = RequestMethod.POST)
  public String executeNlpProcess(@ModelAttribute("SpringWeb")RawText rawText, ModelMap model) {
    Document nlpOutput = StanfordEngine.annotateText(rawText.getRawText().toString());
    ArrayList<Sentence> nlpSentences = nlpOutput.getSentences();
    ArrayList<Tree> parseTrees = nlpOutput.getParseTrees();
    model.addAttribute("nlpSentences", nlpSentences.toString());
    model.addAttribute("nlpParseTrees", parseTrees.toString());
    // TODO move out of controller?
    String formattedString = "";
    for (Sentence sentence : nlpSentences) {
      formattedString += " " + sentence.toHtmlFormattedString();
    }
    model.addAttribute("nlpProcessingResultPretty", formattedString);
    return "nlp_output";
  }

}
