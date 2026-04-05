package com.lawrence.ordermanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private boolean success;
    private Object message ;

}
