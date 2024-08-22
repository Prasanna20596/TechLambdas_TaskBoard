package com.taskboard.management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.taskboard.management.dto.TaskBoardDTO;
import com.taskboard.management.model.TaskBoard;
import com.taskboard.management.request.TaskBoardRequestPojo;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskBoardMapper {

	TaskBoardDTO taskBoardDTO(TaskBoardRequestPojo taskBoardRequestPojo);

    void updateTaskBoardFromDTO(TaskBoardDTO taskBoardDTO, @MappingTarget TaskBoard taskBoard);

}
