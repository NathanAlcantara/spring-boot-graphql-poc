package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DeleteAuthorInput {
    @NotNull
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
