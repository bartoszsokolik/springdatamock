package pl.sokolik.springdatamock.business.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest;
import pl.sokolik.springdatamock.system.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

import static pl.sokolik.springdatamock.business.book.model.Book.TABLE_NAME;

@Entity
@Getter
@Table(name = TABLE_NAME)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Book extends BaseEntity {

    static final String TABLE_NAME = "books";

    private String title;

    private Integer year;

    private String publisher;

    private String isbn;

    public static Book create(CreateBookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .year(request.getYear())
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .build();
    }

}
