package com.example.flyergraphic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SaveClientRequestDto {

    private UUID id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String website;

    private String licenseNumber;

    private MultipartFile profilePic;

    private MultipartFile personalLogo;

    private MultipartFile companyLogo;

}
