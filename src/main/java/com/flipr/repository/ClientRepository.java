package com.flipr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.flipr.modal.Client;

public interface ClientRepository extends MongoRepository<Client, String>{

}
