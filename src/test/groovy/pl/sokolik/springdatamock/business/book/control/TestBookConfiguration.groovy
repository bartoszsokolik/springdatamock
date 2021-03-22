package pl.sokolik.springdatamock.business.book.control


import com.mmnaseri.utils.spring.data.domain.impl.key.SequentialLongKeyGenerator
import com.mmnaseri.utils.spring.data.dsl.factory.RepositoryFactoryBuilder
import pl.sokolik.springdatamock.business.book.boundary.BookOperation

class TestBookConfiguration {

    static BookOperation bookOperation() {
        return new BookConfiguration().bookOperation(testBookRepository)
    }

    static BookRepository testBookRepository = RepositoryFactoryBuilder.builder()
            .generateKeysUsing(SequentialLongKeyGenerator.class)
            .mock(TestBookRepository.class)

    static interface TestBookRepository extends BookRepository {}
}
