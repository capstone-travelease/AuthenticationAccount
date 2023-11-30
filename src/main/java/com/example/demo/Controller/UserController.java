package com.example.demo.Controller;


import com.example.demo.DTO.*;
import com.example.demo.Services.AuthenticationService;
import com.example.demo.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {
    private  final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/signup")
    public HashMap SignUp(@RequestBody @Valid UserRequestDTO request, HttpServletResponse response, BindingResult bindingResult){
        HashMap responeData = new HashMap<>();
        var userReponse = userService.SignUpService(request);
        if(userReponse.isEmpty()){
            response.setStatus(409);
            responeData.put("code",response.getStatus());
            responeData.put("data",userReponse);
            responeData.put("message","Email already exists");
            return responeData;
        }
        responeData.put("code",response.getStatus());
        responeData.put("data", userReponse);
        responeData.put("message","Seccessful");

        return responeData;
    }

    @PostMapping(path = "/login")
    public HashMap Login(@RequestBody @Valid AuthenticationRequestDTO request, HttpServletResponse response){
        var serviceData = authenticationService.authenticate(request);
        HashMap responeData = new HashMap<>();
        if(serviceData.isEmpty()){
            response.setStatus(401);
            responeData.put("code",response.getStatus());
            responeData.put("data", serviceData);
            responeData.put("message","Wrong password or email");
            return responeData;
        }
        responeData.put("code",response.getStatus());
        responeData.put("data", serviceData);
        responeData.put("message","Sucessful");
        return responeData;
    }
}
