package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex(){
        return "posts index page";
    }


    @GetMapping("/posts/{id}")
    @ResponseBody
    public String userPostById(@PathVariable Integer id){
        return "view an individual post id: " + id;
    }

    @GetMapping(path = "/posts/create")
    @ResponseBody
    public String viewPosts(){
        return "view post form";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPosts(){
        return "create a new post";
    }
}

