package pl.sokolik.springdatamock.business.book.control;

import io.vavr.Function2;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookValidationError;
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest;

import java.util.Objects;
import java.util.regex.Pattern;

import static pl.sokolik.springdatamock.business.book.boundary.dto.BookValidationError.EMPTY_ISBN;
import static pl.sokolik.springdatamock.business.book.boundary.dto.BookValidationError.INVALID_ISBN;

class BookValidator {

    private static final Pattern ISBN_PATTERN = Pattern.compile("^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$");

    public Either<Seq<BookValidationError>, CreateBookRequest> validate(final CreateBookRequest request) {
        final var title = request.getTitle();
        final var isbn = request.getIsbn();

        return Validation.combine(validateField(title, BookValidationError.EMPTY_TITLE), validateIsbn(isbn))
                .ap(Function2.constant(request))
                .toEither();
    }

    private Validation<BookValidationError, String> validateField(String value, BookValidationError error) {
        return Objects.nonNull(value) ? Validation.valid(value) : Validation.invalid(error);
    }

    private Validation<BookValidationError, String> validateIsbn(String isbn) {
        if (Objects.isNull(isbn)) return Validation.invalid(EMPTY_ISBN);

        return ISBN_PATTERN.matcher(isbn).matches() ? Validation.valid(isbn) : Validation.invalid(INVALID_ISBN);
    }
}
