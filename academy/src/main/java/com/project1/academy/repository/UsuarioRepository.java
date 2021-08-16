package com.project1.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.project1.academy.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);

    @Query("select j from Usuario j where j.email = :email and j.senha = :senha")
    public Usuario buscarLogin(String email, String senha);

}
