package com.project.book.repository;

import com.project.book.entity.PublicationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<PublicationDetails,Integer> {
}
