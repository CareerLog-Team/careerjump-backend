package org.careerjump.server.response.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.careerjump.server.response.ResponseCode;
import org.careerjump.server.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private String message;
    private String code;

    public ResponseDto() {
        this.code = ResponseCode.SUCCESS;
        this.message = ResponseMessage.SUCCESS;
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto responseBody = new ResponseDto(
                ResponseCode.DATABASE_ERROR,
                ResponseMessage.DATABASE_ERROR
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseBody);
    }

    public static ResponseEntity<ResponseDto> validationFail() {
        ResponseDto responseBody = new ResponseDto(
                ResponseCode.VALIDATION_FAIL,
                ResponseMessage.VALIDATION_FAIL
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseBody);
    }

}
