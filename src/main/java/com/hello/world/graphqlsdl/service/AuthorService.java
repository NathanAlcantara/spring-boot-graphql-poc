package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.ChangeAuthorEmailInput;
import com.hello.world.graphqlsdl.model.ChangeAuthorNameInput;
import com.hello.world.graphqlsdl.model.ChangeAuthorPayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorNotesPayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorPayload;
import com.hello.world.graphqlsdl.model.InputAuthor;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
import com.hello.world.graphqlsdl.repository.NoteRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        final Author author = authorRepository.findById(id).orElseThrow();
        author.setName(name);
        authorRepository.save(author);

        final ChangeAuthorPayload output = new ChangeAuthorPayload();
        output.setAuthor(author);
        output.setSuccess(true);

        return output;
    }

    public ChangeAuthorPayload changeAuthorEmail(UUID id, String email) {
        final Author author = authorRepository.findById(id).orElseThrow();
        author.setEmail(email);
        authorRepository.save(author);

        final ChangeAuthorPayload output = new ChangeAuthorPayload();
        output.setAuthor(author);
        output.setSuccess(author.getId() != null);

        return output;
    }

    public DeleteAuthorPayload deleteById(UUID id) {
        authorRepository.deleteById(id);

        final boolean exist = authorRepository.existsById(id);

        final DeleteAuthorPayload output = new DeleteAuthorPayload();
        output.setSuccess(!exist);

        return output;
    }

    public DeleteAuthorNotesPayload deleteAuthorNotes(UUID authorId) {
        final Author author = authorRepository.findById(authorId).orElseThrow();

        noteRepository.deleteInBatch(author.getNotes());

        final boolean emptyNotes = author.getNotes().isEmpty();

        final DeleteAuthorNotesPayload output = new DeleteAuthorNotesPayload();
        output.setSuccess(emptyNotes);
        output.setAuthor(author);

        return output;
    }

    public List<Author> createAuthorBulk(List<InputAuthor> authors) {
        final List<Author> authorList = new ArrayList<>();

        authors.forEach(author -> {
            final Author authorEntity = new Author();
            authorEntity.setName(author.getName());
            authorEntity.setEmail(author.getEmail());

            authorList.add(authorEntity);
        });

        return authorRepository.saveAll(authorList);
    }

    public List<ChangeAuthorPayload> changeAuthorNameBulk(List<ChangeAuthorNameInput> authorsNameInput) {
        final List<ChangeAuthorPayload> outputList = new ArrayList<>();

        authorsNameInput.forEach(authorNameInput -> {
            final Author author = authorRepository.findById(authorNameInput.getId()).orElseThrow();

            author.setName(authorNameInput.getName());
            authorRepository.save(author);

            final ChangeAuthorPayload output = new ChangeAuthorPayload();
            output.setSuccess(author.getId() != null);
            output.setAuthor(author);

            outputList.add(output);
        });

        return outputList;
    }

    public List<ChangeAuthorPayload> changeAuthorEmailBulk(List<ChangeAuthorEmailInput> authorsEmailInput) {
        final List<ChangeAuthorPayload> outputList = new ArrayList<>();

        authorsEmailInput.forEach(authorEmailInput -> {
            final Author author = authorRepository.findById(authorEmailInput.getId()).orElseThrow();

            author.setName(authorEmailInput.getEmail());
            authorRepository.save(author);

            final ChangeAuthorPayload output = new ChangeAuthorPayload();
            output.setSuccess(author.getId() != null);
            output.setAuthor(author);

            outputList.add(output);
        });

        return outputList;
    }

    public List<DeleteAuthorPayload> deleteAllById(List<UUID> ids) {
        final List<DeleteAuthorPayload> outputList = new ArrayList<>();

        authorRepository.deleteByIdIn(ids);

        ids.forEach(id -> {
            final boolean exist = authorRepository.existsById(id);

            final DeleteAuthorPayload output = new DeleteAuthorPayload();
            output.setSuccess(!exist);

            outputList.add(output);
        });

        return outputList;
    }

    public List<DeleteAuthorNotesPayload> deleteAuthorNotesBulk(List<UUID> authorsId) {
        final List<DeleteAuthorNotesPayload> outputList = new ArrayList<>();

        authorsId.forEach(authorId -> {
            final Author author = authorRepository.findById(authorId).orElseThrow();

            noteRepository.deleteInBatch(author.getNotes());

            final boolean emptyNotes = author.getNotes().isEmpty();

            final DeleteAuthorNotesPayload output = new DeleteAuthorNotesPayload();
            output.setSuccess(emptyNotes);
            output.setAuthor(author);

            outputList.add(output);
        });

        return outputList;
    }
}
