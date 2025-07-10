package com.flipr.repository;

import com.flipr.modal.Project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
	
}
