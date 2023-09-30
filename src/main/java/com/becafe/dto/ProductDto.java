package com.becafe.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    @NotEmpty(message = "{designation_not_empty}")
    private String designation;
}
