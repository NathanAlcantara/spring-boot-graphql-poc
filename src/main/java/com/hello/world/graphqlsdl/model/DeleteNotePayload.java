package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class DeleteNotePayload {
    @NotNull
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
