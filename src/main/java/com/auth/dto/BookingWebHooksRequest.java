package com.auth.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "webhook")

public class BookingWebHooksRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sign;
    private String data;

    public BookingWebHooksRequest(String data, String sign){
        this.data = data;
        this.sign = sign;
    }



}
