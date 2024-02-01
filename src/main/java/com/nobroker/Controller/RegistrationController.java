package com.nobroker.Controller;

import com.nobroker.Entity.User;
import com.nobroker.Service.EmailService;
import com.nobroker.Service.EmailVerificationService;
import com.nobroker.Service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private EmailService emailService;
    private UserService userService;
    private EmailVerificationService emailVerificationService;

    public RegistrationController(EmailService emailService, UserService userService,EmailVerificationService emailVerficationService) {
        this.emailService = emailService;
        this.userService = userService;
        this.emailVerificationService=emailVerficationService;
    }

    //http://localhost:8080/api/register
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user){
        User  registerUser=userService.registerUser(user);
        Map<String, String> stringMap = emailService.sendOtpEmail(user.getEmail());
        return stringMap;
    }

//http://localhost:8080/api/otpVerification?email=&otp=
    @PostMapping("/otpVerification")
    public Map<String, String> veriftOtp(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtp(email,otp);
    }
}
