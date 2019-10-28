package ua.mycompany.model.dao;

import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import ua.mycompany.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class UploadDao extends GenericDao {
    public void addEntry(UserEntity entry){
        Map<String, Object> json = new HashMap<>();
        json.put("email", entry.getEmail());
        json.put("password", entry.getPassword());
        JsonDocument document = JsonDocument.create(UUID.randomUUID().toString(), JsonObject.from(json));
        this.entries.insert(document);
    }
}
