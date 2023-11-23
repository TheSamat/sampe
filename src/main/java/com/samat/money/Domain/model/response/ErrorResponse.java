
package com.samat.money.Domain.model.response;

import com.samat.money.Domain.constant.ErrorCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    UUID id;
    ErrorCode code;
    String message;
}
