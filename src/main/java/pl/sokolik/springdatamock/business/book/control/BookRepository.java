package pl.sokolik.springdatamock.business.book.control;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sokolik.springdatamock.business.book.model.Book;

import java.util.UUID;

interface BookRepository extends JpaRepository<Book, Long> {
}
