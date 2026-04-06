package com.lawrence.ordermanagement.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private boolean success;
    private Object message;

}
