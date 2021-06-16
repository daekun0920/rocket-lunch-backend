package com.project.rocketlunch.repository;

import com.project.rocketlunch.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @SuppressWarnings("SqlResolve")
    @Query(value="SELECT a.id, a.food, a.time, a.city, a.user_id, b.username FROM POST a INNER JOIN USER b ON a.user_id = b.id", nativeQuery = true)
    List<Post> findAllByOrderByIdDesc();
}
