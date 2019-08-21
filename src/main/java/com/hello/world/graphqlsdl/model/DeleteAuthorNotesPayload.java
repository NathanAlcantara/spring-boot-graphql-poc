package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class DeleteAuthorNotesPayload {
    @NotNull
    private Boolean success;
    @NotNull
    private Author author;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
