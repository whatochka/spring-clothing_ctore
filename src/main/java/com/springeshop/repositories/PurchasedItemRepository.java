package com.springeshop.repositories;

import com.springeshop.data.domain.PurchasedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Long> {
    void deleteById(@NonNull Long id);
}
