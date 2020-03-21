package tasks;

import java.time.LocalDate;
import java.util.Date;

/**
 * DTO Klasse für Tasks. Rahmendaten eine Task, wie Anforderer, Veranwortlicher, Zieltermin etc. sind in der Task enthalten
 * @author Alfa
 *
 */

public class Task {
	
	/**
	 * Enthält die Beschreibung der Aufgabe
	 */
	protected String taskSummary;
	/**
	 * Anforderer der Aufgabe
	 */	
	protected String requester;
	/**
	 * Verantwortlicher für die Aufgabe
	 */
	protected String responsible;
	/**
	 * Zieltermin
	 */
	protected LocalDate dueDate; // setzten durch: = LocalDate.of(1970, 01, 01);
	/**
	 * Eineindeutige ID (Wird von der DB vergeben)
	 */
	protected int taskID = 0;
	/**
	 * Task Status (enum)
	 */
	protected TaskStatus taskStatus;
	//private String taskStatus2 = {};
	
	
	public Task(String taskSummary, String requester, String responsible, LocalDate dueDate) {
		this.taskSummary = taskSummary;
		this.requester = requester;
		this.responsible = responsible;
		this.dueDate = dueDate;	
		this.taskStatus = TaskStatus.OPEN;
	}


	@Override
	public String toString() {
		return "Task [taskSummary=" + taskSummary + ", requester=" + requester + ", responsible=" + responsible
				+ ", dueDate=" + dueDate + ", taskID=" + taskID + ", taskStatus=" + taskStatus + "]";
	}

	public int getTaskID() {
		return taskID;
	}
		
	public String getTaskSummary() {
		return taskSummary;
	}

	public String getRequester() {
		return requester;
	}

	public String getResponsible() {
		return responsible;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}	
	
	public void setTaskSummary(String taskSummary) {
		this.taskSummary = taskSummary;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}	
	
}
