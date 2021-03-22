package pl.sokolik.springdatamock.business.book.boundary.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class BookDto {

    private final Long id;
    private final String title;
    private final String publisher;
    private final String isbn;
    private final Integer year;
    private final LocalDateTime createdDate;
    private final String createdBy;
    private final Integer version;
}
