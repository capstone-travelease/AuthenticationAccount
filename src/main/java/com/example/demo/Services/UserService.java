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
    public Map<String,String> SignUpService(UserRequestDTO UserRequest){
        HashMap<String,String> userReturn = new HashMap<>();
      var userData = userRepository.findByEmail(UserRequest.getEmail());
      if(userData.isPresent()){
           return userReturn;
       }
      var user = new UserEnity(
              null,
                UserRequest.getEmail(),
                UserRequest.getPhonenumber(),
                UserRequest.getName(),
                UserRequest.isGender(),
                new BCryptPasswordEncoder().encode(UserRequest.getEmail()),
                null,
                null,
                UserRequest.getRole()
        );
        userRepository.save(user);
        userReturn.put("email",UserRequest.getEmail());
        userReturn.put("phone", String.valueOf(UserRequest.getPhonenumber()));
        userReturn.put("name", UserRequest.getName());
        userReturn.put("gender",String.valueOf(UserRequest.isGender()));
        userReturn.put("role", String.valueOf(UserRequest.getRole()));
        return userReturn;
    }
}
