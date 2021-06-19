package com.project.rocketlunch.repository;

import com.project.rocketlunch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findAllByEmailAndPassword(String email, String password);

    @Query(value="UPDATE USER SET username = :#{#data.username}, password = :#{#data.password} WHERE id=:#{#data.id}", nativeQuery = true)
    void update(@Param("data") User data);
}
