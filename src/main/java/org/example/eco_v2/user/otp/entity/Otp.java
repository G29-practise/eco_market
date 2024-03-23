package org.example.eco_v2.user.otp.entity;

import jakarta.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eco_v2.user.dto.UserCreateDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "otp", timeToLive = 60 * 60 * 24)
@EntityListeners(AuditingEntityListener.class)
public class Otp {
    @Id
    private String email;
    private int code;
    private int sentCount;
    private LocalDateTime created;
    private LocalDateTime lastSentTime;
    private UserCreateDto userCreateDto;
}
