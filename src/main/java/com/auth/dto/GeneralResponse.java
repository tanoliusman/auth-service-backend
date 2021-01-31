package com.auth.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse {

    private boolean result;
    private String resultDetail;
}
