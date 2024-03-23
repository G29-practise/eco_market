package org.example.eco_v2.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.eco_v2.cart.dto.CartResponseDtoForUser;
import org.example.eco_v2.order.dto.OrderResponseDtoForUser;
import org.example.eco_v2.user.role.dto.RoleResponseDto;
import org.example.eco_v2.wishlist.dto.WishlistResponseDtoForUser;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto extends UserBaseDto{
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Set<RoleResponseDto> roles;
    private Set<String> permissions;
    private CartResponseDtoForUser cart;
    private WishlistResponseDtoForUser wishlist;
    private Set<OrderResponseDtoForUser> orders;
}
