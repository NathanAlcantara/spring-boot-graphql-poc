package com.hello.world.graphqlsdl.graphqls;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.DeleteAuthorOutput;
import com.hello.world.graphqlsdl.model.Note;
import com.hello.world.graphqlsdl.service.AuthorService;
import com.hello.world.graphqlsdl.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private NoteService noteService;
    @Autowired
    private AuthorService authorService;

    @Transactional
    public Optional<Note> note(final UUID id) {
        return noteService.findById(id);
    }

    @Transactional
    public List<Note> notes() {
        return noteService.findAll();
    }

    @Transactional
    public Optional<Author> author(final UUID id) {
        return authorService.findById(id);
    }

    @Transactional
    public List<Author> authors() {
        return authorService.findAll();
    }
}