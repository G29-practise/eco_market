package org.example.eco_v2.rating;

import org.example.eco_v2.common.repository.GenericRepository;
import org.example.eco_v2.rating.entity.Rating;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends GenericRepository<Rating, UUID> {
}
