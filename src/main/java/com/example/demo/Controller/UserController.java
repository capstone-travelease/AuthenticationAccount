package com.example.demo.Controller;


import com.example.demo.DTO.*;
import com.example.demo.Services.AuthenticationService;
import com.example.demo.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {
    private  final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(path = "/signup")
    public HashMap SignUp(@RequestBody @Valid UserRequestDTO request, HttpServletResponse response, BindingResult bindingResult){
        HashMap responseData = new HashMap<>();
        var userReponse = userService.SignUpService(request);
        if(userReponse.isEmpty()){
            response.setStatus(409);
            responseData.put("code",response.getStatus());
            responseData.put("message","Email already exists");
            return responseData;
        }
        responseData.put("code",response.getStatus());
        responseData.put("message","Ok");

        return responseData;
    }
    @PostMapping(path = "/login")
    public HashMap Login(@RequestBody @Valid AuthenticationRequestDTO request, HttpServletResponse response){
        var serviceData = authenticationService.authenticate(request);
        HashMap responseData = new HashMap<>();
        if(serviceData.isEmpty()){
            response.setStatus(401);
            responseData.put("code",response.getStatus());
            responseData.put("message","Wrong password or email");
            return responseData;
        }
        responseData.put("code",response.getStatus());
        responseData.put("data",serviceData);
        responseData.put("message","Ok");
        return responseData;
    }
}
