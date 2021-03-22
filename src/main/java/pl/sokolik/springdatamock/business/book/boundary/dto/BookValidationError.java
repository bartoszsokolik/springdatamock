package pl.sokolik.springdatamock.business.book.boundary.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.sokolik.springdatamock.system.model.ApiError;

@Getter
@RequiredArgsConstructor
public enum BookValidationError implements ApiError {

    EMPTY_TITLE(500, "Empty ISBN"),
    EMPTY_ISBN(500, "Empty ISBN"),
    INVALID_ISBN(500, "Invalid ISBN");

    private final Integer code;
    private final String message;
}
