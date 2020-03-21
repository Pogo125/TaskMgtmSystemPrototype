package databaseconnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import tasks.Task;
import tasks.TaskStatus;

/**
 * Die Klasse dient zum Aufbau einer Verbindung zur Datenbank und zum Ausführen diverse Interaktionen mit der Datenbank 
 * @author Alfa
 * 
 * */
public class TaskDAOimplDB implements TaskDAO {

	private final String VERBINDUNG = "jdbc:mysql://localhost:3306/task_management_system";
	private final String USER = "root";
	private final String PW = "";


	/**
	 * Baut eine Verbindung zur SQL Datenbank auf und fügt eine task zur datenbank hinzu
	 * Exceptions werden abgefangen	
	 * @param task
	 */
	@Override
	public void addTask(Task task) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {			
			String sql = "INSERT INTO tasks VALUES(null, ?, ?, ?, ?, ?)";			
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, task.getTaskSummary());
			pstmt.setString(2, task.getRequester());
			pstmt.setString(3, task.getResponsible());			
			pstmt.setObject(4, task.getDueDate());						
			pstmt.setInt(5, task.getTaskStatus().ordinal());				
			pstmt.execute();

		} catch (SQLException ausnahme) {
			System.out.println("Fehlermeldung: " + ausnahme.getMessage());
			ausnahme.printStackTrace();
		}
	}

	
	/**
	 * Baut eine Verbindung zur SQL Datenbank auf und liest alle tasks aus der DB aus 
	 * Exceptions werden abgefangen
	 * @return
	 */	
	public List<Task> getAllTasks() {
		List<Task> allTasks = new ArrayList<>();

		try (
			// 1. Get a connection to the database
			Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);

			// 2. Create a statement
			Statement stm = conn.createStatement()) {
			String sql = "SELECT * FROM tasks";
			// Kommentar: Der SQL Befehl wird in ein Java Objekt verpackt

			// 3. Execute SQL query
			ResultSet rs = stm.executeQuery(sql);

			// 4. Process results set
			while (rs.next()) {
				String taskSummary = rs.getString("taskSummary");
				String requester = rs.getString("requestor");
				String responsible = rs.getString("responsible");
				Date dueDateInDateFormat = rs.getDate("dueDate");
				LocalDate dueDate = dueDateInDateFormat.toLocalDate();
				//Get taskStatus from DB as int/ordinal and convert into enum
				int taskStatusOrdial = rs.getInt("taskStatus");
				TaskStatus taskStatusEnum = TaskStatus.values()[taskStatusOrdial];				
				int taskID = rs.getInt("taskID");
				
				//Write retrived data into task DTO and retreived set ID and status
				Task taskObject = new Task(taskSummary, requester, responsible, dueDate);
				taskObject.setTaskID(taskID);
				taskObject.setTaskStatus(taskStatusEnum);
				//Write task DTO into list
				allTasks.add(taskObject);
			}
		} catch (SQLException ausnahme) {
			System.out.println("Connection ist geschlosse, das Statement ist geschlossen, Ausnahmen gab es trotzden:");
			System.out.println(ausnahme.getMessage());
		}
		return allTasks;
	}
	
	/**
	 * Baut eine Verbindung zur SQL Datenbank auf und löscht die Task, mit der im Parameter übergegebenen ID, aus der Datenbank 
	 * Exceptions werden abgefangen
	 * @param taskID
	 */
	//TODO: Hier ein Taskobjekt, anstelle einer ID, übergeben 
	@Override
	public void deleteTask(int taskID) {		
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {			
			String sql = "DELETE FROM tasks WHERE taskID =" + taskID + ";";			
			PreparedStatement pstmt = conn.prepareStatement(sql);			
			pstmt.execute();
		} catch (SQLException ausnahme) {
			System.out.println("Fehlermeldung: " + ausnahme.getMessage());
			ausnahme.printStackTrace();
		}
	}
	
	/**
	 * Baut eine Verbindung zur SQL Datenbank auf und aktualisiert die Task, mit der im Parameter übergegebenen ID, in der Datenbank 
	 * Exceptions werden abgefangen
	 * @param taskID
	 */
	@Override
	public void updateTask(int taskID, Task task) {
		try (Connection conn = DriverManager.getConnection(VERBINDUNG, USER, PW);
				Statement stm = conn.createStatement()) {			
			PreparedStatement pstmt = conn.prepareStatement
					("UPDATE tasks SET taskSummary = ?, requestor = ?, responsible = ?, dueDate = ?, taskStatus = ? WHERE taskID = " + taskID); 
						
			pstmt.setString(1, task.getTaskSummary());
			pstmt.setString(2, task.getRequester());
			pstmt.setString(3, task.getResponsible());
			pstmt.setObject(4, task.getDueDate());
			pstmt.setObject(5, task.getTaskStatus().ordinal());	
			pstmt.executeUpdate();			
			
		} catch (SQLException ausnahme) {
			System.out.println("Fehlermeldung: " + ausnahme.getMessage());
			ausnahme.printStackTrace();
		}
	}
		

}