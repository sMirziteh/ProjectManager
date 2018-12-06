package com.shelby.beltexambb.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Task must not be empty")
	@Size(min=1, message="task cannot be empty")
	private String content;
	@NotNull(message="Task must be assigned")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="assignee_id")
	private User assignee;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User creator;
	@NotNull(message="Task must have a priority level")
	private String priority;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	@OneToMany(mappedBy="creator", fetch=FetchType.LAZY)
	private List<Task> createdTasks;
	
	@OneToMany(mappedBy="assignee", fetch=FetchType.LAZY)
	private List<Task> assignedTasks;

	
	public Task() {
		
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public User getAssignee() {
		return assignee;
	}


	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User creator) {
		this.creator = creator;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public List<Task> getCreatedTasks() {
		return createdTasks;
	}


	public void setCreatedTasks(List<Task> createdTasks) {
		this.createdTasks = createdTasks;
	}


	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}


	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}
}
