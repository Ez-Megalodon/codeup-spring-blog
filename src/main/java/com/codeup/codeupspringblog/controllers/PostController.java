package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final CommentRepository commentsDao;
    private final EmailService emailService;

    public PostController(PostRepository adsDao, UserRepository usersDao, CommentRepository commentsDao, EmailService emailService) {
        this.postsDao = adsDao;
        this.usersDao = usersDao;
        this.commentsDao = commentsDao;
        this.emailService = emailService;

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
        emailService.prepareAndSend(post, "A new Post has been Posted!", "The body of the email! " + "\n" + post.getTitle() + "\n" + post.getBody());
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

        return "redirect:/posts/" + id;
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
        model.addAttribute("comments", commentsDao.findAllByPostId(id));
        model.addAttribute("post", postsDao.findPostById(id));
        return "/posts/post_page";
    }

    @PostMapping("/posts/delete")
    public String deletePostById(@RequestParam("postDelete") Long id) {
        System.out.println("deleting post id:" + id);
        postsDao.deleteById(id);
        return "/posts/index";
    }

    @PostMapping("/posts/addComment")
    public String addComment(@RequestParam("postId") Long id, @RequestParam("commentContent") String commentContent) {
        Comment newComment = new Comment(commentContent, postsDao.findPostById(id));
        commentsDao.save(newComment);
        return "redirect:/posts/" + id;
    }
}

