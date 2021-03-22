package pl.sokolik.springdatamock.business.book.control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sokolik.springdatamock.business.book.boundary.BookOperation;

@Configuration
class BookConfiguration {

    @Bean
    BookOperation bookOperation(BookRepository bookRepository) {
        return new DefaultBookOperation(new BookMapper(), bookRepository);
    }
}
