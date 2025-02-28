package com.cavidanrahmanov.depoti.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    private String name;
    private Long parentId;
}
