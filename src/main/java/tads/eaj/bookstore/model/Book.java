package tads.eaj.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Size(min = 5, max = 60, message = "O título deve ter entre 5 e 60 caracteres!")
    @NotBlank(message = "O campo não deve estar em branco")
    String title;

    @Size(min = 3, max = 60, message = "O nome do autor deve ter entre 3 e 60 caracteres!")
    @NotBlank(message = "O campo não deve estar em branco")
    String author;

    @DecimalMin(value = "0", message = "Deve ser maior ou igual a zero")
    @NotNull(message = "O campo não deve estar em branco")
    Double price;

    String subtitle;

    Date release_date;

    String description;

    String language;

}
