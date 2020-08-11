package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.exceptions.RequestInvalidException;
import ar.edu.itba.paw.interfaces.services.HelpService;
import ar.edu.itba.paw.webapp.form.HelpStatusForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final HelpService helpService;

    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GetMapping("/helpList")
    public ModelAndView help(@ModelAttribute("getHelpForm") HelpStatusForm helpForm) {
        final ModelAndView mav = new ModelAndView("helpRequests");
        LOGGER.debug("Request attempted to get the list of help requests.");
        mav.addObject("updated", false);
        mav.addObject("helpList", helpService.getAllRequestsThatRequireAction());
        return mav;
    }

    @PostMapping("/updateHelpStep")
    public ModelAndView updateHelpStep(@ModelAttribute("getHelpForm") HelpStatusForm helpForm) throws RequestInvalidException {
        final ModelAndView mav = new ModelAndView("helpRequests");
        LOGGER.debug("Attempted to update status on help request.");
        if(helpService.updateStatus(helpForm.getHelpId(), helpForm.getStatus())) {
            mav.addObject("updated", true);
            mav.addObject("helpList", helpService.getAllRequestsThatRequireAction());
            return mav;
        }
        throw new RequestInvalidException();
    }
}
