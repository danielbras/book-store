package tads.eaj.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.bookstore.model.Book;
import tads.eaj.bookstore.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public void setRepository(BookRepository repository){
        this.repository = repository;
    }

    public List<Book> findAll(){
        return repository.findAll();
    }

    public void save(Book b){
        repository.save(b);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Book getById(Long id){
    return repository.getOne(id);
    }
}
