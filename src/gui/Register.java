package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import logiclayer.TaskService;
import tasks.Task;
import tasks.TaskStatus;

/**
 * Enthält die Registerkarten mit den wesntlichen Funktionen des Systems. 
 * Die einzelnen Tabs werden über Methoden erstellt, die aus dem Konstruktor aufgerufen werden.
 * In diesen Methoden ist die Funktionalität der zugehörigen Tabs näher beschrieben.
 * 
 * @author Alfa
 *
 */
public class Register extends JTabbedPane {
	
	/////////////  Variablen deklarieren  ///////////
	//Deklaration Variablen zum Datenaustausch 
	/**
	 * TaskService objekt zwecks Zugriff auf die Datenhaltungsschicht und die entsprechenden Mehtoden wie, "add", "update"....
	 */
	private TaskService taskService;
	/**
	 * List Objekt mit einem generic vom Typ Task. Dient als Schnittstellenelement zwischen Eingabefenster, Dashboard (Anzeige der Daten) und Datenhaltsungsschicht 
	 */
	private List<Task> taskList;
	
	//Declare fields of table tab
	/**
	 * Dient zur identifizierung der Ausgewählten Zeile in der Anzeigetabelle im Dashboard. Die Daten der entsprechenden Zeile können so weiter verwertetet
	 * werden, zur Anzeige im Eingabefenster und bei Bedarf zum Update der Datenhaltungsschicht
	 */
	private int selectedRow;
	/**
	 * Dient zur identifizierung der Ausgeählten Spalte in der Anzeigetabelle im Dashboard. 
	 */
	private int selectedColoumn;
	/**
	 * Wird über die TaskList befüllt und die Daten werden hiermit an die Tabelle im Dashboard weiter gegeben. Somit werden in der Tabelle im Dashbaord immer
	 * alle Daten die auch in der Datenhaltungsschicht vorhanden sind angezeigt.   
	 */
	private DefaultTableModel dtm;
	/**
	 * Tabelle die die Tasks der Datenhaltungsschicht und etwaige hinzugefügte Daten (über die Dateneingabe Fenster) anzeigt 
	 */
	private JTable jTableTasks;	
	
	//Declare text fields and combo box for use in the Task Tab and for retrival of data in entire class
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt und zeigt die Task ID an, sofern die Task schon hinzugefügt wurde.
	 * Wurde die Task noch nicht angelegt, ist das Feld leer. 
	 * Ist vom User nicht editierbar und dient nur der Anzeige, da die Task ID von der Datenbank vergeben wird. 
	 */
	private JTextField taskIDField;		
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt. Dient der Eingabe der im Namen beschriebenen Daten beim Anlegen von neuen Tasks.
	 * Wenn eine bereits bestehenden Task im Dashboard ausgewühlt wurde, wird diese hier angezeigt.	 * 
	 */
	private JTextField taskSummaryField;
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt. Dient der Eingabe der im Namen beschriebenen Daten beim Anlegen von neuen Tasks.
	 * Wenn eine bereits bestehenden Task im Dashboard ausgewühlt wurde, wird diese hier angezeigt.	 * 
	 */
	private JTextField requesterField;
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt. Dient der Eingabe der im Namen beschriebenen Daten beim Anlegen von neuen Tasks.
	 * Wenn eine bereits bestehenden Task im Dashboard ausgewühlt wurde, wird diese hier angezeigt.	 * 
	 */
	private JTextField responsibleField;
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt. Dient der Eingabe der im Namen beschriebenen Daten beim Anlegen von neuen Tasks.
	 * Wenn eine bereits bestehenden Task im Dashboard ausgewühlt wurde, wird diese hier angezeigt.	 * 
	 */
	private JTextField dueDateField;
	/**
	 * Wird im Eingabefenster "Tasks" angezeigt. Dient der Eingabe der im Namen beschriebenen Daten beim Anlegen von neuen Tasks.
	 * Wenn eine bereits bestehenden Task im Dashboard ausgewühlt wurde, wird diese hier angezeigt.	 * 
	 */
	private JComboBox<TaskStatus> taskStatusComboBox;	
	
	
	/////////////  Konstruktor definieren  ///////////
	/**
	 * Konstruktor für die Registerkarten. Benötigt als Parameter eine Objekt vom Typ Task service um den Gui Fenstern 
	 * einen Zugriff auf die Logikschicht zu ermöglichen
	 * @param taskService
	 */
	public Register(TaskService taskService) {
		this.taskService = taskService;
		createTaskTab();
		createTaskDashboardTab();
		createAdminTab();		
	}
		
	//Tab bauen: "Tasks"
	/**
	 * Erzegt das Tab "Task" mit den entsprechenden Eingabefeldern, Labels, Buttons und Action Listenern
	 */
	private void createTaskTab() {		
		//Create panel
		JPanel taskTab = new JPanel();		
		taskTab.setLayout(new GridLayout(8, 2));
				
		JButton addTaskButton = new JButton("Add task");
		addTaskButton.addActionListener(event -> {			
			actionPerformedButtonAdd(event);
		});	
		
		JButton updateTaskButton = new JButton("Update task");		
		updateTaskButton.addActionListener(event ->	{			
			actionPerformedButtonUpdate(event);
		});	
		
		JButton clearFieldsButton = new JButton("Clear Fields");
		clearFieldsButton.addActionListener(event ->	{			
			actionPerformedButtonClear(event);
		});	
			
		JButton placeholderButton = new JButton(" "); //Nur um layout zu gewährleisten => TODO: Layout verbessern, dummy button eliminieren
		
		//Buttons dem Panel hinzufügen
		taskTab.add(addTaskButton);
		taskTab.add(updateTaskButton);
		taskTab.add(clearFieldsButton);
		taskTab.add(placeholderButton);	
		
		// Textfelder und combo box und zugehörige Label und deklarieren und initialisieren
		JLabel taskIDLabel = new JLabel("Task ID", 4);
		taskIDField = new JTextField(" ", 1);
		taskIDField.setEditable(false);		
		JLabel taskSummaryLabel = new JLabel("Task Summary", 4);
		taskSummaryField = new JTextField(" ", 1);		
		JLabel requesterLabel = new JLabel("Requestor", 4);
		requesterField = new JTextField(" ", 1);		
		JLabel responsibleLabel = new JLabel("Responsible", 4);
		responsibleField = new JTextField(" ", 1);		
		JLabel dueDateLabel = new JLabel("Due Date (yyyy-mm-dd)", 4);
		dueDateField = new JTextField(" ", 1);		
		JLabel taskStatusLabel = new JLabel("Task Status", 4);		
		
		taskStatusComboBox = new JComboBox(TaskStatus.values());
				
		// Label dem Panel hinzufügen
		taskTab.add(taskIDLabel);
		taskTab.add(taskIDField);
		taskTab.add(taskSummaryLabel);
		taskTab.add(taskSummaryField);
		taskTab.add(requesterLabel);
		taskTab.add(requesterField);
		taskTab.add(responsibleLabel);
		taskTab.add(responsibleField);
		taskTab.add(dueDateLabel);
		taskTab.add(dueDateField);
		taskTab.add(taskStatusLabel);
		taskTab.add(taskStatusComboBox);		
		// Text Felder und CB dem Panel hinzufügen
			
		add(taskTab, "Tasks");
	}
	
	//Tab bauen: "Dashboard"
	/**
	 * Erzegt das Tab "Dashboard" mit der zugehärigen Tabelle und dem Mouse Listener. Mit dem Mouse Listener wird festgestellt, welche
	 * Zeile und damit Task vom User ausgewält wurde. Dies Task wird dann im Eingabefenster angezeigt und kann dort ggf. modifiziert werden. 
	 */
	private void createTaskDashboardTab() {			
		
		//Create Scroll Panel
		JPanel tabDashboard = new JPanel();	
		
		//Table header and table model		
		String tableColoumHeader[] = new String[] { "Task ID", "Task Summary", "Requestor", "Responsible", "Due Date", "Status"};
		dtm = new DefaultTableModel(tableColoumHeader, 0);
		
		//Task Liste aus persistenzsschickt aktualisieren
		taskList = loadDataFromPersistenceLayer();		
		
		// Tabelle in GUI mit letzem Datenstand aus TaskList neu aufbauen
		updateGuiDataTable ();		

		//Create table and assign simple Table Model which defines the headers and amount of rows
		jTableTasks = new JTable();		
		jTableTasks.setModel(dtm);
		jTableTasks.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				mouseListenerForTable(evt);
			}
		});
		
		//Add table to dashboard tab
		tabDashboard.add(jTableTasks);
		add(new JScrollPane(jTableTasks), "Dashboard");
		
	}
	
	/**
	 * Ermöglicht das Anlegen von Usern etc. Ist noch "Work in Progress" 
	 */
	//Tab bauen: "Admin"
	private void createAdminTab() {		
		JPanel tabAdmin = new JPanel();
		add(tabAdmin, "Admin");		
	}
	
	
	
	/////////////  Action listener definieren  ///////////
	/**
	 * Mit dem Mouse Listener wird festgestellt, welche Zeile und damit Task vom User ausgewält wurde.
	 * Dies Task wird dann im Eingabefenster angezeigt und kann dort ggf. modifiziert werden. 
	 */
	private void mouseListenerForTable(MouseEvent evt) {
		//Identify row which was clicked with the mouse
		selectedRow = jTableTasks.getSelectedRow();
		selectedColoumn = jTableTasks.getColumnCount();	
	
		//Get content of the clicked table row and transfer this into the text fields, so the two correspond		
		taskIDField.setText(dtm.getValueAt(selectedRow, 0).toString());//Get task ID
		taskSummaryField.setText(dtm.getValueAt(selectedRow, 1).toString());//Get task Summary		
		requesterField.setText(dtm.getValueAt(selectedRow, 2).toString());//Get task Requestor
		responsibleField.setText(dtm.getValueAt(selectedRow, 3).toString());//Get task Responsible		
		dueDateField.setText(dtm.getValueAt(selectedRow, 4).toString());//Get task DueDate		
		String taskStatus = dtm.getValueAt(selectedRow, 5).toString();//Get status from table
	}

	/**
	 * Action listner für denn "Add" Button. Wird der Action Listener aufgerufen, werden die Felder des Eingabefensters ausgelesen und in ein
	 * Taskobjekt geschrieben, dass dann der Tasklist hinzugefügt wird. Daraufhin werden die Persistenzschicht und die Tabelle im Dashboard
	 * über den Aufruf der entsprechenden Methoden aktualisert. 
	 * @param evt
	 */
	private void actionPerformedButtonAdd(ActionEvent evt) {
		//read fields from data entry part and write this data into a food object				
		String taskSummary = taskSummaryField.getText();
		String requestor = requesterField.getText();
		String responsible = responsibleField.getText();
		TaskStatus taskStatus = (TaskStatus) taskStatusComboBox.getSelectedItem();
		
		//LocalDate dueDateAsLocalDate = LocalDate.of(1976, 03, 25);
		//TODO: Gelesenes Due Date von Text in local date konvertieren
		//String dueDateAsString = dueDateField.getText();
		
		String dueDateAsStringFromTextField = dueDateField.getText();
		System.out.println(dueDateAsStringFromTextField + "__Date as string from Text Field");		
		LocalDate dueDateAsLocalDate = LocalDate.parse(dueDateAsStringFromTextField);		
		System.out.println(dueDateAsLocalDate + "__Date as string  Parsed from Code");
		
		//Create new task object
		Task task = new Task(taskSummary, requestor, responsible, dueDateAsLocalDate);		
		task.setTaskStatus(taskStatus);	
		
		//Hinzufügen des neuen Task Objekts zur liste
		taskList.add(task);		
		//Daten in persitenzschicht schreiben
		addTaskToPersistenceLayer(task);
		//Daten aus persitenzschicht laden um die DB-ID der neu angelegten Task zu erhalten und in der GUI anzuzeigen
		loadDataFromPersistenceLayer();
		// Tabelle in GUI mit letzem Datenstand aus TaskList neu aufbauen		
		updateGuiDataTable ();	
		clearDataEntryFields();
	}
	
	/**
	 * Action listner für denn "Update" Button. Wird der Action Listener aufgerufen, werden die Felder des Eingabefensters ausgelesen und in ein
	 * Taskobjekt geschrieben. Über die ausgelesene Task ID wird diejenige Task mit dieser ID in der Persistenzschicht und in der Tabelle im
	 * Dashboard, aktualisiert.
	 *   
	 * @param evt
	 */
	private void actionPerformedButtonUpdate(ActionEvent evt) {		
		String updatedTaskIDAsString = taskIDField.getText();
		int taskIDAsInteger = Integer.parseInt(updatedTaskIDAsString);		
		String updatedTaskSummary = taskSummaryField.getText();
		String updatedRequester = requesterField.getText();
		String updatedResponsible = responsibleField.getText();
		
		String dueDateAsStringFromTextField = dueDateField.getText();				
		LocalDate dueDateAsLocalDate = LocalDate.parse(dueDateAsStringFromTextField);
		//System.out.println(dueDateAsStringFromTextField + "__Date as string from Text Field");
		//System.out.println(dueDateAsLocalDate + "__Date as string  Parsed from Code");		
		
		TaskStatus updatedTaskStatus = (TaskStatus) taskStatusComboBox.getSelectedItem();		
		
		//Gets the food object attributes in the array position which corresponds to the selected table row (the array is a "copy" of the table)
		//writes the data from the text fields into this attribute and hence updates the ArrayList
		taskList.get(selectedRow).setTaskSummary(updatedTaskSummary);
		taskList.get(selectedRow).setRequester(updatedRequester);
		taskList.get(selectedRow).setResponsible(updatedResponsible);
		taskList.get(selectedRow).setDueDate(dueDateAsLocalDate);		
		taskList.get(selectedRow).setTaskStatus(updatedTaskStatus);

		//Create new task DTO to hand over to DAO
		Task task = new Task(updatedTaskSummary, updatedRequester, updatedResponsible, dueDateAsLocalDate);
		task.setTaskStatus(updatedTaskStatus);
		
		//Hand data over to DAO
		updatePersistenceLayer(taskIDAsInteger, task);
		// Tabelle in GUI mit letzem Datenstand aus TaskList neu aufbauen
		updateGuiDataTable ();	
				
		clearDataEntryFields();
	}
	
	/**
	 * Leert die Eingabefelster im Tab Task.
	 * @param evt
	 */
	private void actionPerformedButtonClear(ActionEvent evt) {
		clearDataEntryFields();
	}
	
	/////////////  Interakion mit Datenhaltungssschischt  ///////////
	/**
	 * Erstellt ein Abbild der Persistenzschicht in der TaskList über den Aufruf der entsprechenden Methode im TaskService Objekt.	 
	 * @return
	 */
	private List<Task> loadDataFromPersistenceLayer() {
		//Über Schnittstelle zur Persistenzschicht ein Abbild der Persistenzsschicht erstellen 
		taskList = taskService.getAllTasks();
		//TaskListe zurückgeben
		return taskList;
	}
	/**
	 * Fügt der Persistenzschicht, über den Aufruf der entsprechenden Methode im TaskService Objekt, eine Task hinzu. 
	 * @param task => die Task, die der Persistenzschicht hinzugefügt werden soll
	 */	
	private void addTaskToPersistenceLayer(Task task) {
		//Über Schnittstelle zur Persistenzschicht die gewählte Taks hinzufügen
		taskService.addTask(task);				
	}

	/**
	 * Aktualisert in der Persistenzschicht, über den Aufruf der entsprechenden Methode im TaskService Objekt, eine Task. 
	 * @param taskID => Die ID der Task, die aktualsiert werden soll.
	 * @param task => Ein Task Objekt, dass die Daten enhtält, die die aktualisierte Task enthalten soll (enthält keine ID)
	 */
	private void updatePersistenceLayer(int taskID, Task task) {		
		//Über Schnittstelle zur Persistenzschicht die gewählte Taks aktualisieren
		taskService.updateTask(taskID, task);
	}	
	
	
	
	/////////////  Datenfelder in der GUI aktualisieren  ///////////	
	//Datentabelle in GUI an den letzten Stand der TaskList anpassen
	/**
	 * Befüllt die Tabelle im Dashboard mit den Daten aus der TaskList. 
	 * Durch Aufruf dieser Methode, nach einer Änderung an der Tasklist, kann sichergestellt werden, dass die Tabelle im
	 * GUI immer den letztern Stand anzeigt. 
	 */
	private void updateGuiDataTable () {
		// Datenmodel "resetten"
		dtm.setRowCount(0);
		// Datenmodel neu aus aktueller TaskList neu aufbauen
		for (int i = 0; i < taskList.size(); i++) {
			Object[] objs = {taskList.get(i).getTaskID(),
					taskList.get(i).getTaskSummary(),
					taskList.get(i).getRequester(),
					taskList.get(i).getResponsible(),
					taskList.get(i).getDueDate(),
					taskList.get(i).getTaskStatus()};
			dtm.addRow(objs);
		}		
	}
	
	//Dateneingabefelder leeren 
	/**
	 * Leert die Eingabefelder des EingabeTabs 
	 */
	private void clearDataEntryFields() {
		taskIDField.setText("");
		taskSummaryField.setText("");
		requesterField.setText("");
		responsibleField.setText("");
		dueDateField.setText("");
		taskStatusComboBox.setSelectedIndex(0);

	}
	

	

}
