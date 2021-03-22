package pl.sokolik.springdatamock.business.book.boundary.dto;

import io.vavr.collection.List;
import lombok.Value;
import pl.sokolik.springdatamock.system.model.ApiError;

@Value
public class ApiResponse<T> {

    private final List<ApiError> error;
    private final T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(null, data);
    }
    public static ApiResponse failure(List<ApiError> errors) {
        return new ApiResponse(errors, null);
    }

    public static ApiResponse failure(ApiError error) {
        return new ApiResponse(List.of(error), null);
    }

}
