package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ChangeNoteInput {
    @NotNull
    private UUID id;
    @NotNull
    private String note;

    public String getName() {
        return note;
    }

    public void setName(String name) {
        this.note = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
