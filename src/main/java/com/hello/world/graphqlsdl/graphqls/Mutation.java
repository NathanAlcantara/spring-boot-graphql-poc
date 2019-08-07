package com.hello.world.graphqlsdl.graphqls;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.InputPerson;
import com.hello.world.graphqlsdl.model.Note;
import com.hello.world.graphqlsdl.service.AuthorService;
import com.hello.world.graphqlsdl.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    NoteService notesService;
    @Autowired
    AuthorService authorService;

    public Note addNote(final String note, final UUID authorId) {
        return notesService.createNote(note, authorId);
    }

    public Author addAuthor(final InputPerson author) {
        return authorService.createAuthor(author.getName(), author.getEmail());
    }
}
