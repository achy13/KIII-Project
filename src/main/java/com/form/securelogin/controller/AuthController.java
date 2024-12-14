package com.form.securelogin.controller;

import com.form.securelogin.model.User;
import com.form.securelogin.model.UserDto;
import com.form.securelogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // user registration form submit request
    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String users(HttpServletRequest request, Model model){
        User user = (User) request.getSession().getAttribute("currentUser");
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("currentUser", user);
        return "users";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/delete/{email}")
    public String deleteUser(@PathVariable String email) {
        this.userService.deleteUserByEmail(email);
        return "redirect:/users";
    }

    @PostMapping("/users/updateRole/{userId}")
    public String updateRole(@PathVariable Long userId, @RequestParam String role) {
        userService.updateUserRole(userId, role);
        return "redirect:/users"; // Redirect to the user details page or any other appropriate page
    }



}