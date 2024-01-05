package com.example.demo.Services;

import com.example.demo.DTO.ResquestChangePasswordDTO;
import com.example.demo.DTO.UserRequestDTO;
import com.example.demo.Enities.UserEnity;
import com.example.demo.Repositories.UserRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserService {
    private  final UserRepository userRepository;
    private JavaMailSender emailSender;
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

    public boolean ChangePassword(Integer userId,ResquestChangePasswordDTO userRequest){
        var userData = userRepository.findByUserId(userId);
        BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
        boolean isCheckPassword = BCrypt.matches(userRequest.getOldPassword(),userData);
        if(userData == null || !isCheckPassword){
            return false;
        }
        userRepository.updatePassword(userId,BCrypt.encode(userRequest.getNewPassword()));
        return true;
    }

    public boolean sendPassword(String email){
        Optional<String> isCheck = userRepository.findByEmail(email);
        if(isCheck.isEmpty()){
            return false;
        }
        try {
            String passwordNew = generatePassword();
            BCryptPasswordEncoder BCrypt = new BCryptPasswordEncoder();
            userRepository.forgotPassword(email,BCrypt.encode(passwordNew));
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("capstonevlu1204@gmail.com");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Hello");
            mimeMessageHelper.setText("Đây là mật khẩu của bạn : " + passwordNew + " Mật khẩu của bạn có thơ hạn là 30 ngày");
           emailSender.send(mimeMessage);
            return true;
        }catch (Exception exception){
            System.out.println(exception);
           return false;
        }
    }
    private String generatePassword(){
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i <= 16; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }
}
