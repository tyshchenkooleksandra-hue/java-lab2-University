package ua.model;

import java.util.UUID;

public class BaseEntity {
    protected final String id;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'';
    }
}
