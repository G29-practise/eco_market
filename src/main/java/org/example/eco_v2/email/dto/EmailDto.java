package org.example.eco_v2.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.eco_v2.user.dto.UserCreateDto;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("Email")
public class EmailDto implements Serializable {

    @Email
    private String email;

    @NonNull
    private String message;

    private UserCreateDto userCreateDto;

}
