package com.tweteroo.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Tweet create(TweetDTO tweetDTO) {
        Optional<User> user = userRepository.findByUsername(tweetDTO.username());

        if(!user.isPresent()) {
            return null;
        }

        return tweetRepository.save(new Tweet(user, tweetDTO));
    } 
}
