package com.example.demo.Services;

import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.Enities.UserEnity;
import com.example.demo.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserService {

    private  final UserRepository userRepository;
    public Map<String,String> SignUpService(UserRequestDTO userRequest){
        HashMap<String,String> userReturn = new HashMap<>();
      var userData = userRepository.findByEmail(userRequest.getEmail());
      if(userData.isPresent()){
           return userReturn;
       }
      var user = new UserEnity(
              null,
                userRequest.getEmail(),
                userRequest.getPhonenumber(),
                userRequest.getName(),
                userRequest.isGender(),
                new BCryptPasswordEncoder().encode(userRequest.getPassword()),
                null,
                null,
               3
        );
        userRepository.save(user);
        userReturn.put("email",userRequest.getEmail());
        userReturn.put("phone", String.valueOf(userRequest.getPhonenumber()));
        userReturn.put("name", userRequest.getName());
        userReturn.put("gender",String.valueOf(userRequest.isGender()));
        return userReturn;
    }
}
