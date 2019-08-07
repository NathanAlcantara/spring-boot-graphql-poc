package com.hello.world.graphqlsdl.model;

import javax.validation.constraints.NotNull;

public class InputPerson {

    @NotNull
    private String name;
    @NotNull
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
