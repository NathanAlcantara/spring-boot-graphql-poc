package com.hello.world.graphqlsdl.repository;

import com.hello.world.graphqlsdl.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

    void deleteByIdIn(List<UUID> ids);
}
