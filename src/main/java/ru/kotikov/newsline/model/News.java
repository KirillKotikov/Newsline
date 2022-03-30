package ru.kotikov.newsline.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "news")
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String newsTittle;
    @Column(nullable = false)
    private String dateAdded;
    @Column(nullable = false)
    private String newsText;
    private String filename;

    // create news with normal date format
    public News(String newsTittle, Date dateAdded, String newsText) {
        this.newsTittle = newsTittle;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        this.dateAdded = df.format(dateAdded);
        this.newsText = newsText;
    }
}