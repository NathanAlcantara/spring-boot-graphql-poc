package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.Note;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
import com.hello.world.graphqlsdl.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    AuthorRepository authorRepository;

    public Note createNote(final String note, final UUID authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow();

        Note noteEntity = new Note();
        noteEntity.setNote(note);
        noteEntity.setAuthor(author);
        noteEntity.setCreatedOn(ZonedDateTime.now());

        return noteRepository.save(noteEntity);
    }

    public Optional<Note> findById(UUID id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}
