package com.doigrales.fergie.repositories;

import com.doigrales.fergie.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

