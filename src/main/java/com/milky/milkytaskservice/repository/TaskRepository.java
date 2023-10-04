package com.milky.milkytaskservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.milky.milkytaskservice.model.Task;

@Repository
public interface TaskRepository extends MongoRepository<Task, String>{
}
