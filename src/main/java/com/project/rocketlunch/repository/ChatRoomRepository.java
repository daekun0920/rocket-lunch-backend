package com.project.rocketlunch.repository;

import com.project.rocketlunch.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query(value = "INSERT INTO CHAT_ROOM (init_flag, post_id, receiver_id, sender_id, post_id) VALUES (:#{#data.initFlag}, :#{#data.post.id}, :#{#data.receiver.id}, :#{#data.sender.id}, :#{#data.post.id})", nativeQuery = true)
    void insertJoinRoom(@Param("data") ChatRoom chatRoom);

    @Query(value = "SELECT a.id, a.sender_id, a.receiver_id, a.post_id, a.message, a.init_flag, b.city, b.food, b.time, c.username FROM CHAT_ROOM a INNER JOIN POST b ON a.post_id = b.id INNER JOIN USER c ON c.id = b.user_id WHERE a.sender_id = :id AND a.receiver_id = :id ORDER BY a.id DESC", nativeQuery = true)
    List<ChatRoom> getRooms(@Param("id") Integer id);
}
