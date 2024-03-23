package org.example.eco_v2.comment;

import org.example.eco_v2.comment.entity.Comment;
import org.example.eco_v2.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends GenericRepository<Comment, UUID> {
}
