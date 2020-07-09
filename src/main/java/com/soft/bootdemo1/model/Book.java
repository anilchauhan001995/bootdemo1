package com.soft.bootdemo1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int bookId;

    @NotEmpty(message = "Please Provide Book Name")
    private String name;

    @NotEmpty(message = "Please Provide Author Name")
    private String author;

    @NotNull(message = "Please Provide a Price")
    @DecimalMin("1.00")
    private BigDecimal price;

    public Book() {}

    public Book(@NotEmpty(message = "Please Provide Book Name") String name, @NotEmpty(message = "Please Provide Author Name") String author, @NotNull(message = "Please Provide a Price") @DecimalMin("1.00") BigDecimal price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
