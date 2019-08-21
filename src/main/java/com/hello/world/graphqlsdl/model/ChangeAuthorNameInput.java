package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ChangeAuthorNameInput {
    @NotNull
    private UUID id;
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
