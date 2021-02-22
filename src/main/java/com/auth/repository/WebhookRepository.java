package com.auth.repository;

import com.auth.dto.BookingWebHooksRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookRepository extends JpaRepository<BookingWebHooksRequest, Long> {
}
