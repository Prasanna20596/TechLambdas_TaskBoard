package com.taskboard.management.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskboard.management.constant.DocumentConstant;
import com.taskboard.management.dto.TaskBoardDTO;
import com.taskboard.management.mapper.TaskBoardMapper;
import com.taskboard.management.model.TaskBoard;
import com.taskboard.management.repository.TaskBoardRepository;
import com.taskboard.management.request.TaskBoardRequestPojo;
import com.taskboard.management.service.TaskBoardService;

@Service
public class TaskBoardServiceImpl implements TaskBoardService {

	Logger logger = LoggerFactory.getLogger(TaskBoardServiceImpl.class);

	@Autowired
	private TaskBoardRepository taskBoardRepository;

	@Autowired
	private TaskBoardMapper taskBoardMapper;

	@Override
	public boolean saveTaskBoardDetails(TaskBoardRequestPojo taskBoardRequestPojo) {
		logger.info("Received a request from the controller to save the task board details.");
		boolean isSaved = false;
		try {
			logger.info("The task board detail-saving process started...");
			TaskBoard taskBoard = new TaskBoard();

			taskBoard.setTaskId(DocumentConstant.TASK_CODE + taskBoardRequestPojo.getTaskId());
			taskBoard.setDate(LocalDate.now());
			taskBoard.setTaskType(taskBoardRequestPojo.getTaskType());
			taskBoard.setProjectName(taskBoardRequestPojo.getProjectName());
			taskBoard.setProjectOwnerId(taskBoardRequestPojo.getProjectOwnerId());
			taskBoard.setEmployeeId(DocumentConstant.COMPANY_CODE + taskBoardRequestPojo.getEmployeeId());
			taskBoard.setResourceName(taskBoardRequestPojo.getResourceName());
			taskBoard.setTitle(taskBoardRequestPojo.getTitle());
			taskBoard.setTitleDescription(taskBoardRequestPojo.getTitleDescription());
			taskBoard.setTaskStatus(taskBoardRequestPojo.getTaskStatus());
			taskBoard.setEstimationTime(taskBoardRequestPojo.getEstimationTime());
			taskBoard.setActualTime(taskBoardRequestPojo.getActualTime());
			taskBoard.setComments(taskBoardRequestPojo.getComments());
			TaskBoard saveTaskBoard = taskBoardRepository.save(taskBoard);
			isSaved = saveTaskBoard.getId() != null;
		} catch (Exception exception) {
			logger.error("Unable to save the task board details", exception);
			throw exception;
		}
		return isSaved;
	}

	@Override
	public boolean checkTaskTitleIsAssigned(String title) {
		logger.info("Received a request from the controller to check if the task board exists.");
		boolean isTaskTitleExist = false;
		try {
			logger.info("Checking the task board is already assigned to the employee");
			isTaskTitleExist = taskBoardRepository.existsByTitle(title);
		} catch (Exception exception) {
			logger.error("An error occurs while checking if the task board exists.", exception);
			throw exception;
		}
		return isTaskTitleExist;
	}

	@Override
	public List<TaskBoard> getTaskBoardDetails() {
		logger.info("Received a request from the controller to fetch the task board details.");
		List<TaskBoard> taskBoards = null;
		try {
			logger.info("Task board details fetching process started...");
			taskBoards = taskBoardRepository.findAll();
		} catch (Exception exception) {
			logger.error("Unable to fetch the task board details", exception);
			throw exception;
		}
		return taskBoards;
	}

	@Override
	public boolean updateTaskBoardDetails(String id, TaskBoardRequestPojo taskBoardRequestPojo) {
		logger.info("Received a request from the controller to update the task board details");
		boolean isTaskDetailsUpdated = false;
		try {
			TaskBoard existingTaskDetails = taskBoardRepository.findById(id).get();

			if (Objects.nonNull(existingTaskDetails)) {
				logger.info("Task board details exist by given ID.");

				TaskBoardDTO taskBoardDTO = taskBoardMapper.taskBoardDTO(taskBoardRequestPojo);
				taskBoardMapper.updateTaskBoardFromDTO(taskBoardDTO, existingTaskDetails);

				TaskBoard updateTaskBoard = taskBoardRepository.save(existingTaskDetails);

				isTaskDetailsUpdated = updateTaskBoard.getId() != null;
			} else {
				logger.error("TaskBoard with id {} does not found.", id);
			}
		} catch (Exception exception) {
			logger.error("Unable to update the task board details", exception.getMessage());
			throw exception;
		}
		return isTaskDetailsUpdated;
	}

	@Override
	public TaskBoard deleteTaskBoardDetail(String id) {
		logger.info("Received the request to delete the task board detail by id");
		TaskBoard deletedTaskBoard = null;
		try {
			deletedTaskBoard = taskBoardRepository.deleteTaskDetailsById(id);
			if (Objects.nonNull(deletedTaskBoard)) {
				logger.info("TaskBoard with id {} has been deleted successfully.", id);
			} else {
				logger.warn("TaskBoard with id {} does not exist.", id);
			}
		} catch (Exception exception) {
			logger.error("Unable to delete the task details", exception);
			throw exception;
		}
		return deletedTaskBoard;
	}

}
