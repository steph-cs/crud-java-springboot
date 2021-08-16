
package com.project1.academy.controller;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.project1.academy.Exceptions.ServiceExc;
import com.project1.academy.model.Usuario;
import com.project1.academy.service.ServiceUsuario;
import com.project1.academy.util.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class UsuarioController {

    @Autowired
    private ServiceUsuario serviceUsuario;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/login");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid Usuario usuario, BindingResult br, HttpSession session) throws NoSuchAlgorithmException, ServiceExc{
        ModelAndView mv = new ModelAndView();
        if(br.hasFieldErrors("email") || br.hasFieldErrors("senha")){
            mv.setViewName("usuario/login");
        }else{
            Usuario userLogin = serviceUsuario.loginUser(usuario.getEmail(),Util.md5(usuario.getSenha()) );
            if(userLogin == null){
                mv.addObject("msg", "Usuário não encontrado. Tente novamente.");
                mv.setViewName("usuario/login");
            }else{
                session.setAttribute("usuarioLogado", userLogin);
                mv.setViewName("redirect:/");
            }
        }
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Usuario usuario) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/cadastro");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    
    @PostMapping("/AddUsuario")
    public ModelAndView usuario(@Valid Usuario usuario, BindingResult br) throws Exception{
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("usuario/cadastro");
        }else{
            String msg = serviceUsuario.salvarUsuario(usuario);
            if (msg == null){
                mv.setViewName("redirect:/");
            }else{
                mv.addObject("msg",msg);
                mv.setViewName("usuario/cadastro");
            }
        }
        
        return mv;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return login();
    }
}
