package com.becafe.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String title;
    private String author;
    private String genre;
}
