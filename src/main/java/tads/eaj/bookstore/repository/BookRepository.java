package tads.eaj.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
