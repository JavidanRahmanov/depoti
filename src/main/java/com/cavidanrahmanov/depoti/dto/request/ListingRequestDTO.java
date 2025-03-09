package com.cavidanrahmanov.depoti.dto.request;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.CategoryType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingRequestDTO {

    private String title;
    private String description;
    private double price;

    @Schema(description = "Kateqoriya növü", example = "MID_CATEGORY")
    private CategoryType categoryType;
    private boolean isDelivery;
    private String cityName;

    @Email
    @NotNull
    private String email;
    private String userName;

    @Pattern(regexp = "^[0-9]+$", message = "Mobil nömrə yalnız reqəmlərdən ibarət olmalıdır")
    private String phoneNumber;

    private String imageName;
    private String imageType;
    private byte[] imageDate;

    private Long categoryId;
}
