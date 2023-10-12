package com.example.eurekaclient.repository;


import com.example.eurekaclient.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieRepository extends MongoRepository<Movie, UUID> {

}
