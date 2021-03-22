package pl.sokolik.springdatamock.business.book

import pl.sokolik.springdatamock.business.book.boundary.dto.BookDto
import pl.sokolik.springdatamock.business.book.model.Book

trait BookSampleData {

    Long SAMPLE_ID = 1L
    String SAMPLE_TITLE = "Sample title"
    String SAMPLE_ISBN = "Sample isbn"
    String SAMPLE_PUBLISHER = "Sample publisher"
    Integer SAMPLE_YEAR = 2020

    Book book(Map<String, Object> params = [:]) {
        return Book.builder()
            .id(params['id'] as Long ?: SAMPLE_ID)
            .title(params['title'] as String ?: SAMPLE_TITLE)
            .isbn(params['isbn'] as String ?: SAMPLE_ISBN)
            .publisher(params['publisher'] as String ?: SAMPLE_PUBLISHER)
            .year(params['year'] as Integer ?: SAMPLE_YEAR)
            .build()
    }

    BookDto SAMPLE_BOOK_DTO = BookDto.builder()
        .id(SAMPLE_ID)
        .title(SAMPLE_TITLE)
        .isbn(SAMPLE_ISBN)
        .publisher(SAMPLE_PUBLISHER)
        .year(SAMPLE_YEAR)
        .build()
}