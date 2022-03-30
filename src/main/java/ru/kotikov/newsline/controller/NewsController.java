package ru.kotikov.newsline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kotikov.newsline.model.News;
import ru.kotikov.newsline.repository.NewsRepo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
public class NewsController {

    // repository with data
    @Autowired
    NewsRepo newsRepo;

    // path for uploads files
    @Value("${upload.path}")
    private String uploadPath;

    // Main page with news
    @GetMapping("/")
    public String main(
            Model model,
            // for page display
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        // page with all news
        Page<News> page = newsRepo.findAll(pageable);
        // adding page in model for viewing
        model.addAttribute("page", page);
        // adding URL in model
        model.addAttribute("url", "/");
        return "main";
    }

    // news introduction page
    @GetMapping("/add")
    public String addPage(Model model) {
        return "add";
    }

    // method for adding news
    @PostMapping("/add")
    public String addingNews(
            @RequestParam String newsTittle, // param with news tittle
            @RequestParam String newsText, // param wist news text
            // param for upload image file
            @RequestParam("file") MultipartFile file, Map<String, Object> model
    ) throws IOException {
        // create new news with input params
        News news = new News(newsTittle, new Date(), newsText);
        // condition for file validation check
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            // UUID for file
            String uuidFile = UUID.randomUUID().toString();
            // final name for image file
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            // converting MultipartFile to File
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            // set final name for file in directory
            news.setFilename(resultFilename);
        }
        // save news in repository (database)
        newsRepo.save(news);
        // view html template with params
        return "redirect:/";
    }
}
