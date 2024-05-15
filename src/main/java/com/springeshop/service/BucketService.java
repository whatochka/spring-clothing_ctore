package com.springeshop.service;

import com.springeshop.data.domain.Bucket;
import com.springeshop.data.domain.Product;
import com.springeshop.data.domain.PurchasedItem;
import com.springeshop.data.domain.User;
import com.springeshop.exceptions.ProductNotFoundException;
import com.springeshop.repositories.BucketRepository;
import com.springeshop.repositories.ProductRepository;
import com.springeshop.repositories.PurchasedItemRepository;
import com.springeshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchasedItemRepository purchasedItemRepository;

    public void addItemToBucket(Long productId, String username) {
        User user = userRepository.findFirstByName(username);
        if (user != null) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            Bucket bucket = user.getBucket();
            if (bucket == null) {
                bucket = new Bucket();
                bucket.setUser(user);
                user.setBucket(bucket);
            }
            List<PurchasedItem> purchasedItems = bucket.getPurchasedItems();
            if (purchasedItems == null) {
                purchasedItems = new ArrayList<>();
            } else {
                Optional<PurchasedItem> existingItem = purchasedItems.stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst();
                if (existingItem.isPresent()) {
                    PurchasedItem item = existingItem.get();
                    int newQuantity = item.getAmount() + 1;
                    item.setAmount(newQuantity);
                    bucketRepository.save(bucket);
                    return;
                }
            }
            PurchasedItem purchasedItem = new PurchasedItem();
            purchasedItem.setBucket(bucket);
            purchasedItem.setProduct(product);
            purchasedItem.setAmount(1);
            purchasedItems.add(purchasedItem);
            bucket.setPurchasedItems(purchasedItems);
            bucketRepository.save(bucket);
        }
    }

    public void removeItemFromBucket(Long itemId) {
        Optional<PurchasedItem> itemOptional = purchasedItemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            PurchasedItem item = itemOptional.get();
            int currentQuantity = item.getAmount();
            if (currentQuantity > 1) {
                item.setAmount(currentQuantity - 1);
                purchasedItemRepository.save(item);
            } else {
                purchasedItemRepository.deleteById(itemId);
            }
        }
    }

    public List<PurchasedItem> getAll(String username) {
        User user = userRepository.findFirstByName(username);
        if (user != null) {
            Bucket bucket = user.getBucket();
            if (bucket != null) {
                return bucket.getPurchasedItems();
            }
        }
        return null;
    }
}
