package com.hello.world.graphqlsdl.repository;

import com.hello.world.graphqlsdl.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    void deleteByIdIn(final List<UUID> ids);
}
