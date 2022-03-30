package ru.kotikov.newsline.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kotikov.newsline.model.News;

public interface NewsRepo extends JpaRepository<News, Long> {
    Page<News> findAll(Pageable pageable);
}
