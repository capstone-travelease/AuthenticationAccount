package com.example.demo.Enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Date;

@Table(name ="Individuals")
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
public class UserEnity implements UserDetails {

        @Id
        @Column(name = "user_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "email")
        private String email;

        @Column(name = "phone_number")
        private Integer phone;

        @Column(name = "full_name")
        private String fullname;

        @Column(name = "gender")
        private boolean gender;

        @Column(name = "password")
        private String password;

        @Column(name = "birthday")
        private Date birthday;

        @Column(name = "avatar")
        private String avatar;

        @Column(name = "role_id")
        private int role;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return email;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
