package com.my.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Administrator on 2017/2/10.
 */
@Controller
public class ExceptionController {
    public String ex(Model model,Exception e){
        model.addAttribute("err", e);
        return "error";
    }
}
