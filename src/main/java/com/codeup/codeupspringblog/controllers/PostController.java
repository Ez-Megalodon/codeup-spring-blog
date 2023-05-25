package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository adsDao;

    public PostController(PostRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", adsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String showPosts(Model model) {
        model.addAttribute("posts", adsDao.findAll());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createAdForm(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postAdForm(@RequestParam(name = "title")String title, @RequestParam(name = "body") String body){
        Post post = new Post(title, body);
        adsDao.save(post);
        return "redirect:/posts";
    }
}

