package com.example.flyergraphic.client.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String website;

    private String profilePic;

    private String personalLogo;

    private String companyLogo;

    private String licenseNumber;

}
