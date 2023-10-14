package com.example.eurekaclient.listener;
import com.example.eurekaclient.domain.DbOperation;
import com.example.eurekaclient.domain.Movie;
import com.example.eurekaclient.domain.MovieEvent;
import com.example.eurekaclient.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitListener {
    private final Logger log = LoggerFactory.getLogger(RabbitListener.class);
    private final MovieRepository movieRepository;

    public RabbitListener(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = "${moviesapi.receiving-queue}")
    public void receiveMessage(MovieEvent movieEvent) {
        log.info("Recive  : {}", movieEvent);
        if (movieEvent.dbOperation().equals(DbOperation.SAVE)){
            movieRepository.save(new Movie(movieEvent));
        }
    }
}
