package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String postsIndex(){
        return "/posts/index";
    }

    @GetMapping("/posts/show")
    public String showPost(Model model){
        ArrayList<Post> postList = new ArrayList<>();
        Post newPost1 = new Post("title 1", "body 1");
        Post newPost2 = new Post("title 2", "body 2");
        postList.add(newPost1);
        postList.add(newPost2);
        model.addAttribute("postList", postList);
        return "/posts/show";
    }

    @GetMapping("/posts/{id}")
    public String userPostById(@PathVariable Integer id){
        ArrayList<Post> postList = new ArrayList<>();
        Post newPost1 = new Post("title 1", "body 1");
        postList.add(newPost1);
        return "/posts/show";
    }

    @GetMapping(path = "/posts/create")
    public String viewPosts(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPosts(@RequestParam("title") String title, @RequestParam("body") String body, Model model){
        Post newPost1 = new Post(title, body);
        model.addAttribute("addedPost", newPost1);
        return "posts/show";
    }
}

