package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);
    Post findPostByTitle(String title);

    @Query("from Post p where p.title like %:term%")
    Post findPostsByTitleLike(@Param("term")String term);

}
