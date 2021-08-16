package com.project1.academy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomeController {
   
   @GetMapping("/")
   public ModelAndView index(){
       ModelAndView mv = new ModelAndView();
       mv.setViewName("home/index");
       return mv ;
   }
   
}

