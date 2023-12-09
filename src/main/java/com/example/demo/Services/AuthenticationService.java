package com.example.demo.Services;

import com.example.demo.DTO.AuthenticationRequestDTO;
import com.example.demo.Repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository repository;

    public Map<String,Object> authenticate(@Valid AuthenticationRequestDTO request) {
        HashMap<String, Object> map = new HashMap<>();
        BCryptPasswordEncoder Bcryt = new BCryptPasswordEncoder();
        var idUser = repository.findByName(request.getEmail());
        boolean isCheck =  Bcryt.matches(request.getPassword(),  (String) idUser.get("password"));
        if(idUser.isEmpty() || !isCheck){
            return map;
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch (Exception ex){
            System.out.print(ex);
        }
        System.err.println(idUser.get("user_id"));
        var user = repository.findByUserName(request.getEmail()).orElseThrow();
        var jwtToken= jwtService.generateToken(user);
        map.put("token",jwtToken);
        map.put("idUser", idUser.get("user_id"));
        return map;
    }
}
