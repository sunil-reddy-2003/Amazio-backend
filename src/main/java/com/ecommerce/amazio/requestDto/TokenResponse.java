package com.ecommerce.amazio.requestDto;

import com.ecommerce.amazio.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    String token;
    UserResponseDto userInfo;
}
