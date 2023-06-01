package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;

    public PostController(PostRepository adsDao, UserRepository usersDao) {
        this.postsDao = adsDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/show")
    public String showPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createAdForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String postAdForm(@ModelAttribute Post post) {
        post.setUser(usersDao.findUserById(1L));
        postsDao.save(post);
        return "redirect:/posts/show";
    }

    @PostMapping("/posts/edit")
    public String editAdForm(
            @RequestParam("post-id") Long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body) {

        Post findPost = postsDao.findPostById(id);
        if (findPost != null) {
            postsDao.setPostById(id, title, body);
        } else {
            Post post = new Post(title, body);
            postsDao.save(post);
        }
        return "redirect:/posts/show";
    }

    @PostMapping("/posts/search")
    public String searchResults(
            @RequestParam("post-search") String title, Model model) {
        model.addAttribute("posts", postsDao.searchByTitleLike(title));
        return "posts/search";
    }

    @GetMapping("/posts/{id}")
    public String postPage(
            @PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postsDao.findPostById(id));
        return "/posts/post_page";
    }

    @PostMapping("/posts/delete")
    public String deletePostById (@RequestParam("postDelete")Long id){
        System.out.println("deleting post id:" + id);
        postsDao.deleteById(id);
        return "/posts/index";
    }
}

