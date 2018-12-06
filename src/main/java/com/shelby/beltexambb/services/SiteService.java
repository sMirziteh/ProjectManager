package com.shelby.beltexambb.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.shelby.beltexambb.models.Task;
import com.shelby.beltexambb.models.User;
import com.shelby.beltexambb.repositories.TaskRepo;
import com.shelby.beltexambb.repositories.UserRepo;


@Service
public class SiteService {
	private final UserRepo ur;
	private final TaskRepo tr;
	
	
	public SiteService(UserRepo ur, TaskRepo tr) {
		this.ur = ur;
		this.tr = tr;
	}
	
	public User findByEmail(String email) {
		return ur.findByEmail(email);
	}
	
	public User findUserById(Long id) {
		Optional<User> opUser = ur.findById(id);
		if(opUser.isPresent()) {
			return opUser.get();
		} else {
			return null;
		}
	}
	
	public Task findTaskById(Long id) {
		Optional<Task> opTask = tr.findById(id);
		if(opTask.isPresent()) {
			return opTask.get();
		} else {
			return null;
		}
	}
	
	public List<User> findAllUsers(){
		return ur.findAll();
	}
	
	public List<Task> findAllTasks(){
		return tr.findAll();
	}
	
	public User registerUSer(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return ur.save(user);
	}
	
	public User findUSerById(Long id) {
		Optional<User> u = ur.findById(id);
		if(u.isPresent()) {
			return u.get();
		} else {
			return null;
		}
	}
	
	public boolean authenticateUser(String email, String password) {
		//find user by email
		User user = ur.findByEmail(email);
		//if we can't find it by email, return false
		if(user == null) {
			return false;
		}else {
			//if the passwords match, return true, else, return false
			if(BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

	public Task addNewTask(Task newTask) {
		return tr.save(newTask);
		
	}
	public Task addNewTask(Task newTask, Long uid) {
		User creator = findUserById(uid);
		if(creator != null) {
			newTask.setCreator(creator);
			return tr.save(newTask);			
		} else {
			return null;
		}
		
	}

	public void deleteTask(Long id) {
		Task task = findTaskById(id);
		tr.delete(task);
		
	}
	
	public List<Task> allTaskDesc(){
		return tr.findByOrderByPriorityDesc();
	}
	public List<Task> allTaskAsc(){
		return tr.findByOrderByPriorityAsc();
	}
}
