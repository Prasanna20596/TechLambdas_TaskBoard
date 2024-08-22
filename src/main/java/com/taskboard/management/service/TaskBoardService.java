package com.taskboard.management.service;

import java.util.List;

import com.taskboard.management.model.TaskBoard;
import com.taskboard.management.request.TaskBoardRequestPojo;

public interface TaskBoardService {

	public boolean saveTaskBoardDetails(TaskBoardRequestPojo taskBoardRequestPojo);
	
	public boolean checkTaskTitleIsAssigned(String title);
	
	public List<TaskBoard> getTaskBoardDetails();
	
	public boolean updateTaskBoardDetails(String id,TaskBoardRequestPojo taskBoardRequestPojo);
	
	public TaskBoard deleteTaskBoardDetail(String id);

	
}
