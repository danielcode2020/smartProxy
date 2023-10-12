package com.example.eurekaclient.listener;

import com.example.eurekaclient.domain.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class MovieMongoEventListener extends AbstractMongoEventListener<Movie> {

    private final Logger log = LoggerFactory.getLogger(MovieMongoEventListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<Movie> event) {
        Movie savedMovie = event.getSource();
        log.info("Saved to db : {}", event.getSource());
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Movie> event) {
        log.info("Deleted from db : {}", event.getDocument());
    }

}
