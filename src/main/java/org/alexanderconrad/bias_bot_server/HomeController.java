package org.alexanderconrad.bias_bot_server;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.alexanderconrad.bias_bot_server.nlp.RawText;
import org.alexanderconrad.bias_bot_server.nlp.StanfordEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate );

    return "home";
  }


  /**
   *
   */
  @RequestMapping(value = "/requestNlpProcess", method = RequestMethod.GET)
  public ModelAndView requestNlpProcess() {
    return new ModelAndView("requestNlpProcess", "command", new RawText());
  }

  /**
   *
   */
  @RequestMapping(value = "/executeNlpProcess", method = RequestMethod.POST)
  public String executeNlpProcess(@ModelAttribute("SpringWeb")RawText rawText, ModelMap model) {
    model.addAttribute("nlpProcessingResult", StanfordEngine.annotateText(rawText.getRawText().toString()));
    return "nlpProcessResult";
  }

}
