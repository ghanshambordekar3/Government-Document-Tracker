import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class login extends JFrame implements ActionListener 
{

    JPanel loginPanel, restorePanel;
    JLabel lbUserName, lbPassword, lbRestoreFolder, lbLine, lbRestoreData;
    JTextField txUserName, txFolder;
    JPasswordField txPassword;
    JButton btnLogin, btnCancel, btnRestoreUI, btnBrowse, btnRestore;
    private String selectedFilePath = null;

    public login() 
    {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocation(400, 0);
        setLocationRelativeTo(null);

        // Container Panel with GridBagLayout
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Login Info Panel
        loginPanel = new JPanel(null);
        loginPanel.setPreferredSize(new Dimension(530, 200));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login State"));

        lbUserName = new JLabel("UserName");
        lbUserName.setBounds(150, 50, 100, 20);
        loginPanel.add(lbUserName);

        txUserName = new JTextField();
        txUserName.setBounds(250, 50, 100, 20);
        loginPanel.add(txUserName);

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(150, 100, 100, 20);
        loginPanel.add(lbPassword);

        txPassword = new JPasswordField();
        txPassword.setBounds(250, 100, 100, 20);
        loginPanel.add(txPassword);

        // Add login panel to container
        gbc.gridx = 0;
        gbc.gridy = 0;
        container.add(loginPanel, gbc);

        // Buttons Panel
        JPanel btnPanel = new JPanel(null);
        btnPanel.setPreferredSize(new Dimension(530, 80));

        btnLogin = new JButton("Login");
        btnLogin.setBounds(125, 10, 100, 25);
        btnLogin.addActionListener(this);
        btnPanel.add(btnLogin);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(275, 10, 100, 25);
        btnCancel.addActionListener(this);
        btnPanel.add(btnCancel);

        gbc.gridy++;
        container.add(btnPanel, gbc);

        // Line Label
        lbLine = new JLabel("________________________________________________________________________________________");
        gbc.gridy++;
        container.add(lbLine, gbc);

        // Restore Label + Button
        JPanel restoreTrigger = new JPanel(null);
        restoreTrigger.setPreferredSize(new Dimension(530, 60));

        lbRestoreData = new JLabel("Restore Backup Data:");
        lbRestoreData.setBounds(190, 5, 200, 20);
        restoreTrigger.add(lbRestoreData);

        btnRestoreUI = new JButton("Restore");
        btnRestoreUI.setBounds(200, 30, 100, 25);
        btnRestoreUI.addActionListener(this);
        restoreTrigger.add(btnRestoreUI);

        gbc.gridy++;
        container.add(restoreTrigger, gbc);

        // Restore Panel
        restorePanel = new JPanel(null);
        restorePanel.setPreferredSize(new Dimension(530, 180));
        restorePanel.setBorder(BorderFactory.createTitledBorder("Restore Data"));
        restorePanel.setVisible(false);

        lbRestoreFolder = new JLabel("Choose Restore File:");
        lbRestoreFolder.setBounds(40, 40, 150, 20);
        restorePanel.add(lbRestoreFolder);

        txFolder = new JTextField();
        txFolder.setBounds(190, 40, 160, 20);
        txFolder.setEditable(false);
        restorePanel.add(txFolder);

        btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(370, 40, 100, 20);
        btnBrowse.addActionListener(this);
        restorePanel.add(btnBrowse);

        btnRestore = new JButton("Restore Now");
        btnRestore.setBounds(210, 100, 120, 25);
        btnRestore.addActionListener(this);
        restorePanel.add(btnRestore);

        gbc.gridy++;
        container.add(restorePanel, gbc);

        // Wrap the container in a scroll pane
        JScrollPane scroll = new JScrollPane(container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scroll);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String u = txUserName.getText().trim();
            String p = new String(txPassword.getPassword());
            if (u.equals("admin") && p.equals("1234")) {

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
                txUserName.setText("");
                txPassword.setText("");
                txUserName.requestFocus();
            }
        }

        if (e.getSource() == btnCancel) {
            System.exit(0);
        }

        if (e.getSource() == btnRestoreUI) {
            restorePanel.setVisible(true);
            pack(); // resize frame to fit new content
        }

        if (e.getSource() == btnBrowse) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("SQL Files", "sql"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selected = fileChooser.getSelectedFile();
                selectedFilePath = selected.getAbsolutePath();
                txFolder.setText(selectedFilePath);
            }
        }

        if (e.getSource() == btnRestore) {
            restore();
        }
    }

    public void restore() {
        if (selectedFilePath == null || selectedFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a backup file first.");
            return;
        }

        String db = "ShradaSchool";
        String user = "root";
        String pwd = "admin";
        String mysql = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe";

        try {

            ProcessBuilder pb = new ProcessBuilder(mysql, "-u" + user, "-p" + pwd, db);
            pb.redirectInput(new File(selectedFilePath));
            pb.redirectErrorStream(true);

            Process process = pb.start();
            int result = process.waitFor();
            if (result == 0)
                JOptionPane.showMessageDialog(this, "Restore completed successfully!");
            else
                JOptionPane.showMessageDialog(this, "Restore failed with exit code: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(login::new);
    }
}

