package com.cranesteam.partylist.Controllers;

import com.cranesteam.partylist.Domain.User;
import com.cranesteam.partylist.Services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Main Controller
 *
 * @author ilyaivankin
 * @see Controller
 */
@Slf4j
@Controller
public class MainController {

    private UserServices userService;

    public MainController(UserServices userService) {
        this.userService = userService;
    }

    /**
     * signin view
     * @return model and view
     */
    @RequestMapping(value={"/", "/signin"}, method = RequestMethod.GET)
    public ModelAndView signin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }

    /**
     * password recovery view
     *
     * @return model and view
     */
    @RequestMapping(value={"/passwordrecovery"}, method = RequestMethod.GET)
    public ModelAndView passwordRecovery(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("passwordrecovery");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user); // fixme
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    // todo: registration
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.loadUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }


    // todo: home page
    @RequestMapping(value="/views/parties", method = RequestMethod.GET)
    public ModelAndView home() {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.loadUserByUsername(auth.getName());

        // auth log
        // todo: logger to table
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
        log.info("user auth -> " + user.getUsername() + " " + timeStamp);
        // end

        modelAndView.addObject("userName", "Welcome " + user.getUsername());
        modelAndView.addObject("adminMessage","welcome");
        modelAndView.setViewName("views/parties");
        return modelAndView;
    }


}
