import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;

public class login extends JFrame implements ActionListener 
{
	JPanel loginPanel,RestorePanel;
	
	JLabel lbUserName,lbPassword,lbLine,lbRestore,lbRestoreFile;
	
	JTextField txUserName,txRestoreFile;
	
	JPasswordField txPassword;
	
	JButton btnLogin,btnCancel,btnRestore,btnBrowse,btnrestore;
	
	private String selectedFilePath = null;
	
	final int restorePanelFullWidth = 540;
	final int restorePanelHeight = 200;
	
	login()
	{
		GlobalClass.setConnection();
		
		setSize(600,400);
		setLocation(400,0);
		setTitle("Login");
		
		setLayout(null);
		
		loginPanel = new JPanel();
		loginPanel.setBounds(20,20,540,200);
		loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
		add(loginPanel);
		
		loginPanel.setLayout(null);
		
		lbUserName = new JLabel("UserName :");
		lbUserName.setBounds(130,50,100,20);
		loginPanel.add(lbUserName);
		
		txUserName = new JTextField();
		txUserName.setBounds(230,50,150,20);
		loginPanel.add(txUserName);
		
		lbPassword = new JLabel("Password :");
		lbPassword.setBounds(130,100,100,20);
		loginPanel.add(lbPassword);
		
		txPassword = new JPasswordField();
		txPassword.setBounds(230,100,150,20);
		loginPanel.add(txPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(150,240,100,20);
		add(btnLogin);
		btnLogin.addActionListener(this);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(300,240,100,20);
		add(btnCancel);
		btnCancel.addActionListener(this);
		
		lbLine = new JLabel("_______________________________________________________________________________________");
		lbLine.setBounds(20,260,540,20);
		add(lbLine);
		
		lbRestore = new JLabel("Restore Backup Data : ");
		lbRestore.setBounds(220,300,150,20);
		add(lbRestore);
		
		btnRestore = new JButton("Restore");
		btnRestore.setBounds(230,330,100,20);
		add(btnRestore);
		btnRestore.addActionListener(this);
		
		RestorePanel = new JPanel();
		RestorePanel.setBounds(20,420,540,200);
		RestorePanel.setBorder(BorderFactory.createTitledBorder("Restore"));
		add(RestorePanel);
		RestorePanel.setVisible(false);
		
		RestorePanel.setLayout(null);
		
		lbRestoreFile = new JLabel("Choose Restore File :");
		lbRestoreFile.setBounds(20,50,150,20);
		RestorePanel.add(lbRestoreFile);
		
		txRestoreFile = new JTextField();
		txRestoreFile.setBounds(180,50,150,20);
		RestorePanel.add(txRestoreFile);
		
		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(360,50,100,20);
		RestorePanel.add(btnBrowse);
		btnBrowse.addActionListener(this);
		
		btnrestore = new JButton("Restore");
		btnrestore.setBounds(205,130,100,20);
		RestorePanel.add(btnrestore);
		btnrestore.addActionListener(this);
	}
	
	 private void animateRestorePanelWipeIn() {
	        RestorePanel.setVisible(true);
	        final int startX = 20;
	        final int y = 420;

	        Timer timer = new Timer(5, null);
	        timer.addActionListener(new ActionListener() {
	            int w = 0;
	            public void actionPerformed(ActionEvent e) {
	                if (w < restorePanelFullWidth) {
	                    w += 10;
	                    RestorePanel.setBounds(startX, y, w, restorePanelHeight);
	                    RestorePanel.repaint();
	                } else {
	                    RestorePanel.setBounds(startX, y, restorePanelFullWidth, restorePanelHeight);
	                    timer.stop();
	                }
	            }
	        });
	        timer.start();
	    }
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnRestore)
		{
			setSize(600,670);
			RestorePanel.setVisible(true);
			animateRestorePanelWipeIn();			
		}
		if(e.getSource() == btnLogin)
		{
			if(txUserName.getText().toString().equals("admin"))
			{
				if(txPassword.getText().equals("1234"))
				{
					GlobalClass.uName = txUserName.getText();
					MDIForm obj = new MDIForm();
					obj.show();
					this.hide();
				}
				else
				{
					txPassword.setText("");
					txPassword.requestFocus();
				}
			}
			else
			{
				txUserName.setText("");
				txPassword.setText("");
				txUserName.requestFocus();
				JOptionPane.showMessageDialog(null,"Invalid State");
			}
		}
		
		if(e.getSource() == btnCancel)
		{
			System.exit(0);
		}
		
		if(e.getSource() == btnBrowse)
		{
			JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setAcceptAllFileFilterUsed(false);
		    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("SQL Files", "sql"));			
			int result = fileChooser.showOpenDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION)
			{
				File selected = fileChooser.getSelectedFile();
				selectedFilePath = selected.getAbsolutePath();
				txRestoreFile.setText(selectedFilePath);
			}
		}

		
		if(e.getSource() == btnrestore)
		{
			restore();
		}
	}
	
	public void restore()
	{	
		if (selectedFilePath == null || selectedFilePath.isEmpty()) 
		{
            JOptionPane.showMessageDialog(this, "Please select a backup file first.");
            return;
        }
		
		String url = "InWordOutWord";
		String user = "root";
		String password = "admin";
		String mysqlPath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe";
		
		try
		{		
		     ProcessBuilder pb = new ProcessBuilder(
		             mysqlPath,
		             "-u", user,
		             "-p" + password,
		             url
		    );

		    pb.redirectInput(new File(selectedFilePath)); 
		    pb.redirectErrorStream(true);
		    Process process = pb.start();

		    int status = process.waitFor();

		    if (status == 0) 
		    {
		    	JOptionPane.showMessageDialog(this, "Restore completed successfully!");
		    }
		    else 
		    {
		        JOptionPane.showMessageDialog(this, "Restore failed with exit code: " + status);
		    }		
		 } 
		catch (Exception ex) 
		{
			ex.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
	    }	
	}

}
