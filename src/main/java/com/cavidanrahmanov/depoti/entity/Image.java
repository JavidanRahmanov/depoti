//package com.cavidanrahmanov.depoti.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "images")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Lob  // Büyük verileri saklamak için
//    private byte[] imageData;
//
//    private String imageType; // Örn: image/png, image/jpeg
//
//    @ManyToOne
//    @JoinColumn(name = "listing_id", nullable = false)
//    private Listing listing;
//}
//
