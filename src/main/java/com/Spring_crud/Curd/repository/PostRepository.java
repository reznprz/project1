package com.Spring_crud.Curd.repository;

import com.Spring_crud.Curd.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository  extends JpaRepository<Post, Long> {

}
