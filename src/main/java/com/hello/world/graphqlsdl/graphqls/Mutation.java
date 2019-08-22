package com.hello.world.graphqlsdl.graphqls;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.ChangeAuthorEmailInput;
import com.hello.world.graphqlsdl.model.ChangeAuthorNameInput;
import com.hello.world.graphqlsdl.model.ChangeAuthorPayload;
import com.hello.world.graphqlsdl.model.ChangeNoteInput;
import com.hello.world.graphqlsdl.model.ChangeNotePayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorNotesPayload;
import com.hello.world.graphqlsdl.model.DeleteAuthorPayload;
import com.hello.world.graphqlsdl.model.DeleteNotePayload;
import com.hello.world.graphqlsdl.model.InputAuthor;
import com.hello.world.graphqlsdl.model.Note;
import com.hello.world.graphqlsdl.service.AuthorService;
import com.hello.world.graphqlsdl.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private NoteService noteService;
    @Autowired
    private AuthorService authorService;

    @Transactional
    public Author createAuthor(final InputAuthor author) {
        return authorService.createAuthor(author.getName(), author.getEmail());
    }

    @Transactional
    public Note createNote(final String note, final UUID authorId) {
        return noteService.createNote(note, authorId);
    }

    @Transactional
    public ChangeAuthorPayload changeAuthorName(final ChangeAuthorNameInput authorNameInput) {
        return authorService.changeAuthorName(authorNameInput.getId(), authorNameInput.getName());
    }

    @Transactional
    public ChangeAuthorPayload changeAuthorEmail(final ChangeAuthorEmailInput authorEmailInput) {
        return authorService.changeAuthorEmail(authorEmailInput.getId(), authorEmailInput.getEmail());
    }

    @Transactional
    public ChangeNotePayload changeNote(final ChangeNoteInput noteInput) {
        return noteService.changeNote(noteInput.getId(), noteInput.getNote());
    }

    @Transactional
    public DeleteAuthorPayload deleteAuthor(final UUID id) {
        return authorService.deleteById(id);
    }

    @Transactional
    public DeleteAuthorNotesPayload deleteAuthorNotes(final UUID authorId) {
        return authorService.deleteAuthorNotes(authorId);
    }

    @Transactional
    public DeleteNotePayload deleteNote(final UUID id) {
        return noteService.deleteById(id);
    }

    // Bulk
    @Transactional
    public List<Author> createAuthorBulk(final List<InputAuthor> authors) {
        return authorService.createAuthorBulk(authors);
    }

    @Transactional
    public List<Note> createNoteBulk(final List<String> notes, final UUID authorId) {
        return noteService.createNoteBulk(notes, authorId);
    }

    @Transactional
    public List<ChangeAuthorPayload> changeAuthorNameBulk(final List<ChangeAuthorNameInput> authorsNameInput) {
        return authorService.changeAuthorNameBulk(authorsNameInput);
    }

    @Transactional
    public List<ChangeAuthorPayload> changeAuthorEmailBulk(final List<ChangeAuthorEmailInput> authorsEmailInput) {
        return authorService.changeAuthorEmailBulk(authorsEmailInput);
    }

    @Transactional
    public List<ChangeNotePayload> changeNoteBulk(final List<ChangeNoteInput> notesInput) {
        return noteService.changeNoteBulk(notesInput);
    }

    @Transactional
    public List<DeleteAuthorPayload> deleteAuthorBulk(final List<UUID> ids) {
        return authorService.deleteById(id);
    }

    @Transactional
    public List<DeleteAuthorNotesPayload> deleteAuthorNotesBulk(final List<UUID> authorsId) {
        return authorService.deleteAuthorNotes(authorId);
    }

    @Transactional
    public List<DeleteNotePayload> deleteNoteBulk(final List<UUID> ids) {
        return noteService.deleteById(id);
    }
}
