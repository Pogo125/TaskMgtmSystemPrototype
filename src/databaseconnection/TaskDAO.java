package databaseconnection;

import java.util.List;
import tasks.Task;

/**
 * Data aquisition object => interface um diverse interkationen zwischen der Logikschicht und Datenhaltungsschichten, wie z.B.einer SQL Datenbank zu ermöglichen
 * @author Alfa
 *
 */
public interface TaskDAO {
	
	public void addTask(Task task);
	public List<Task> getAllTasks();
	public void deleteTask(int taskID); 
	public void updateTask(int taskID, Task task); 

}

