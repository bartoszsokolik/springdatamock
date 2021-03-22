package pl.sokolik.springdatamock.business.book.control

import pl.sokolik.springdatamock.business.book.BookSampleData
import pl.sokolik.springdatamock.business.book.boundary.BookOperation
import pl.sokolik.springdatamock.business.book.boundary.dto.CreateBookRequest
import spock.lang.Specification

import static pl.sokolik.springdatamock.business.book.boundary.dto.BookError.BOOK_NOT_FOUND
import static pl.sokolik.springdatamock.business.book.control.TestBookConfiguration.bookOperation
import static pl.sokolik.springdatamock.business.book.control.TestBookConfiguration.getTestBookRepository

class DefaultBookOperationUT extends Specification implements BookSampleData {

    BookOperation bookOperation = bookOperation()

    def cleanup() {
        testBookRepository.deleteAll()
    }

    def "should create book"() {
        given:
            def request = CreateBookRequest.builder()
                    .title(SAMPLE_TITLE)
                    .isbn(SAMPLE_ISBN)
                    .publisher(SAMPLE_PUBLISHER)
                    .year(SAMPLE_YEAR)
                    .build()

        when:
            def result = bookOperation.create(request)

        then:
            def expectedResult = SAMPLE_BOOK_DTO.toBuilder()
                .id(result.getId())
                .build()

            expectedResult == result
    }

    def "should find book with given id"() {
        given:
            def savedBook = testBookRepository.save(book())

        when:
            def result = bookOperation.find(SAMPLE_ID).get()

        then:
            savedBook.id == result.id
            savedBook.title == result.title
            savedBook.isbn == result.isbn
            savedBook.publisher == result.publisher
            savedBook.year == result.year
    }

    def "should return error when book with given is not present"() {
        when:
            def result = bookOperation.find(SAMPLE_ID).getLeft()

        then:
            BOOK_NOT_FOUND == result
    }

}
