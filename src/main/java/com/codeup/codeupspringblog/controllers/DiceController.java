package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceController {
    @GetMapping("/roll-dice")
    public String rollDiceView() {
        return "roll-dice";
    }

    @PostMapping("/roll-dice")
    public String displayUserGuess(@RequestParam("userDiceGuess") String userDiceGuess, Model model) {
        model.addAttribute("userDiceGuess", userDiceGuess);
        int randomNum = (int) (Math.random() * 6 - 1 + 1) + 1;
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String displayUserGuessLink(@PathVariable String n, Model model) {
        model.addAttribute("n", Integer.parseInt(n));
        int randomNum = (int) (Math.random() * 6 - 1 + 1) + 1;
        model.addAttribute("randomNum", randomNum);
        return "roll-dice";
    }
}
