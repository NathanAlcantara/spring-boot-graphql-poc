package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class DeleteNotesPayload {
    @NotNull
    private Boolean sucess;

    public Boolean getSucess() {
        return sucess;
    }

    public void setSucess(Boolean sucess) {
        this.sucess = sucess;
    }
}
