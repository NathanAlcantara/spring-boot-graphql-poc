package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ChangeAuthorEmailInput {
    @NotNull
    private UUID id;
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
