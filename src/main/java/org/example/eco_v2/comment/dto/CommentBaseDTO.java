package org.example.eco_v2.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentBaseDTO {
    private String commit;
    private UUID user_id;
}
