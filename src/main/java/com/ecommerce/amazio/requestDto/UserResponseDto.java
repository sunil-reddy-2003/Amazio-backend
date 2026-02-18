package com.ecommerce.amazio.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private UUID userId;
    private String email;
    private String fname;
    private String lname;
    private long mobile;
}
