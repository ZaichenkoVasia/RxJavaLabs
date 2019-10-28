package ua.mycompany.model.dao;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import ua.mycompany.model.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DownloadDao extends GenericDao {

    private static final String SELECT_COUNT_FROM_ENTRIES = "SELECT count(*) FROM users";
    private static final String SELECT_FROM_ENTRIES_LIMIT_1_OFFSET_$_1 = "SELECT * FROM users LIMIT 1 OFFSET $1";

    public synchronized Integer getCountEntries(){
        N1qlQueryResult result = this.entries.query(N1qlQuery.simple(SELECT_COUNT_FROM_ENTRIES));
        N1qlQueryRow row = result.allRows().get(0);
        JsonObject json = row.value();
        return (int)json.get("$1");
    }

    public UserEntity getEntryForRXJava(int offset){
        String query = SELECT_FROM_ENTRIES_LIMIT_1_OFFSET_$_1;
        N1qlQueryResult result = this.entries.query(N1qlQuery.parameterized(query, JsonArray.from(offset)));
        if(result.allRows() == null && result.allRows().isEmpty())
            return null;
        JsonObject json = (JsonObject) result.allRows().get(0).value().get("users");
        return new UserEntity(json.getString("email"), json.getString("password"));
    }
}
