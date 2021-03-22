package pl.sokolik.springdatamock.business.book.boundary;

import io.vavr.collection.List;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sokolik.springdatamock.business.book.boundary.dto.ApiResponse;
import pl.sokolik.springdatamock.business.book.boundary.dto.BookDto;
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest;
import pl.sokolik.springdatamock.system.model.ApiError;
import pl.sokolik.springdatamock.system.web.ResponseResolver;

import static org.springframework.http.HttpStatus.CREATED;
import static pl.sokolik.springdatamock.business.book.boundary.BookResource.BOOK_API_URI;
import static pl.sokolik.springdatamock.system.Endpoint.API_ROOT;

@RestController
@RequiredArgsConstructor
@RequestMapping(BOOK_API_URI)
class BookResource {

    static final String BOOK_API_URI = API_ROOT + "/books";

    private final BookOperation bookOperation;

    @GetMapping
    ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(bookOperation.findAllBooks());
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<BookDto>> get(@PathVariable Long id) {
        return ResponseResolver.resolveOk(bookOperation.find(id));
    }

    @PostMapping
    ResponseEntity<BookDto> create(@RequestBody CreateBookRequest request) {
        return new ResponseEntity<>(bookOperation.create(request), CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<BookDto>> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return ResponseResolver.resolveOk(bookOperation.update(id, bookDto));
    }
}
