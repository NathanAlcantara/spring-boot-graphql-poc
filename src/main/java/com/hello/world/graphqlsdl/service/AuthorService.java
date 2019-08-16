package com.hello.world.graphqlsdl.service;

import com.hello.world.graphqlsdl.model.Author;
import com.hello.world.graphqlsdl.model.DeleteAuthorInput;
import com.hello.world.graphqlsdl.model.DeleteAuthorOutput;
import com.hello.world.graphqlsdl.repository.AuthorRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public DeleteAuthorOutput deleteById(DeleteAuthorInput input) {
        DeleteAuthorOutput output = new DeleteAuthorOutput();
        if (authorRepository.existsById(input.getId())) {
            authorRepository.deleteById(input.getId());
        }
        output.setSucesso(true);
        return output;
    }

    public Author createAuthor(final String name, final String email) {

        Author author = new Author();
        author.setName(name);
        author.setEmail(email);

        return authorRepository.save(author);
    }
}
