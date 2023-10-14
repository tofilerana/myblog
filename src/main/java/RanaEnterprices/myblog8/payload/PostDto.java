package RanaEnterprices.myblog8.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

// Instead of payload they might call that as DTO.
@Data
public class PostDto {
    // POSTDTO will go postman. and Service will go to database.
    private long id;

    @NotEmpty
    @Size(min = 2 , message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 3 , message = "Post description should have at least 10 characters")
    private String description;


    private String content;

}
