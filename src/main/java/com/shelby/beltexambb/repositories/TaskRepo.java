package com.shelby.beltexambb.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shelby.beltexambb.models.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long>{

	List<Task> findAll();
	
	List<Task> findByOrderByPriorityDesc();
	List<Task> findByOrderByPriorityAsc();
}
