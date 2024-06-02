package org.careerjump.server.hello;

import lombok.Builder;
import lombok.Data;

@Data
public class HelloDto<T> {
    private T data;

    @Builder
    public HelloDto(T data) {
        this.data = data;
    }
}
