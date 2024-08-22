package com.taskboard.management.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskboard.management.model.TaskBoard;
import com.taskboard.management.request.TaskBoardRequestPojo;
import com.taskboard.management.response.TaskBoardResponsePojo;
import com.taskboard.management.service.TaskBoardService;

@RestController
public class TaskBoardController {
	
	Logger logger = LoggerFactory.getLogger(TaskBoardController.class);

	@Autowired
	private TaskBoardService taskBoardService;

	@PostMapping("/saveTaskBoardDetails")
	public TaskBoardResponsePojo saveTaskBoardDetails(@RequestBody TaskBoardRequestPojo taskBoardRequestPojo) {
		logger.info("Received a request to save the task board details.");
		TaskBoardResponsePojo taskBoardResponsePojo = new TaskBoardResponsePojo();
		try {
			if (taskBoardService.checkTaskTitleIsAssigned(taskBoardRequestPojo.getTitle())) {
				logger.error("Given that task board details are already assigned.");
				taskBoardResponsePojo.errorResponse("Given that task board details are already assigned.");
			} else {
				if (taskBoardService.saveTaskBoardDetails(taskBoardRequestPojo)) {
					logger.info("Task board details are saved.");
					taskBoardResponsePojo.response("Task board details are saved.", null, true);
				} else {
					logger.error("The task board details cannot be saved.");
					taskBoardResponsePojo.errorResponse("The task board details cannot be saved.");
				}
			}

		} catch (Exception exception) {
			logger.error("An error occurred while saving task board details.");
			taskBoardResponsePojo.errorResponse("An error occurred while saving task board details.");
		}
		return taskBoardResponsePojo;

	}

	@GetMapping("/fetchTaskBoardDetails")
	public TaskBoardResponsePojo fetchTaskBoardDetails() {
		logger.info("Received a request to fetch the task board details.");
		TaskBoardResponsePojo taskBoardResponsePojo = new TaskBoardResponsePojo();
		try {
			List<TaskBoard> taskBoards = taskBoardService.getTaskBoardDetails();
			if (Objects.nonNull(taskBoards)) {
				logger.info("Task board details are");
				taskBoardResponsePojo.response("Task board details are", taskBoards, true);
			} else {
				logger.info("Task board details are not found.");
				taskBoardResponsePojo.response("Task board details are not found.", taskBoards, true);
			}
		} catch (Exception exception) {
			logger.error("An error occurred while fetching task board details.");
			taskBoardResponsePojo.errorResponse("An error occurred while fetching task board details.");
		}
		return taskBoardResponsePojo;
	}

	@PutMapping("/updateTaskBoardDetails/{id}")
	public TaskBoardResponsePojo updateTaskBoardDetails(@PathVariable String id,
			@RequestBody TaskBoardRequestPojo taskBoardRequestPojo) {
		logger.info("Received a request to update the task board details.");
		TaskBoardResponsePojo taskBoardResponsePojo = new TaskBoardResponsePojo();
		try {
			if (taskBoardService.updateTaskBoardDetails(id, taskBoardRequestPojo)) {
				logger.info("Task board details are updated.");
				taskBoardResponsePojo.response("Task board details are updated.", null, true);
			} else {
				logger.error("Unable to update the task board details.");
				taskBoardResponsePojo.errorResponse("Unable to update the task board details.");
			}
		} catch (Exception exception) {
			logger.error("An error occurred while updating task board details.");
			taskBoardResponsePojo.errorResponse("An error occurred while updating task board details.");
		}
		return taskBoardResponsePojo;
	}

	@DeleteMapping("/deleteTaskBoardDetail/{id}")
	public TaskBoardResponsePojo deleteTaskBoardDetails(@PathVariable String id) {
		logger.info("Received a request to delete the task board details.");
		TaskBoardResponsePojo taskBoardResponsePojo = new TaskBoardResponsePojo();
		try {
			TaskBoard deletedTaskBoard = taskBoardService.deleteTaskBoardDetail(id);
			if (Objects.nonNull(deletedTaskBoard)) {
				logger.info("Task board details are deleted.");
				taskBoardResponsePojo.response("Task board details are deleted.", null, true);
			} else {
				logger.error("Unable to delete the task board details.");
				taskBoardResponsePojo.errorResponse("Unable to delete the task board details.");
			}

		} catch (Exception exception) {
			logger.error("An error occurred while deleting task board details.");
			taskBoardResponsePojo.errorResponse("An error occurred while deleting task board details.");
		}
		return taskBoardResponsePojo;
	}


}
