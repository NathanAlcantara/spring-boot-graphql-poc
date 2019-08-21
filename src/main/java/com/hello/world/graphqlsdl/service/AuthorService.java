package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.ChangeAuthorPayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorNotesPayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorPayload;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
import com.hello.world.graphqlsdl.repository.NoteRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private NoteRepository noteRepository;

    public Optional<Author> findById(UUID id) {
        final Author author = authorRepository.findById(id).orElseThrow();
        Hibernate.initialize(author.getNotes());

        return Optional.of(author);
    }

    public List<Author> findAll() {
        final List<Author> authorList = authorRepository.findAll();
        authorList.forEach(author -> Hibernate.initialize(author.getNotes()));

        return authorList;
    }

    public Author createAuthor(final String name, final String email) {

        final Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        return authorRepository.save(author);
    }

    public ChangeAuthorPayload changeAuthorName(UUID id, String name) {
        final ChangeAuthorPayload output = new ChangeAuthorPayload();

        final Author author = authorRepository.findById(id).orElseThrow();
        author.setName(name);
        authorRepository.save(author);

        output.setAuthor(author);
        output.setSuccess(true);

        return output;
    }

    public ChangeAuthorPayload changeAuthorEmail(UUID id, String email) {
        final ChangeAuthorPayload output = new ChangeAuthorPayload();

        final Author author = authorRepository.findById(id).orElseThrow();
        author.setEmail(email);
        authorRepository.save(author);

        output.setAuthor(author);
        output.setSuccess(true);

        return output;
    }

    public DeleteAuthorPayload deleteById(UUID id) {
        final DeleteAuthorPayload output = new DeleteAuthorPayload();

        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
        output.setSucess(true);

        return output;
    }

    public DeleteAuthorNotesPayload deleteAuthorNotes(UUID authorId) {
        final DeleteAuthorNotesPayload output = new DeleteAuthorNotesPayload();

        final Author author = authorRepository.findById(authorId).orElseThrow();

        noteRepository.deleteInBatch(author.getNotes());

        final boolean emptyNotes = author.getNotes().isEmpty();

        output.setSuccess(emptyNotes);
        output.setAuthor(author);

        return output;
    }
}
