package org.careerjump.server.response;

public interface ResponseMessage {

    String SUCCESS = "Success";

    String VALIDATION_FAIL = "Validation Failed";
    String DUPLICATE_ID = "Duplicate Id";

    String SIGN_IN_FAIL = "Login information Mismatch";
    String CERTIFICATION_FAIL = "Certification Failed";

    String DATABASE_ERROR = "Database Error";

}
