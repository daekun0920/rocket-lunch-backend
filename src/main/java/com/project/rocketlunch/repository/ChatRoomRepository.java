package com.project.rocketlunch.repository;

import com.project.rocketlunch.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
    @Query(value = "INSERT INTO CHAT_ROOM (init_flag, post_id, receiver_id, sender_id, post_id) VALUES (:#{#data.initFlag}, :#{#data.post.id}, :#{#data.receiver.id}, :#{#data.sender.id}, :#{#data.post.id})", nativeQuery = true)
    void insertJoinRoom(@Param("data") ChatRoom chatRoom);
}
