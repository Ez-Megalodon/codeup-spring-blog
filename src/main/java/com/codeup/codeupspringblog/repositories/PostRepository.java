package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostById(Long id);
    Post findPostByTitle(String title);

    @Transactional
    @Modifying
    @Query("update Post p set p.title = :title, p.body = :body where p.id = :id")
    int setPostById(@Param("id") Long id, @Param("title")String title, @Param("body")String body);

    @Query("from Post a where a.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);


}
