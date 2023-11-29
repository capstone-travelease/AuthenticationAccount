package com.example.demo.Enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "Roles")
@RequiredArgsConstructor
public class RoleEnity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "role_name")
    private String rolename;

}
