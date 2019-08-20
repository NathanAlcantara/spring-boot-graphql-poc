package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.*;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
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

    public Optional<Author> findById(UUID id) {
        Optional<Author> author = authorRepository.findById(id);
        Hibernate.initialize(author.get().getNotes());
        return author;
    }

    public List<Author> findAll() {
        List<Author> authorList = authorRepository.findAll();
        authorList.forEach(author -> {Hibernate.initialize(author.getNotes());});
        return authorList;
    }

    public DeleteAuthorPayload deleteById(UUID id) {
        DeleteAuthorPayload output = new DeleteAuthorPayload();
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        }
        output.setSucess(true);
        return output;
    }

    public Author createAuthor(final String name, final String email) {

        Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        return authorRepository.save(author);
    }

    public ChangeAuthorPayload updateAuthor(ChangeAuthorInput input) {
        ChangeAuthorPayload payload = new ChangeAuthorPayload();

        Optional<Author> author = findById(input.getId());
        if (author.isPresent()) {
            payload.setAuthor(author.get());
            payload.setSuccess(true);
        } else {
            payload.setSuccess(false);
        }
        return payload;
    }
}
