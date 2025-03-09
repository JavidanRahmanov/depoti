package com.cavidanrahmanov.depoti.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Kateqoriya növü")
public enum CategoryType {
    @Schema(description = "Əsas kateqoriya", example = "HEAD_CATEGORY")
    HEAD_CATEGORY,

    @Schema(description = "Orta səviyyə kateqoriya", example = "MID_CATEGORY")
    MID_CATEGORY,

    @Schema(description = "Ən aşağı səviyyə kateqoriya", example = "SUB_CATEGORY")
    SUB_CATEGORY
}

