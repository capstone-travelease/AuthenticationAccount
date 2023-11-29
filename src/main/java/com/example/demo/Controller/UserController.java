package com.example.demo.Controller;


import com.example.demo.DTO.AuthenticationRequestDTO;
import com.example.demo.DTO.AuthenticationResponeDTO;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.DTO.UserResponeDTO;
import com.example.demo.Services.AuthenticationService;
import com.example.demo.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class UserController {

    private  final UserService userService;
    private final AuthenticationService authenticationService;
    @PostMapping(path = "/signup")
    public List<UserResponeDTO> SignUp(@RequestBody UserRequestDTO request, HttpServletResponse response){
         return List.of(
                 new UserResponeDTO(
                         response.getStatus(),
                         userService.SignUpService(request),
                         "Successful"
                 )
         );
    }

    @PostMapping(path = "/login")
    public List<AuthenticationResponeDTO> Login(@RequestBody AuthenticationRequestDTO request, HttpServletResponse response){
        return List.of(
                new AuthenticationResponeDTO(
                        response.getStatus(),authenticationService.authenticate(request),"Successfully"
                )
        );
    }
}
