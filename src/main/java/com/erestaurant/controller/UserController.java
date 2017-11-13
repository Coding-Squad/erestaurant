package com.erestaurant.controller;

import com.erestaurant.model.UserRole;
import com.erestaurant.model.Users;
import com.erestaurant.repository.RoleRepository;
import com.erestaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by Programmer on 30-Oct-17.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @RequestMapping("/")
    public String home (){
        return "home";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Users user = new Users();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Users user, Set<UserRole> userRoles, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Users userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            for (UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            userService.createUser(user, userRoles);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Users());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
}
