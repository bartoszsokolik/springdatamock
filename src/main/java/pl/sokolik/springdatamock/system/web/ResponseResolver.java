package pl.sokolik.springdatamock.system.web;

import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.sokolik.springdatamock.business.book.boundary.dto.ApiResponse;
import pl.sokolik.springdatamock.system.model.ApiError;

import static org.springframework.http.HttpStatus.*;

public class ResponseResolver {

    public static <T> ResponseEntity<ApiResponse<T>> resolveOk(Either<? extends ApiError, T> either) {
        return either.fold(ResponseResolver::mapError, book -> ResponseEntity.ok(ApiResponse.success(book)));
    }

    public static <T> ResponseEntity<ApiResponse<T>> resolveCreated(Either<? extends ApiError, T> either) {
        return either.fold(
                ResponseResolver::mapError,
                book -> ResponseEntity.status(CREATED)
                        .body(ApiResponse.success(book))
        );
    }

    private static ResponseEntity mapError(ApiError error) {
        return ResponseEntity.status(error.getCode())
                .body(ApiResponse.failure(error));
    }
}
