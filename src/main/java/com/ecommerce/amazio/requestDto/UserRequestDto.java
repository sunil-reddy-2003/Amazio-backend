package com.ecommerce.amazio.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String fName;
    private String lName;
    private String email;
    private long mobile;
}
