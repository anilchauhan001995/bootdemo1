package com.soft.bootdemo1.controllers;

import com.soft.bootdemo1.dao.BookRepository;
import com.soft.bootdemo1.dto.OutsideUser;
import com.soft.bootdemo1.error.BookNotFoundException;
import com.soft.bootdemo1.error.BookUnSupportedFieldPatchException;
import com.soft.bootdemo1.model.Book;
import com.soft.bootdemo1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by Dell on 4/25/2020.
 */
@RestController
@Validated
public class BaseController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user")
    public String user() {
        System.out.println("User");
        return "User";
    }

    @Async
    @GetMapping("/outsideUsers")
    public List<OutsideUser> othersUser() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");
        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
        ResponseEntity<OutsideUser[]> res = restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, entity, OutsideUser[].class);
        List<OutsideUser> list = Arrays.asList(res.getBody());

        return list;
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book save(@Valid @RequestBody Book book) {
        System.out.println("Save " + book);
        return repository.save(book);
    }

    @GetMapping(value = "/book/{id}")
    public Book findOne(@PathVariable @Min(1) Integer id) {
        System.out.println("Get");
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @GetMapping("/books")
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @PutMapping("/books/{id}")
    public Book saveAndUpdate(@RequestBody @Valid Book newBook, @PathVariable Integer id) {
        System.out.println("--------- " + id);
        return repository.findById(id).map(book -> {
            book.setName(newBook.getName());
            book.setAuthor(newBook.getAuthor());
            book.setPrice(newBook.getPrice());
            return repository.save(book);
        }).orElseGet(() -> {
            newBook.setBookId(id);
            return repository.save(newBook);
        });
    }

    @PatchMapping("/books/{id}")
    public Book updateAuthor(@RequestBody Map<String, String> update,  @PathVariable Integer id) {

        return repository.findById(id).map(book -> {
            String author = update.get("author");
            if (!StringUtils.isEmpty(author)) {
                book.setAuthor(author);
                return repository.save(book);
            } else {
                throw new BookUnSupportedFieldPatchException(update.keySet());
            }
        }).orElseGet(() -> {
            throw new BookNotFoundException(id);
        });
    }

}
