package com.example.pstagram.repository.post;

import java.nio.FloatBuffer;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.pstagram.domain.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {


}
