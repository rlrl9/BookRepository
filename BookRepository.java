package springjpa.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springjpa.exam.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPriceGreaterThan(int i);


    Book findTopByOrderByPriceDesc();
    Long countByKind(String b05);
    List<Book> findByTitleStartsWith(String va);

    List<Book> findByTitleContainingOrTitleContaining(String s1, String s2);

    @Query("SELECT b.kind, AVG(b.price) FROM Book b GROUP BY b.kind")
    List<Object[]> avgByPrice();
}
