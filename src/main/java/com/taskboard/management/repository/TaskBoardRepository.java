package com.taskboard.management.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.taskboard.management.model.TaskBoard;

@Repository
public interface TaskBoardRepository extends MongoRepository<TaskBoard,String> {
	
	public boolean existsByTitle(String title);
	
	public boolean existsById(String id);

	@Query(value="{'id' : ?0}", delete = true)
    public TaskBoard deleteTaskDetailsById(String id);
}
