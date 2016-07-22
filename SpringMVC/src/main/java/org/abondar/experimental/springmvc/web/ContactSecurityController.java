package org.abondar.experimental.springmvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 * Created by abondar on 22.07.16.
 */
@Controller
@RequestMapping("/security")
public class ContactSecurityController {

    private Logger logger = LoggerFactory.getLogger(ContactSecurityController.class);

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("loginfail")
    public String loginFail(Model uiModel, Locale locale){
        logger.info("Login fail detected");
        uiModel.addAttribute("message",new Message("error",
                messageSource.getMessage("message_login_fail",new Object[]{},locale)));

        return "contacts/list";
    }


}
