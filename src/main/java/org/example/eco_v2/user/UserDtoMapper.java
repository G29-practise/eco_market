package org.example.eco_v2.user;

import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.mapper.GenericMapper;
import org.example.eco_v2.user.dto.UserCreateDto;
import org.example.eco_v2.user.dto.UserResponseDto;
import org.example.eco_v2.user.dto.UserUpdateDto;
import org.example.eco_v2.user.entity.User;
import org.example.eco_v2.user.permission.entity.Permission;
import org.example.eco_v2.user.role.RoleDtoMapper;
import org.example.eco_v2.user.role.dto.RoleResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class UserDtoMapper extends GenericMapper<User, UserCreateDto, UserResponseDto, UserUpdateDto> {
    private final ModelMapper mapper;
    private final RoleDtoMapper roleDtoMapper;
    @Override
    public User toEntity(UserCreateDto userCreatedDto) {

        return mapper.map(userCreatedDto,User.class);
    }

    @Override
    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);

        Set<RoleResponseDto> roles = user
                .getRoles()
                .stream()
                .map(roleDtoMapper::toResponseDto)
                .collect(Collectors.toSet());

        if (user.getPermissions()!=null) {
            Set<String> permissions = user
                    .getPermissions()
                    .stream()
                    .map(Permission::getName)
                    .collect(Collectors.toSet());

            responseDto.setPermissions(permissions);
        }
        responseDto.setRoles(roles);
        return responseDto;
    }

    public UserResponseDto userResponseDto(UserCreateDto createDto){
       return mapper.map(createDto, UserResponseDto.class);
    }

    @Override
    public void toEntity(UserUpdateDto userUpdateDto, User user) {
        mapper.map(userUpdateDto,user);

    }
}
