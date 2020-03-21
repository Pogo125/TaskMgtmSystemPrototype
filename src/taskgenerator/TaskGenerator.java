package taskgenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import tasks.Task;
/**
 * Generiert Tasks zu Testzwecken. Wird im Live Betrieb nicht benötigt.
 * @author Alfa
 *
 */
public class TaskGenerator {
	List<Task> taskList = new ArrayList<Task>();
	Task task ;
	String[] taskSummary = {"L01", "L02", "L03", "K05", "K06", "K07", "M01", "M02"};
	String[] requester = {"Arlt", "Trolenberg", "Lunau", "Gries"};
	String[] responsible = {"Deleimi", "Mehdi", "Lui"};
	
	public List<Task> generateTaskList(int amountOfTasksToGenerate) {
		for(int i=0; i<amountOfTasksToGenerate; i++) {
			int year = ThreadLocalRandom.current().nextInt(2020, 2022);
			int month = ThreadLocalRandom.current().nextInt(1, 12);
			int day = ThreadLocalRandom.current().nextInt(1, 28);			
			String tSummary = taskSummary[new Random().nextInt(taskSummary.length)];
			String req = requester[new Random().nextInt(requester.length)];
			String resp = responsible[new Random().nextInt(responsible.length)];
			LocalDate dDate = LocalDate.of(year, month, day);
			task = new Task(tSummary, req, resp, dDate);
			taskList.add(task);
		}
		return taskList;		
	}
	
	
	
	
	
	//Task task = new Task();

}
