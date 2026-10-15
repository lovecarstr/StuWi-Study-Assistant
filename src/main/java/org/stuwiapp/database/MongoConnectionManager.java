package org.stuwiapp.database;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConnectionManager {
    private static MongoClient mongoClient;

    private static void createConnection() {
        String connectionString = System.getenv("MONGODB_URI");
        if (connectionString == null || connectionString.isBlank()) {
            throw new IllegalStateException("Set the MONGODB_URI environment variable to your MongoDB connection string");
        }
        ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            createConnection();
        }
        return mongoClient;
    }
}