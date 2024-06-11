package com.codegym.model.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequestDto {
    private String code;
    private String description;
    private String image;
    private BigDecimal price;
    private boolean status;
    private Long type;

}
