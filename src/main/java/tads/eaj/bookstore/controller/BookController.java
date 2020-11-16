package tads.eaj.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tads.eaj.bookstore.model.Book;
import tads.eaj.bookstore.service.BookService;

import java.util.List;

@Controller
public class BookController {

    BookService service;

    @Autowired
    public void setService(BookService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String getHome(Model model){
        List<Book> book_list = service.findAll();
        model.addAttribute("book_list", book_list);
        return "home";
    }

    @RequestMapping("/details/{id}")
    public String getDetails(@PathVariable(name = "id") Long id, Model model){
        Book book = service.getById(id);
        model.addAttribute("book", book);
        return "details";
    }

    @RequestMapping("/register")
    public String getCreate(Model model){
        Book new_book = new Book();
        model.addAttribute("new_book", new_book);
        return "register";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(@ModelAttribute Book book, Model model){
        service.save(book);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Book b = service.getById(id);
        modelAndView.addObject("book", b);
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String toDelete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return "redirect:/";
    }
}
