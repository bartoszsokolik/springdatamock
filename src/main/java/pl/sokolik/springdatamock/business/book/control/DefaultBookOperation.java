package pl.sokolik.springdatamock.business.book.control;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.sokolik.springdatamock.business.book.boundary.BookOperation;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookDto;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookError;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookValidationError;
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest;
import pl.sokolik.springdatamock.business.book.model.Book;

import static pl.sokolik.springdatamock.business.book.boundary.dto.BookError.BOOK_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
class DefaultBookOperation implements BookOperation {

    private final BookValidator bookValidator;
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public List<BookDto> findAllBooks() {
        return List.ofAll(bookRepository.findAll()).map(bookMapper::map);
    }

    @Override
    public Either<BookError, BookDto> find(Long id) {
        log.info("Fetching book with id: {}", id);
        return Option.ofOptional(bookRepository.findById(id))
                .toEither(BOOK_NOT_FOUND)
                .map(bookMapper::map);
    }

    @Override
    @Transactional
    public BookDto create(CreateBookRequest request) {
        Either<Seq<BookValidationError>, BookDto> map = bookValidator.validate(request)
                .map(Book::create)
                .map(bookRepository::save)
                .peek(savedBook -> log.info("Created book with id: {}", savedBook.getId()))
                .map(bookMapper::map);

        return null;
    }

    @Override
    @Transactional
    public Either<BookError, BookDto> update(Long id, BookDto bookDto) {
        return Option.ofOptional(bookRepository.findById(id))
                .toEither(BOOK_NOT_FOUND)
                .map(book -> bookMapper.map(id, bookDto))
                .map(bookRepository::save)
                .peek(savedBook -> log.info("Updated book with id: {}", savedBook.getId()))
                .map(bookMapper::map);
    }
}
