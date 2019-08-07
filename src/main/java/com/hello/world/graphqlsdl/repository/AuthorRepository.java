package com.hello.world.graphqlsdl.repository;

import com.hello.world.graphqlsdl.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
