package com.lawrence.ordermanagement.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {

    private boolean success;
    private Object message;

}
