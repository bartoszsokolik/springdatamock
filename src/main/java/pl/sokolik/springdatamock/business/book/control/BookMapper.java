package pl.sokolik.springdatamock.business.book.control;

import pl.sokolik.springdatamock.business.book.boundary.dto.BookDto;
import pl.sokolik.springdatamock.business.book.model.Book;

class BookMapper {

    Book map(Long id, BookDto bookDto) {
        return Book.builder()
                .id(id)
                .isbn(bookDto.getIsbn())
                .title(bookDto.getTitle())
                .publisher(bookDto.getPublisher())
                .year(bookDto.getYear())
                .version(bookDto.getVersion())
                .build();
    }

    BookDto map(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .publisher(book.getPublisher())
                .year(book.getYear())
                .createdDate(book.getCreatedDate())
                .createdBy(book.getCreatedBy())
                .version(book.getVersion())
                .build();
    }
}
