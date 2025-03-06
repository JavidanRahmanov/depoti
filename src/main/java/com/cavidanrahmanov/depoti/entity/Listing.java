package com.cavidanrahmanov.depoti.entity;

import com.cavidanrahmanov.depoti.security.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "listing")
@Setter
@Getter
@RequiredArgsConstructor
public class Listing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String title;
    private String description;
    private double price;

    @ManyToMany(mappedBy = "favoriteListings")
    private Set<Users> favoritedBy = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Users seller;

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    private String listingNumber;
    private String normalizedTitle;
    private String normalizedDescription;
    private String normalizedCategory;

    public void generateListingNumber() {
        this.listingNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
    }

    @Column(length = 2000) // Store file paths as text (comma-separated)
    private String imagePaths;

    private boolean isExpired = false;
    private LocalDateTime createdAt;
    private LocalDateTime expiryDate;


    @PreUpdate
    public void setNormalizedFields() {
        this.normalizedTitle = normalize(title);
        this.normalizedDescription = normalize(description);
        this.normalizedCategory = normalize(category.getName());
    }

    private String normalize(String input) {
        if (input == null) return null;
        String lower = input.toLowerCase();
        lower = lower.replace("ə", "e")
                .replace("ç", "c")
                .replace("ş", "s")
                .replace("ğ", "g")
                .replace("ı", "i");
        return lower;
    }

    @PrePersist
    public void prePersist(){
        onCreate();
        generateListingNumber();
        setNormalizedFields();
    }
    private void onCreate() {
        this.createdAt = LocalDateTime.now();
        // Məsələn, pulsuz elan üçün 10 günlük aktivlik
        this.expiryDate = this.createdAt.plusDays(10);
    }

    @Column(nullable = false)
    private int viewCount = 0;

    public void incrementViewCount() {
        this.viewCount++;
    }
}
