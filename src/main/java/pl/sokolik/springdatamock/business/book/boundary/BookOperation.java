package pl.sokolik.springdatamock.business.book.boundary;

import io.vavr.collection.List;
import io.vavr.control.Either;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookDto;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookError;
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest;

public interface BookOperation {

    List<BookDto> findAllBooks();
    Either<BookError, BookDto> find(Long id);
    BookDto create(CreateBookRequest request);
    Either<BookError, BookDto> update(Long id, BookDto bookDto);
}
