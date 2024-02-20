package com.project.book.repository;

import com.project.book.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain,Integer> {
}
