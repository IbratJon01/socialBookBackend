package com.example.demo.Repository;

import com.example.demo.Entety.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
}