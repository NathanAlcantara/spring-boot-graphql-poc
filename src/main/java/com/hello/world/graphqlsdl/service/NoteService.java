package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.ChangeNotePayload;
import com.hello.world.graphqlsdl.model.DeleteNotePayload;
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
    private NoteRepository noteRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Optional<Note> findById(UUID id) {
        return noteRepository.findById(id);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note createNote(final String note, final UUID authorId) {
        final Author author = authorRepository.findById(authorId).orElseThrow();

        final Note noteEntity = new Note();
        noteEntity.setNote(note);
        noteEntity.setCreatedOn(ZonedDateTime.now());

        author.addNote(noteEntity);
        noteRepository.save(noteEntity);

        return noteEntity;
    }

    public ChangeNotePayload changeNote(UUID id, String note) {
        final ChangeNotePayload output = new ChangeNotePayload();

        final Note noteEntity = noteRepository.findById(id).orElseThrow();
        noteEntity.setNote(note);
        noteRepository.save(noteEntity);

        output.setSuccess(true);
        output.setNote(noteEntity);

        return output;
    }

    public DeleteNotePayload deleteById(UUID id) {
        final DeleteNotePayload output = new DeleteNotePayload();

        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
        output.setSucess(true);

        return output;
    }
}
