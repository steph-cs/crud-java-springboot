package com.project1.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project1.academy.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
     

}
