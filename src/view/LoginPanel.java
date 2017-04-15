package view;
/*
 * LoginPanel is a login system with textField for username and password and 2 buttons for login or reset password
 * show View window after successful login or reset password
 * 
 * @author k1631383, k1631145
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginPanel extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6035809420969474543L;
	
	//Fields
	JPanel panel = new JPanel();
	private JLabel username;
	private JLabel password;
	private JTextField jtaUsername;
	private JTextField jtapassword;
	private JButton login;
	private String usrnm="london";
	private String psrwrd="1234";
	private JLabel setL;
	private JTextField field;
	private JButton setP;
	
	/*
	 * Constructor LoginPanel
	 * Sets title of JFrame and default close operation
	 * Initializes widgets
	 */
	public LoginPanel(){
		this.setSize(700, 500);
		this.setTitle("Login System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initWidgets();
		this.pack();
		this.setVisible(true);
	
		
	}
	
	/*
	 * Initializes widgets and sets up layout 
	 */
	
	public void initWidgets(){
		username = new JLabel("Username");
		password = new JLabel("Password");
		jtaUsername = new JTextField();
		jtapassword = new JTextField();
		login = new JButton("Login");
		JLabel setL = new JLabel("set Password");
		//JTextField field = new JTextField();
		JButton setP = new JButton("Reset Password");
		
		/**
		 * Event Listener
		 * Resets password value if username is valid and inform user about successful change
		 * otherwise inform user about wrong username
		 *
		 */
		  setP.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					String k = jtaUsername.getText();
					String  p= jtapassword.getText();
				if(usrnm.equals(k)){
					setPassword(p);
					JOptionPane.showMessageDialog(panel, "Password changed");
					View view = new View();
					view.setVisible(true);
					setVisible(false);
					
				}
				else{
					JOptionPane.showMessageDialog(panel, "Invalid username or password");
				}
				}
			   });
		/**
		 * Adding ActionListener to login button
		 * if username and password are valid, you will login system
		 * make our View visible after login
		 * if not then you will be informed about wrong username or password
		 */
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String usrnm1 = jtaUsername.getText();
			    String psrwrd1 = jtapassword.getText();
				jtapassword.setText(getPassword());
				if(usrnm.equals(usrnm1)&&getPassword().equals(psrwrd1)){
					JOptionPane.showMessageDialog(panel, "Welcome!!!");
					View view = new View();
					view.setVisible(true);
					setVisible(false);
				}
				
				else{
					JOptionPane.showMessageDialog(panel, "Invalid username or password");
				}
				
			}
			
		});
		//Adding labels, buttons and textFields to panel and panel to frame
		panel.setLayout(new GridLayout(3, 2));
		panel.add(username); 
		panel.add(jtaUsername);
		panel.add(password);
		panel.add(jtapassword);
		panel.add(login);
		panel.add(setP);
		this.add(panel);
		

	}
	//method set new password
	public void setPassword(String s){
		psrwrd=s;
	}
	//method return password
	public String getPassword(){
		return psrwrd;
	}

	
}
