package com.tweteroo.api.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.model.User;
import com.tweteroo.api.repository.TweetRepository;
import com.tweteroo.api.repository.UserRepository;

@Service
public class TweetService {
    
    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository; 

    public Tweet create(String username, TweetDTO tweetDTO) {
        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()) {
            return null;
        }

        return tweetRepository.save(new Tweet(user, tweetDTO));
    }
    
    public List<Tweet> findAllByName(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()) {
            return null;
        }

        List<Tweet> tweets = tweetRepository.findAllByUsername(username);

        Collections.reverse(tweets);

        return tweets;
    }

    public Page<Tweet> findPage(Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();

        Pageable pageRequest = PageRequest.of(
            page, size, Sort.by("id").descending());

        return tweetRepository.findAll(pageRequest);
    }
}
