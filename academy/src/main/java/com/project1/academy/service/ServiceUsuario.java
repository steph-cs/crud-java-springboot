package com.project1.academy.service;

import java.security.NoSuchAlgorithmException;


import com.project1.academy.Exceptions.CriptoExistsExeption;
import com.project1.academy.Exceptions.EmailExistsException;
import com.project1.academy.Exceptions.ServiceExc;
import com.project1.academy.model.Usuario;
import com.project1.academy.repository.UsuarioRepository;
import com.project1.academy.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceUsuario {
     
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String salvarUsuario(Usuario usuario) throws Exception{
        try {
            if(usuarioRepository.findByEmail(usuario.getEmail()) != null ){
                throw new EmailExistsException("Email ja cadastrado!");
            }
            usuario.setSenha(Util.md5(usuario.getSenha()));
            usuarioRepository.save(usuario);
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new CriptoExistsExeption("Erro na criptografia de senha!");
        } catch (EmailExistsException e){
            return (e.getMessage());
        }
        
    }

    public Usuario loginUser(String email, String senha) throws ServiceExc{
        Usuario userlogin = usuarioRepository.buscarLogin(email, senha);
        return userlogin;
    }
}
