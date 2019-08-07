package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class InputNote {

    @NotNull
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
