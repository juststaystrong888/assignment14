
package view;
/*
 * View is a window with calendar and reminder tabs. Program allow you to store event in calendar list by writing information with full details
 * and to reminder list by adding information after words remind me to.
 * User can delete unnecessary event or reminder by clicking twice on element of list.
 */

//import library
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
/*
 * @author k1631383, k1631145
 */

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8353464886487322301L;
	//Fields
	private JTabbedPane mainPane;
	private JPanel calendarPanel;
	private JTextField textFrame;
	@SuppressWarnings("rawtypes")
	private DefaultListModel calendarModel;
	@SuppressWarnings("rawtypes")
	private JList calendarList;
	private JPanel reminderPanel;
	private JTextField secondTextFrame;
	@SuppressWarnings("rawtypes")
	private DefaultListModel reminderModel;
    @SuppressWarnings("rawtypes")
	private JList reminderList;
	
    /*
     * Constructor View
     *  Sets title of JFrame and default close operation
    * Initializes createView()
     */
    
    public View(){
    	this.setPreferredSize(new Dimension(900,300));
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setVisible(true);
        this.pack();
		createView();
	}
	
    /*
	 * Initializes view and sets up layout 
	 */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public void createView(){
	  
   
    mainPane = new JTabbedPane();
    //creating the calendar tab
    calendarPanel = new JPanel();
    calendarPanel.setLayout(new BorderLayout());
    //text frame for input
    textFrame = new JTextField();
    calendarModel = new DefaultListModel();

    calendarList = new JList(calendarModel);


    calendarPanel.add(calendarList,BorderLayout.NORTH);
    calendarPanel.add(textFrame,BorderLayout.SOUTH);

   /**
    * Adding ActionListener to textFrame
    * split input from textFrame on 3 parts
    * check if p1 string has "on" word 
    * if yes add to certain string:dateTime
    * if p2 has it then add p2 to dateTime
    * if p3 has it then add p2 to dateTime
    * 
    */

    textFrame.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String labelText = textFrame.getText();
        String[] textParts = labelText.split("at");
        String p1 = textParts[0];
        String p2 = textParts[1];
        String p3 = textParts[2];
        String dateTime = "";
        String date = "-";
        String time = "-";
        String location = "-";
        String event = "-";

        if(p1.contains(" on ")){
          dateTime = p1;

        }
        else if(p2.contains(" on ")){
          dateTime = p2;
        }
        else if(p3.contains(" on ")){
          dateTime = p3;
        }
        String[] dateTimeParts = dateTime.split(" on ");
        String dt1 = dateTimeParts[0];
        String dt2 = dateTimeParts[1];

        //Check if dt1 matches to specific pattern then time=dt1 and date=dt2
        if((dt1.matches("((\\s*[01]?[0-9]|\\s*2[0-3]):?[0-5]?[0-9]?)|(((\\s*[0]?[1-9])|\\s*([1][0-2])):?[0-5]?[0-9]?[apAP][mM])")) == true){
          time = dt1;
          date = dt2;

        }
        //if no, then check whether dt2 matches to this pattern then time =dt2 and date=dt1
        else if ((dt2.matches("((\\s*[01]?[0-9]|\\s*2[0-3]):?[0-5]?[0-9]?)|(((\\s*[0]?[1-9])|\\s*([1][0-2])):?[0-5]?[0-9]?[apAP][mM])")) == true){

          time = dt2;
          date = dt1;
        }


        labelText = labelText.replace(dateTime, " ");

        int x = 0;
        for(int i = 0; i< labelText.length();i++){

          if((labelText.charAt(i) == 'a')&&(labelText.charAt(i+1) == 't')){
            x = i;
            continue;
          }
        }
        location = labelText.substring(x+2,labelText.length());
        event = labelText.substring(0,x-3);
       
        //print Statement on console and adding calendarString to calendarModel and refresh mainPane to see changes on calendarList
        
        System.out.println("Event: " + event + " | Date: " + date + " | Time: " + time + " | Location: " + location);
        String calendarString = "Event: " + event + " | Date: " + date + " | Time: " + time + " | Location: " + location;
        calendarModel.addElement(calendarString);
        mainPane.revalidate();
        mainPane.repaint();


      }
    });
/**MouseListener for calendarList
 * remove element from calendarList by clicking twice on element
 * 
 */
    calendarList.addMouseListener(new MouseAdapter() {
    
    	public void mouseClicked(MouseEvent evt) {
          JList list = (JList)evt.getSource();
          if (evt.getClickCount() == 2) {

              // Double-click detected
              int index = list.locationToIndex(evt.getPoint());
              calendarModel.remove(index);
              mainPane.revalidate();
              mainPane.repaint();
          }

      }
    });
    
    //creating the reminder tab
    reminderPanel = new JPanel();

    secondTextFrame = new JTextField();
    reminderModel = new DefaultListModel();

    reminderList = new JList(reminderModel);
    reminderPanel.setLayout(new BorderLayout());
    reminderPanel.add(reminderList,BorderLayout.NORTH);
    reminderPanel.add(secondTextFrame,BorderLayout.SOUTH);
  
    /**
     * ActionListener for reminder textField 
     * adding String from input with 'Remind me" to JList of reminder
     */
    secondTextFrame.addActionListener(new ActionListener() {
      
    	public void actionPerformed(ActionEvent e) {
        String reminderString = "";
        String secondLabelText = secondTextFrame.getText();
        if(secondLabelText.contains("remind me to ")){
          reminderString = secondLabelText.replace("remind me to", "");

        }
        reminderModel.addElement(reminderString);
        mainPane.revalidate();
        mainPane.repaint();
      }
    });
    
    /**
     * MouseListener for reminderList
     * removing element from reminderList
     */
    reminderList.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent evt) {
          JList list = (JList)evt.getSource();
          if (evt.getClickCount() == 2) {

              // Double-click detected
              int index = list.locationToIndex(evt.getPoint());
              reminderModel.remove(index);
              mainPane.revalidate();
              mainPane.repaint();
          }

      }
    });


   //Adding calendarPanel and reminderPanel to to Tab
    mainPane.addTab("Calendar",calendarPanel);
    mainPane.addTab("Reminders",reminderPanel);
    this.add(mainPane);
    

  }
}
