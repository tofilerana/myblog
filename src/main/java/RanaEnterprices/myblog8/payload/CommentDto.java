package RanaEnterprices.myblog8.payload;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String body;
    private String email;
    private String name;
    private Long postId;


}