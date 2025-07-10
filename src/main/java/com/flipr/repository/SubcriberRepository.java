package com.flipr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flipr.modal.Subcriber;

@Repository
public interface SubcriberRepository extends MongoRepository<Subcriber, String> {

}
