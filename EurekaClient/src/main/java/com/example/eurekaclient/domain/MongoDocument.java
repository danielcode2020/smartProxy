package com.example.eurekaclient.domain;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
public abstract class MongoDocument {
    @Id
    public UUID uuid;
    public Instant lastUpdatedTime;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Instant lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
