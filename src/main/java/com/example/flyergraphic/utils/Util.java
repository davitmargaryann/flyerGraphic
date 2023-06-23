package com.example.flyergraphic.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Util {

    public String upload(MultipartFile image, String uploadDir) throws IOException {
        String name = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File imageM = new File(uploadDir, name);
        image.transferTo(imageM);
        return name;
    }

}
