package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {

    private final AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adsDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/create")
    public String createAdForm() {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String postAdForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Ad ad = new Ad(title, body);
        adsDao.save(ad);
        return "redirect:/ads";
    }
}
