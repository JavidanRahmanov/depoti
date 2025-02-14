package com.cavidanrahmanov.depoti.entity;

import com.cavidanrahmanov.depoti.security.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "listing")
@Setter
@Getter
public class Listing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    private String imageUrl;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageDate;


}
