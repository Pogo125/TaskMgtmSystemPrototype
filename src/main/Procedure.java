package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import databaseconnection.TaskDAO;
import databaseconnection.TaskDAOimplDB;
import gui.MainWindow;
import logiclayer.TaskService;
import taskgenerator.TaskGenerator;
import tasks.Task;
import tasks.TaskStatus;

/**
 * Enthält die Main Methode
 * Legt eine Service Objekt und ein Gui Objkekt an
 * Enthält Code zum Testen der Schnittstelle zur Datenhaltungsschicht. Dieser ist auskommentiert und kann zu Testzwecken aktiivert werden. 
 * @author Alfa
 *
 */
public class Procedure {
	

	public static void main(String[] args) {		
		//Interface persistenzschicht <=> logik schicht
		TaskDAO taskDAO = new TaskDAOimplDB();		
		TaskService taskService = new TaskService(taskDAO);
		//Ende Interface persistenzschicht <=> logik schicht
		
		//GUI		
		MainWindow mainWindow = new MainWindow(taskService);		
		mainWindow.setVisible(true);
		//ENDE GUI	
		
		
		
		
		//////////////////////Test Code Logik Schicht <=> SQL => im Betrieb auskommentieren
//		System.out.println("*********** Schreiben einer Task in die DB ***********");
//		Task testTask = new Task("Schnittstelle Logic <-> SQL testen", "Arlt", "Pedro", LocalDate.of(2022, 1, 9));
//		testTask.setTaskStatus(TaskStatus.IN_WORK);
//		taskService.addTask(testTask);
//		System.out.println("*********** Lesen aller Tasks aus die DB und ausgabe in die Konsole ***********");
//		List<Task> taskList = taskService.getAllTasks();
//		for(int i=0; i<taskList.size(); i++) {			
//			System.out.println(taskList.get(i));
//		}
		//////////////////////Test Code Logik Schicht <=> SQL  ende
		
		
	}
	
}