package com.cavidanrahmanov.depoti.dto.response;

import com.cavidanrahmanov.depoti.entity.Category;
import com.cavidanrahmanov.depoti.entity.CategoryType;
import com.cavidanrahmanov.depoti.security.model.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class ListingResponseDTO {

    private Long id;
    private String title;
    private String description;
    private double price;
    @Schema(description = "Kateqoriya növü", example = "MID_CATEGORY")
    private CategoryType categoryType;
    private boolean isDelivery;
    private String cityName;
    @Pattern(regexp = "^[0-9]+$", message = "Mobil nömrə yalnız reqəmlərdən ibarət olmalıdır")
    private String email;
    private String userName;
    private String phoneNumber;
    private List<MultipartFile> images;
    private String imageName;
    private String imageType;
    private byte[] imageDate;
}
