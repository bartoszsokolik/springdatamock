package pl.sokolik.springdatamock.business.book.boundary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.sokolik.springdatamock.system.model.ApiError;

@Getter
@AllArgsConstructor
public enum BookError implements ApiError {

    BOOK_NOT_FOUND(404, "Book not found");

    private final Integer code;
    private final String message;
}
