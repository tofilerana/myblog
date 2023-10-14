package RanaEnterprices.myblog8.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
// All the status code are present inside HttpStatus. And HttpStatus is an ENUM.
// and as we know that we can only choose whatever is present inside that ENUM.
public class ResourceNotFoundException extends RuntimeException{
    // now we have to do message building.
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        // in order to give a message to my users we will use SUPER KEYWORD.
        super (String.format("%s not found with %s : '%s'" , resourceName,fieldName,fieldValue));

        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
// These will act as setters.
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }
}
