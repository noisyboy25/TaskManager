package com.taskcodee.server.repositoires;

import com.taskcodee.server.entities.BoardCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<BoardCard, Long> {
}
