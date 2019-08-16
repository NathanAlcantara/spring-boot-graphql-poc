package com.hello.world.graphqlsdl.graphqls;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.graphqlsdl.model.*;
import com.hello.world.graphqlsdl.service.AuthorService;
import com.hello.world.graphqlsdl.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private NoteService notesService;
    @Autowired
    private AuthorService authorService;

    @Transactional
    public Note addNote(final String note, final UUID authorId) {
        return notesService.createNote(note, authorId);
    }

    @Transactional
    public Author addAuthor(final InputPerson author) {
        return authorService.createAuthor(author.getName(), author.getEmail());
    }

    @Transactional
    public DeleteAuthorOutput deleteAuthor(final DeleteAuthorInput input) {
        return authorService.deleteById(input);
    }
}
