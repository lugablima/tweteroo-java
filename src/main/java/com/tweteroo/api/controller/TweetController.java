package com.tweteroo.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService service;

    @GetMapping("/{username}")
    public ResponseEntity<List<Tweet>> findAllByName(@PathVariable String username) {
        var listOfTweets = service.findAllByName(username);

        if(listOfTweets != null) {
            return ResponseEntity.status(HttpStatus.OK).body(listOfTweets);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Tweet>> findPage(@PageableDefault(page = 0, size = 5) Pageable page) {
        Page<Tweet> tweets = service.findPage(page);

        return ResponseEntity.status(HttpStatus.OK).body(tweets);
    }
    
    @PostMapping
    public ResponseEntity<String> create(@RequestHeader("User") String username, @RequestBody @Valid TweetDTO tweetDTO) {
        var tweet = service.create(username, tweetDTO);

        if(tweet != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }
}
