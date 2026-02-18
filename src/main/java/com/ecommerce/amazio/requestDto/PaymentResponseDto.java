package com.ecommerce.amazio.requestDto;

import com.ecommerce.amazio.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    PaymentStatus paymentStatus;
    String paymentMethod;
    String transactionId;
    double amountPaid;
}
