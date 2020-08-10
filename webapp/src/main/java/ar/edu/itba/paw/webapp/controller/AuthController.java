package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.models.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @RequestMapping("/403")
    public ModelAndView forbidden() {
        return new ModelAndView("403");
    }

    @RequestMapping("/")
    public String redirect(Authentication authentication) {
        String role = authentication.getAuthorities().toString();
        String home = "user";
        if (!role.contains(UserRole.CLIENT.toString())) {
            home = "rooms";
        }
        return "redirect:/" + home + "/home";
    }

    @RequestMapping("/index")
    public String redirectIndex(Authentication authentication) {
        return redirect(authentication);
    }
}
