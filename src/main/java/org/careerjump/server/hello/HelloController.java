package org.careerjump.server.hello;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/start/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<Void> hello() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("test")
    public HelloDto<Object> testHello(@RequestParam String test) {
        return HelloDto.builder()
                .data(test)
                .build();
    }
}
