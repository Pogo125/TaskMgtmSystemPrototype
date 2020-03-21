package logiclayer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import databaseconnection.TaskDAO;
import databaseconnection.TaskDAOimplDB;
import tasks.Task;

/**
 * Serviceklasse zum Abbilden von Logikfunktionen
 */
public class TaskService {
	
	/**
	 * Declare variables of TaksService class
	 */
	//private List<Task> taskListDBCopy = new ArrayList<Task>();
	//private List<Task> taskListDBCopyByRequestor = new ArrayList<Task>();
	
	private TaskDAO taskDAO;	
	
	
	//Constructor
	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	//Get all tasks
	public List<Task> getAllTasks() {
		List<Task>taskListDBCopy = taskDAO.getAllTasks();
		return taskListDBCopy;
	}
	
	//Add task
	public void addTask(Task task) {
		taskDAO.addTask(task);
	}
	
	/**
	 * Gibt eine Taskliste zurück, die von dem, im Parameter übergebenen, Requestor kommen
	 * @param requestor
	 * @return
	 */
	//Select task by criterion "requestor"
	public List<Task> getTaskByReqestor(String requestor) {
		List<Task> taskListDBCopy = this.getAllTasks();
		List<Task> taskListDBCopyByRequestor =
				taskListDBCopy.stream().filter(task -> task.getRequester().equals(requestor)).collect(Collectors.toList());
		return taskListDBCopyByRequestor;
	}
	
	public void delteTask(int id) {
		taskDAO.deleteTask(id);
	}
	
	public void updateTask(int id, Task task) { 
		taskDAO.updateTask(id, task);
	}

}
