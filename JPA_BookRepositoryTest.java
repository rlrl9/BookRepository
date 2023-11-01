package com.example.springedu2;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import springjpa.exam.entity.Book;
import springjpa.exam.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
public class JPA_BookRepositoryTest {
    @Autowired
    private BookRepository bookR;

    @BeforeEach()
    void pr() {
        System.out.println("=".repeat(80));
    }

    @Test
    @Order(1)
    void list() {
        List<Book> list = bookR.findAll();
        list.stream().forEach(System.out::println);
    }

    @Test
    @Order(4)
    void listId() {
        Optional<Book> book = bookR.findById(10);
        if(book.isEmpty()){
            System.out.println("*********존재하지 않음*********");
        }
        else{
            System.out.println(book);
        }

    }
    @Test
    @Order(2)
    void orderByPrice() {
        List<Book> list = bookR.findAll(Sort.by("price").descending());
        list.stream().forEach(System.out::println);
    }
    @Test
    @Order(3)
    void listOver20000() {
        List<Book> list = bookR.findByPriceGreaterThan(20000);
        list.stream().forEach(System.out::println);
    }
    @Test
    @Order(5)
    void listJavaSpring() {
        List<Book> list = bookR.findByTitleContainingOrTitleContaining("자바", "스프링");
        list.stream().forEach(System.out::println);
    }
    @Test
    @Order(6)
    void listAvg() {
        List<Object[]> list = bookR.avgByPrice();
        for (Object[] objects : list) {
            String stringObject = (String) objects[0];
            Double doubleObject = (Double) objects[1];

            System.out.println(stringObject+" : "+doubleObject);
        }
    }
    @Test
    @Order(7)
    void cheapest(){
        Book book = bookR.findTopByOrderByPriceDesc();
        System.out.println(book);
    }
    @Test
    @Order(8)
    void countAll(){
        Long counting = bookR.count();
        System.out.println("전체 도서의 수 : "+counting);
    }
    @Test
    @Order(9)
    void countB05(){
        Long counting = bookR.countByKind("b05");
        System.out.println("b05 부류의 도서는 "+counting+"권");
    }
    @Test
    @Order(10)
    void vanilla() {
        List<Book> list = bookR.findByTitleStartsWith("바닐라");
        list.stream().forEach(System.out::println);
    }


}
