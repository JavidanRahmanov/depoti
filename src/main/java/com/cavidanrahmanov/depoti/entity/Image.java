package com.cavidanrahmanov.depoti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Image {

    @Id
    private int id;

    private String filename;

    @Column(name = "mime_type")
    private String mimeType;

    private byte[] data;
}
