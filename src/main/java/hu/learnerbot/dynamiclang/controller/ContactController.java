package hu.learnerbot.dynamiclang.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactController
{
    private static final String VIEW_PATH_CONTACT = "pages/contact";

    @Value("${learnerbot.contact.link:https://github.com/learnerbot}")
    private String contactLink;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView support()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_CONTACT);
        modelAndView.addObject("contactLink", contactLink);
        return modelAndView;
    }
}
