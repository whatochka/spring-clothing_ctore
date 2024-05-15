package com.springeshop.repositories;

import com.springeshop.data.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Bucket, Long> {

}
