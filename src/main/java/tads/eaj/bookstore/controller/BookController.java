package tads.eaj.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tads.eaj.bookstore.model.Book;
import tads.eaj.bookstore.service.BookService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
public class BookController {

    BookService service;

    @Autowired
    public void setService(BookService service){
        this.service = service;
    }

    @RequestMapping("/")
    public String getHome(@ModelAttribute(value = "feedback") Book book, Model model, HttpServletResponse response ){
        Calendar calendar = Calendar.getInstance();
        String access = calendar.getTime().toString().replace(" ", "_");
        Cookie c = new Cookie("lastAccess", access);
        response.addCookie(c);

        List<Book> book_list = service.findAll();
        model.addAttribute("book_list", book_list);
        model.addAttribute("feedbackMessage", book);
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
    @GetMapping("/save")
    public String doSave(@ModelAttribute (name = "new_book") @Valid Book book, Errors errors, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            return "register";
        } else {
            service.save(book);
            redirectAttributes.addFlashAttribute("feedback", book);
            return "redirect:/";
        }
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView getEdit(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Book b = service.getById(id);
        modelAndView.addObject("book", b);
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String toDelete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes){
        Book book = service.getById(id);
        redirectAttributes.addFlashAttribute("feedback", book);
        service.delete(id);
        return "redirect:/";
    }
}
