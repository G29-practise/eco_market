package org.example.eco_v2.wishlist;
import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.wishlist.entity.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WishlistRepository extends GenericRepository<Wishlist, UUID> {

}
