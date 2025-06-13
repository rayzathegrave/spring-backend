package com.example.springbackend.repository;

import com.example.springbackend.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReadingRepository extends JpaRepository<Reading, LocalDateTime> {}

