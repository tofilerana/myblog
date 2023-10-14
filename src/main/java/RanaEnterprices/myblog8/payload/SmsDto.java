package RanaEnterprices.myblog8.payload;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SmsDto {


    private String toPhoneNumber;


    private String messageText;

    // Getters and setters...
}