package com.example.flyergraphic.client.controller;

import com.example.flyergraphic.client.service.ClientService;
import com.example.flyergraphic.dto.SaveClientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.apache.commons.io.IOUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Value("${key.uploadDir}")
    private String uploadDir;

    @PostMapping("/client/save")
    public String saveClient(@ModelAttribute SaveClientRequestDto saveClientRequestDto){
        clientService.saveClientInformation(saveClientRequestDto);
        return "redirect:/";
    }

    @GetMapping(value = "/attachments", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/client/{id}")
    public String getClientDetails(@PathVariable String id,ModelMap modelMap){
        clientService.getClientDetails(modelMap,id);
        return "clientDetails";
    }

    @GetMapping("/client/save")
    public String saveClient(){
        return "saveClient";
    }

    @GetMapping("/client/edit/{id}")
    public String editClient(@PathVariable String id,ModelMap modelMap){
        clientService.getClientDetails(modelMap,id);
        return "editClient";
    }

    @GetMapping("/")
    public String getAllClients(ModelMap modelMap){
        modelMap.addAttribute("clients",clientService.findAll());
        return "allClients";
    }

    @GetMapping("/client/search")
    public String searchClients(@RequestParam String name,ModelMap modelMap){
        modelMap.addAttribute("clients",clientService.findByNameContaining(name));
        return "allClients";
    }

    @PostMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable String id){
        clientService.deleteById(UUID.fromString(id));
        return "redirect:/";
    }

    @GetMapping("/flyer")
    public String flyer(){
        return "flyer-creator-style8";
    }
}
