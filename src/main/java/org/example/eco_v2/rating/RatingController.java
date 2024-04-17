package org.example.eco_v2.rating;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.eco_v2.common.App;
import org.example.eco_v2.rating.dto.RatingCreateDto;
import org.example.eco_v2.rating.dto.RatingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RequestMapping(App.BASE_PATH + RatingController.BATH_URL)
@RestController
@RequiredArgsConstructor
public class RatingController {
    public static final String BATH_URL = "/rating";

    private final RatingService ratingService;

    @PreAuthorize("hasAnyAuthority('rating:create')")
    @PostMapping("/create")
    public ResponseEntity<RatingResponseDto> create(@RequestBody @Valid RatingCreateDto createDto) throws IOException {
        RatingResponseDto ratingResponseDto = ratingService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('rating:read')")
    @GetMapping("/getAll")
    public ResponseEntity<Page<RatingResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<RatingResponseDto> all = ratingService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }

    @PreAuthorize("hasAnyAuthority('rating:read')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<RatingResponseDto> get(@PathVariable UUID id) {
        RatingResponseDto ratingResponseDto = ratingService.get(id);
        return ResponseEntity.ok(ratingResponseDto);
    }
}
