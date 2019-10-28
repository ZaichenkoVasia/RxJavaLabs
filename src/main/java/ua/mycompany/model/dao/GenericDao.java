package ua.mycompany.model.dao;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;

public abstract class GenericDao {
    protected Bucket entries = CouchbaseCluster.create("127.0.0.1")
            .authenticate("root", "root")
            .openBucket("users");
}
