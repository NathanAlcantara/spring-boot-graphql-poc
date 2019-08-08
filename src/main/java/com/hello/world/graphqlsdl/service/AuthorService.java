package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }

    @Transactional
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional
    public Author createAuthor(final String name, final String email) {

        Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        return authorRepository.save(author);
    }
}
