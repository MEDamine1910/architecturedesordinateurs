package org.example.spring_security.service;

import org.example.spring_security.model.Note;
import org.example.spring_security.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceNote {
    @Autowired
    NoteRepository noteRepository;
    public List<Note> listeNote(){
        return noteRepository.findAll();
    }
    public List<Note> listeNoteParUser(String nom){
        return noteRepository.getNotesByUsername(nom);
    }
}
