package com.flipr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flipr.modal.Contact;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

}
