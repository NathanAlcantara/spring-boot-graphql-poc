package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class ChangeAuthorPayload {
    @NotNull
    private boolean success;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    private Author author;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
