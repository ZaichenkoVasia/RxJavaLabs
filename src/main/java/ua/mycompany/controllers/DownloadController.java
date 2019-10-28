package ua.mycompany.controllers;

import ua.mycompany.model.services.ServiceDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/download")
public class DownloadController {
    @Autowired
    private ServiceDownload serviceDownload;

    @GetMapping("/common")
    public Map<String, ?> getWithRXJava(){
        Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("data", serviceDownload.getAllEntriesWithRXJava());
        return modelMap;
    }
}
