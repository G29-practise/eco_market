package org.example.eco_v2.email;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.exceptions.IncorrectEmail;
import org.example.eco_v2.common.exceptions.IncorrectPassword;
import org.example.eco_v2.common.exceptions.TimeOut;
import org.example.eco_v2.email.dto.EmailRequestDto;
import org.example.eco_v2.user.NotificationService;
import org.example.eco_v2.user.UserDtoMapper;
import org.example.eco_v2.user.UserRepository;
import org.example.eco_v2.user.dto.UserCreateDto;
import org.example.eco_v2.user.dto.ValidateEmailRequestDto;
import org.example.eco_v2.user.entity.User;
import org.example.eco_v2.user.otp.OtpRepository;
import org.example.eco_v2.user.otp.entity.Otp;
import org.example.eco_v2.user.role.RoleRepository;
import org.example.eco_v2.user.role.entity.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.example.eco_v2.common.ExcMessage.*;


@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleRepository roleRepository;
    private final OtpRepository otpRepository;
    private final NotificationService notificationService;
    private final Random random = new Random();


    @Transactional
    public String verifyEmail(ValidateEmailRequestDto userDto) {

        Otp otp = otpRepository.findById(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        int otpCode = otp.getCode();
        Integer userCode = userDto.getCode();
        String userEmail = userDto.getEmail();

        UserCreateDto user = otp.getUserCreateDto();

        if (!(user == null)) {
            if (userEmail.equals(otp.getEmail())) {
                if (otpCode == userCode) {
                    User userEntity = userDtoMapper.toEntity(user);
                    userEntity.setId(UUID.randomUUID());
                    Set<Role> roles = Collections.singleton(roleRepository.findByName("USER").orElseThrow());
                    userEntity.setRoles(roles);
                    userEntity.setVerify(true);
                    userRepository.save(userEntity);
                    return SUCCESSFULLY_VERIFICATION;
                }
                throw new IncorrectPassword(INCORRECT_EMAIL_VER);
            }
            throw new IncorrectEmail(INCORRECT_EMAIL);
        }
        throw new TimeOut(TIME_OUT);

    }


    public void retry(EmailRequestDto dto) {
        String emailDto = dto.getEmail();

        Otp otp = otpRepository.findById(emailDto)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));

        int code = random.nextInt(1000, 10000);
        notificationService.sendVerifyCode(emailDto, code);

        otp.setCode(code);
        otpRepository.save(otp);
    }
}
