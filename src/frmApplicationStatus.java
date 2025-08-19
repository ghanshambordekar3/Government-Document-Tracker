import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;

public class frmApplicationStatus extends JInternalFrame implements ActionListener {
    private JLabel lbName, lbMobile;
    private JTextField txName, txMobile;
    private JButton btnSearch, btnSearch1;
    private static JTable tblList;
    private JScrollPane tableScrollPane;
    private JPanel mainPanel;
    private static int selectedRow = -1;

    private JPanel wipeEffectPanel;
    private Timer wipeTimer;
    private int wipeX;

    public frmApplicationStatus() {
        super("Application Status", true, true, true, true);
        setSize(1200, 500);
        setLocation(0, 0);

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        lbName = new JLabel("1) Enter Applicant Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lbName, gbc);

        txName = new JTextField(20);
        gbc.gridx = 1;
        mainPanel.add(txName, gbc);

        txName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (!Character.isLetter(ch) && ch != ' ') {
                    e.consume();
                }
            }
        });

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        gbc.gridx = 2;
        mainPanel.add(btnSearch, gbc);

        lbMobile = new JLabel("2) Enter Applicant Mobile No.:");
        gbc.gridx = 3;
        gbc.gridy = 0;
        mainPanel.add(lbMobile, gbc);

        txMobile = new JTextField(20);
        gbc.gridx = 4;
        mainPanel.add(txMobile, gbc);

        txMobile.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                String text = txMobile.getText();
                char ch = e.getKeyChar();
                if (ch < 0 || text.length() >= 10) {
                    e.consume();
                }
            }
        });

        btnSearch1 = new JButton("Search");
        btnSearch1.addActionListener(this);
        gbc.gridx = 5;
        mainPanel.add(btnSearch1, gbc);

        tblList = new JTable(new AbstractTable());
        tblList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblList.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedRow = tblList.getSelectedRow();
            }
        });

        tableScrollPane = new JScrollPane(tblList,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setPreferredSize(new Dimension(800, 300));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainPanel.add(tableScrollPane, gbc);

        JScrollPane outerScrollPane = new JScrollPane(mainPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new BorderLayout());
        add(outerScrollPane, BorderLayout.CENTER);

        setupGlassPaneWipe();
        display();
    }

    class AbstractTable extends AbstractTableModel {
        private final String[] ColumnName = {
                "SrNo", "ApplicationDate", "ApplicantName", "CertificateType",
                "ApplicationID", "ContactNo", "BirthDate", "ReceivedBy", "Remarks", "Status"
        };
        private final Object[][] data = new Object[50][ColumnName.length];

        public int getColumnCount() {
            return ColumnName.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return ColumnName[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    void display() {
        try {
            int row = 0;
            GlobalClass.show_List("SELECT * FROM inword");
            while (GlobalClass.rs.next() && row < 50) {
                for (int col = 0; col < 10; col++) {
                    String value = GlobalClass.rs.getString(col + 1);
                    tblList.setValueAt(value != null ? value.trim() : "", row, col);
                }
                row++;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        AbstractTable model = (AbstractTable) tblList.getModel();

        if (e.getSource() == btnSearch) {
            try {
                String name = txName.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a name.");
                    return;
                }

                String sql = "SELECT * FROM inword WHERE ApplicantName='" + name + "'";
                GlobalClass.show_List(sql);

                for (int r = 0; r < 50; r++) {
                    for (int c = 0; c < model.getColumnCount(); c++) {
                        model.setValueAt("", r, c);
                    }
                }

                int row = 0;
                while (GlobalClass.rs.next() && row < 50) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        String val = GlobalClass.rs.getString(col + 1);
                        model.setValueAt(val != null ? val.trim() : "", row, col);
                    }
                    row++;
                }

                if (row == 0) {
                    JOptionPane.showMessageDialog(this, "No matching records found.");
                }

                txName.setText("");
                txName.requestFocus();

                startWipe();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (e.getSource() == btnSearch1) {
            try {
                String uid = txMobile.getText().trim();
                GlobalClass.show_List("SELECT * FROM inword WHERE ContactNo='" + uid + "'");

                for (int r = 0; r < 50; r++) {
                    for (int c = 0; c < model.getColumnCount(); c++) {
                        model.setValueAt("", r, c);
                    }
                }

                int row = 0;
                while (GlobalClass.rs.next() && row < 50) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        String val = GlobalClass.rs.getString(col + 1);
                        model.setValueAt(val != null ? val.trim() : "", row, col);
                    }
                    row++;
                }

                if (row == 0) {
                    JOptionPane.showMessageDialog(this, "No matching records found.");
                }

                model.fireTableDataChanged();
                txMobile.setText("");
                txMobile.requestFocus();
                startWipe();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

    }

    private void setupGlassPaneWipe() {
        wipeEffectPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(50, 60, 70, 70));
                g.fillRect(0, 0, wipeX, getHeight());
            }
        };
        wipeEffectPanel.setOpaque(false);

        JRootPane root = getRootPane();
        root.setGlassPane(wipeEffectPanel);
    }

    private void startWipe() {
        wipeX = 0;
        wipeEffectPanel.setVisible(true);

        if (wipeTimer != null && wipeTimer.isRunning()) {
            wipeTimer.stop();
        }

        wipeTimer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                wipeX += 25;
                wipeEffectPanel.repaint();
                if (wipeX >= getWidth()) {
                    wipeTimer.stop();
                    wipeEffectPanel.setVisible(false);
                }
            }
        });

        wipeTimer.start();
    }
}
