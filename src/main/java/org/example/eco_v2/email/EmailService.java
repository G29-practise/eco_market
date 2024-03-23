package org.example.eco_v2.email;


import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.exceptions.IncorrectEmail;
import org.example.eco_v2.common.exceptions.IncorrectPassword;
import org.example.eco_v2.common.exceptions.TimeOut;
import org.example.eco_v2.email.dto.EmailDto;
import org.example.eco_v2.user.UserDtoMapper;
import org.example.eco_v2.user.UserRepository;
import org.example.eco_v2.user.dto.UserCreateDto;
import org.example.eco_v2.user.entity.User;
import org.example.eco_v2.user.role.RoleRepository;
import org.example.eco_v2.user.role.entity.Role;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.example.eco_v2.common.ExcMessage.*;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final RedisTemplate<String, EmailDto> redisTemplate;
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleRepository roleRepository;

    public String verifyEmail(EmailDto emailEntity) {
        EmailDto email = redisTemplate.opsForValue().get(emailEntity.getEmail());
        if (email != null) {
            String checkEmail = email.getUserCreateDto().getEmail();
            if (checkEmail.equals(email.getEmail())) {
                if (email.getMessage().equals(emailEntity.getMessage())) {
                    UserCreateDto createDto = email.getUserCreateDto();
                    User user = userDtoMapper.toEntity(createDto);
                    user.setId(UUID.randomUUID());
                    Set<Role> roles = Collections.singleton(roleRepository.findByName("USER").orElseThrow());
                    user.setRoles(roles);
                    user.setVerify(true);
                    userRepository.save(user);
                    return SUCCESSFULLY_VERIFICATION;
                }
                throw new IncorrectPassword(INCORRECT_EMAIL_VER);
            }
            throw new IncorrectEmail(INCORRECT_EMAIL);
        }
        throw new TimeOut(TIME_OUT);

    }


    public void retry(EmailDto emailDto) {

    }
}
