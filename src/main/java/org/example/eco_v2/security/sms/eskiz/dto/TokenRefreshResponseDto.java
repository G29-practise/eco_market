package org.example.eco_v2.security.sms.eskiz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshResponseDto {
    private String message;
    @JsonProperty("token_type")
    private String tokenType;
    private Map<String, String> data;
}
