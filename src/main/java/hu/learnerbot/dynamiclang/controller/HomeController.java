package hu.learnerbot.dynamiclang.controller;

import hu.learnerbot.dynamiclang.scripting.ScriptExecutor;
import hu.learnerbot.dynamiclang.scripting.ScriptExecutorFactory;
import hu.learnerbot.dynamiclang.scripting.ScriptType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    private static final String VIEW_PATH_HOME = "pages/home";

    private final ScriptExecutorFactory scriptExecutorFactory;

    @Value("${learnerbot.default.script:}")
    private String defaultScript;

    @Autowired
    public HomeController(ScriptExecutorFactory scriptExecutorFactory) {
        this.scriptExecutorFactory = scriptExecutorFactory;
    }

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
            ScriptExecutor scriptExecutor = scriptExecutorFactory.create(ScriptType.GROOVY);
            Object result = scriptExecutor.execute(sourceCode);
            modelAndView.addObject("output", result);
        } catch (Exception ex) {
            LOGGER.error(String.format("Failed to execute code '%s'!", sourceCode), ex);
            modelAndView.addObject("error", true);
            modelAndView.addObject("output", ex.getMessage());
        }

        return modelAndView;
    }
}
