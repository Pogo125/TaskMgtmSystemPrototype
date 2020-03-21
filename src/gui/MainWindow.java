package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import logiclayer.TaskService;
/**
 * Das Hauptfenster der GUI wird in dieser Klasse definiert
 * @author Alfa
 *
 */

public class MainWindow extends JFrame {
	
	//Attributes
	/**
	 * Serviceobjekt, dass den Austausch mit der Logikschicht ermöglicht 
	 */
	private TaskService taskService;
	
	// Konstruktor
	/**
	 * Hauptfenster - ein TaskService Objekt wird an die beinhalteten objekte des containers übergeben, damit diese  
	 * eine Schnittstelle zur Datenhaltungsschicht zur Verfügung haben
	 * @param taskService
	 */
	public MainWindow(TaskService taskService) {		
		this.taskService = taskService;		
		setSize(800, 500);
		setTitle("Task Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);		
		Register tabs = new Register(taskService);
		add(tabs);		
	}
}