package com.example.flyergraphic.client.service.impl;

import com.example.flyergraphic.client.models.Client;
import com.example.flyergraphic.client.repository.ClientRepository;
import com.example.flyergraphic.client.service.ClientService;
import com.example.flyergraphic.dto.SaveClientRequestDto;
import com.example.flyergraphic.utils.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final Util util;

    private final ModelMapper mapper;

    @Value("${key.uploadDir}")
    private String uploadDir;

    @Override
    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }



    @Override
    public void saveClientInformation(SaveClientRequestDto saveClientRequestDto) {
        Client client = mapper.map(saveClientRequestDto, Client.class);
        boolean isProfilePicChanged = false;
        boolean isPersonalLogoChanged = false;
        boolean isCompanyLogoChanged = false;

        if(saveClientRequestDto.getFullName() != null || !saveClientRequestDto.getFullName().trim().equals("")){
            client.setFullName(saveClientRequestDto.getFullName());
        }
        if(saveClientRequestDto.getFullName() != null || !saveClientRequestDto.getFullName().trim().equals("")){
            client.setEmail(saveClientRequestDto.getEmail());
        }
        if(saveClientRequestDto.getFullName() != null || !saveClientRequestDto.getFullName().trim().equals("")){
            client.setPhoneNumber(saveClientRequestDto.getPhoneNumber());
        }
        if(saveClientRequestDto.getFullName() != null || !saveClientRequestDto.getFullName().trim().equals("")){
            client.setWebsite(saveClientRequestDto.getWebsite());
        }
        if(saveClientRequestDto.getFullName() != null || !saveClientRequestDto.getFullName().trim().equals("")){
            client.setLicenseNumber(saveClientRequestDto.getLicenseNumber());
        }

        if (saveClientRequestDto.getProfilePic() != null && !saveClientRequestDto.getProfilePic().isEmpty()) {
            try {
                String upload = util.upload(saveClientRequestDto.getProfilePic(), uploadDir);
                client.setProfilePic(upload);
                isProfilePicChanged = true;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (saveClientRequestDto.getCompanyLogo() != null && !saveClientRequestDto.getCompanyLogo().isEmpty()) {
            try {
                String upload = util.upload(saveClientRequestDto.getCompanyLogo(), uploadDir);
                client.setCompanyLogo(upload);
                isCompanyLogoChanged = true;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (saveClientRequestDto.getPersonalLogo() != null && !saveClientRequestDto.getPersonalLogo().isEmpty()) {
            try {
                String upload = util.upload(saveClientRequestDto.getPersonalLogo(), uploadDir);
                client.setPersonalLogo(upload);
                isPersonalLogoChanged = true;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(saveClientRequestDto.getId() != null){
            client.setId(saveClientRequestDto.getId());
            Optional<Client> byId = clientRepository.findById(saveClientRequestDto.getId());
            if(byId.isPresent()){
                if(!isProfilePicChanged){
                    client.setProfilePic(byId.get().getProfilePic());
                }
                if(!isPersonalLogoChanged){
                    client.setPersonalLogo(byId.get().getPersonalLogo());
                }
                if(!isCompanyLogoChanged){
                    client.setCompanyLogo(byId.get().getCompanyLogo());
                }
            }
        }

        clientRepository.save(client);
    }

    @Override
    public void getClientDetails(ModelMap modelMap, String id) {
        Optional<Client> byId = clientRepository.findById(UUID.fromString(id));
        byId.ifPresent(client -> modelMap.addAttribute("client", client));
    }

    @Override
    public List<Client> findByNameContaining(String name) {
        return clientRepository.findByFullNameContaining(name);
    }

    @Override
    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }

}
