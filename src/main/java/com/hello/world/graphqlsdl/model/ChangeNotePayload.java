package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class ChangeNotePayload {
    @NotNull
    private boolean success;
    @NotNull
    private Note note;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }
}
