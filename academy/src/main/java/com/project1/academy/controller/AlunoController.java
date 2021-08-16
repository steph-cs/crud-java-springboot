
package com.project1.academy.controller;

import javax.validation.Valid;

import com.project1.academy.model.Aluno;
import com.project1.academy.repository.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController

public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/listAlunos")
    public ModelAndView listAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/listAlunos");
        mv.addObject("alunos", alunoRepository.findAll() );
        return mv;
    }

    @GetMapping("/addAluno")
    public ModelAndView register(Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/addAluno");
        mv.addObject("aluno", aluno);
        return mv;
    }
    
    @PostMapping("/addAluno")
    public ModelAndView aluno(@Valid Aluno aluno, BindingResult br){
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("aluno/addAluno");
        }else{
            alunoRepository.save(aluno);
            mv.setViewName("redirect:/listAlunos");
        }
        
        return mv;
    }

    @GetMapping("/alterarAluno/{id}")
    public ModelAndView alterar( @PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("aluno/alterarAluno");
        Aluno aluno = alunoRepository.getById(id);
        mv.addObject("aluno", aluno);
        return mv;
    }

    @PostMapping("/alterarAluno")
    public ModelAndView alterar(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if(br.hasErrors()){
            mv.setViewName("aluno/addAluno");
        }else{
            mv.setViewName("redirect:/listAlunos");
            alunoRepository.save(aluno);
        }
        return mv;
    }

    @PostMapping("/excluir/{id}")
    public ModelAndView excluir( @PathVariable("id") Integer id) {
        alunoRepository.deleteById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/listAlunos");
        return mv;
    }
    
}
