package ua.mycompany.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mycompany.model.entity.UserEntity;
import ua.mycompany.model.services.ServiceUpload;

import java.util.*;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private ServiceUpload serviceUpload;

    @GetMapping("/common")
    public Map<String, ?> addEntries(@RequestParam("amount") int amount){
        Map<String, Object> modelMap = new HashMap<>();
            List<UserEntity> entries = new ArrayList<>(amount);
            UUID uuid;
            for(int i = 0; i < amount; i++){
                uuid = UUID.randomUUID();
                entries.add(new UserEntity(uuid.toString(), uuid.toString()));
            }
            serviceUpload.addEntries(entries);
        return modelMap;
    }
}
