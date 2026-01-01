package org.example.spring_security.repository;

import org.example.spring_security.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    public List<Note> getNotesByUsername(String nom);
}