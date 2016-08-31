package hu.learnerbot.dynamiclang.controller;

import hu.learnerbot.dynamiclang.scripting.Messenger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.groovy.GroovyScriptFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class HomeController
{
    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    private static final String VIEW_PATH_HOME = "pages/home";

    @Value("${learnerbot.default.script:}")
    private String defaultScript;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_HOME);
        modelAndView.addObject("sourceCode", defaultScript);
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView home(@RequestParam(name = "sourceCode", required = true) final String sourceCode)
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_HOME);
        modelAndView.addObject("sourceCode", sourceCode);

        try {
            ScriptSource scriptSource = new ScriptSource()
            {
                @Override
                public String getScriptAsString() throws IOException
                {
                    return sourceCode;
                }

                @Override
                public boolean isModified()
                {
                    return true;
                }

                @Override
                public String suggestedClassName()
                {
                    return "GroovyMessenger";
                }
            };
            Object result = new GroovyScriptFactory("classpath").getScriptedObject(scriptSource, Messenger.class);
            modelAndView.addObject("output", ((Messenger) result).getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex);
            modelAndView.addObject("error", true);
            modelAndView.addObject("output", ex.getMessage());
        }


        return modelAndView;
    }
}
