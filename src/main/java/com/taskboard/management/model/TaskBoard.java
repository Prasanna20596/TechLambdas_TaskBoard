package com.taskboard.management.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.taskboard.management.enumTask.TaskStatus;
import com.taskboard.management.enumTask.TaskType;

import lombok.Data;

@Data
@Document(collection = "taskBoard")
public class TaskBoard {

	@Id
	private String id;
	private String taskId;
	private LocalDate date;
	private TaskType taskType;
	private String projectName;
	private String projectOwnerId;
	private String employeeId;
	private String resourceName;
	private String title;
	private String titleDescription;
	private TaskStatus taskStatus;
	private LocalDateTime estimationTime;
	private LocalDateTime actualTime;
	private String comments;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectOwnerId() {
		return projectOwnerId;
	}

	public void setProjectOwnerId(String projectOwnerId) {
		this.projectOwnerId = projectOwnerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public LocalDateTime getEstimationTime() {
		return estimationTime;
	}

	public void setEstimationTime(LocalDateTime estimationTime) {
		this.estimationTime = estimationTime;
	}

	public LocalDateTime getActualTime() {
		return actualTime;
	}

	public void setActualTime(LocalDateTime actualTime) {
		this.actualTime = actualTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
