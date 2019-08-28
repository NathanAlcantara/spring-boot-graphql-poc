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

import java.util.List;
import java.util.UUID;

@Component
class Mutation implements GraphQLMutationResolver {

    @Autowired
    private NoteService noteService;
    @Autowired
    private AuthorService authorService;

    public Author createAuthor(final InputAuthor author) {
        return authorService.createAuthor(author.getName(), author.getEmail());
    }

    public Note createNote(final String note, final UUID authorId) {
        return noteService.createNote(note, authorId);
    }

    
    public ChangeAuthorPayload changeAuthorName(final ChangeAuthorNameInput authorNameInput) {
        return authorService.changeAuthorName(authorNameInput.getId(), authorNameInput.getName());
    }

    public ChangeAuthorPayload changeAuthorEmail(final ChangeAuthorEmailInput authorEmailInput) {
        return authorService.changeAuthorEmail(authorEmailInput.getId(), authorEmailInput.getEmail());
    }

    public ChangeNotePayload changeNote(final ChangeNoteInput noteInput) {
        return noteService.changeNote(noteInput.getId(), noteInput.getNote());
    }

    public DeleteAuthorPayload deleteAuthor(final UUID id) {
        return authorService.deleteById(id);
    }

    public DeleteAuthorNotesPayload deleteAuthorNotes(final UUID authorId) {
        return authorService.deleteAuthorNotes(authorId);
    }

    public DeleteNotePayload deleteNote(final UUID id) {
        return noteService.deleteById(id);
    }

    // Bulk
    public List<Author> createAuthorBulk(final List<InputAuthor> authors) {
        return authorService.createAuthorBulk(authors);
    }

    public List<Note> createNoteBulk(final List<String> notes, final UUID authorId) {
        return noteService.createNoteBulk(notes, authorId);
    }

    public List<ChangeAuthorPayload> changeAuthorNameBulk(final List<ChangeAuthorNameInput> authorsNameInput) {
        return authorService.changeAuthorNameBulk(authorsNameInput);
    }

    public List<ChangeAuthorPayload> changeAuthorEmailBulk(final List<ChangeAuthorEmailInput> authorsEmailInput) {
        return authorService.changeAuthorEmailBulk(authorsEmailInput);
    }

    public List<ChangeNotePayload> changeNoteBulk(final List<ChangeNoteInput> notesInput) {
        return noteService.changeNoteBulk(notesInput);
    }

    public List<DeleteAuthorPayload> deleteAuthorBulk(final List<UUID> ids) {
        return authorService.deleteAllById(ids);
    }

    public List<DeleteAuthorNotesPayload> deleteAuthorNotesBulk(final List<UUID> authorsId) {
        return authorService.deleteAuthorNotesBulk(authorsId);
    }

    public List<DeleteNotePayload> deleteNoteBulk(final List<UUID> ids) {
        return noteService.deleteAllById(ids);
    }
}
