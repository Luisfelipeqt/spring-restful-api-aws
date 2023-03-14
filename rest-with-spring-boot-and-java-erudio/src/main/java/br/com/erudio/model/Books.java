package br.com.erudio.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "author", length = 80)
    private String author;
    @Column(nullable = false, name = "launch_date")
    @DateTimeFormat(pattern = "yy/MM/aaaa")
    private Date launch_date;
    @Column(nullable = false, precision = 65, scale = 2)
    private Double price;
    @Column(length = 100)
    private String title;

    public Books(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) && Objects.equals(author, books.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author);
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", launch_date=" + launch_date +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
