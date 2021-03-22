package pl.sokolik.springdatamock.business.book.boundary.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class CreateBookRequest {

    private final String title;
    private final String isbn;
    private final Integer year;
    private final String publisher;

}
