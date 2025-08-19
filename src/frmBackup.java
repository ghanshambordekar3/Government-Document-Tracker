import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmBackup extends JInternalFrame implements ActionListener {
    JPanel BackupPanel;

    JLabel lbChooseFolder;

    JTextField txChooseFolder;

    JButton btnBrowse, btnBackup, btnCancel;

    frmBackup() {
        super("Backup", true, true, true, true);

        setSize(600, 350);
        setTitle("Backup");
        setLocation(0, 0);

        setLayout(null);

        BackupPanel = new JPanel();
        BackupPanel.setBounds(20, 20, 540, 250);
        BackupPanel.setBorder(BorderFactory.createTitledBorder("Backup"));
        add(BackupPanel);

        BackupPanel.setLayout(null);

        lbChooseFolder = new JLabel("Choose Backup Folder :");
        lbChooseFolder.setBounds(20, 40, 150, 20);
        BackupPanel.add(lbChooseFolder);

        txChooseFolder = new JTextField();
        txChooseFolder.setBounds(170, 40, 150, 20);
        BackupPanel.add(txChooseFolder);

        btnBrowse = new JButton("Browse");
        btnBrowse.setBounds(350, 40, 100, 20);
        BackupPanel.add(btnBrowse);
        btnBrowse.addActionListener(this);

        btnBackup = new JButton("Backup");
        btnBackup.setBounds(130, 130, 100, 20);
        BackupPanel.add(btnBackup);
        btnBackup.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 130, 100, 20);
        BackupPanel.add(btnCancel);
        btnCancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBrowse) {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = folderChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selected = folderChooser.getSelectedFile();

                txChooseFolder.setText(selected.getAbsolutePath());
            }
        }

        if (e.getSource() == btnBackup) {
            startBackup();
        }

        if (e.getSource() == btnCancel) {
            dispose();
        }
    }

    public void startBackup() {
        String url = "InWordOutWord";
        String user = "root";
        String password = "admin";
        String folder = txChooseFolder.getText().trim();

        if (url.isEmpty() || user.isEmpty() || password.isEmpty() || folder.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Path Does Not Match");
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = folder + File.separator + url + "_" + timestamp + ".sql";
        String dumpPath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    dumpPath,
                    "-u" + user,
                    "-p" + password,
                    url);

            pb.redirectOutput(new File(filePath));
            Process process = pb.start();
            int status = process.waitFor();

            if (status == 0) {
                JOptionPane.showMessageDialog(this, "Backup Successful:\n" + filePath);
            } else {
                JOptionPane.showMessageDialog(this, "Backup Failed, check credentials or system PATH.");
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
