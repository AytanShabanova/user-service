package com.example.userserviceapidesign.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.QueryCacheLayout;

import java.time.LocalDate;
@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class User {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userId;
    private String email;
    private String number;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String gender;
    private String country;
    private String city;
    private String profileImageUrl;
    private String coverImageUrl;


}
