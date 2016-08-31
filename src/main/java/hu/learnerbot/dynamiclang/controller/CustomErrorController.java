package hu.learnerbot.dynamiclang.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/error")
public class CustomErrorController implements ErrorController
{
    private static final String VIEW_PATH_ERROR = "pages/error";

    @RequestMapping(produces = "text/html")
    public ModelAndView onError(HttpServletRequest request, HttpServletResponse response)
    {
        return new ModelAndView(VIEW_PATH_ERROR);
    }

    @Override
    public String getErrorPath()
    {
        return VIEW_PATH_ERROR;
    }
}
