import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class frmInWord extends JInternalFrame implements ActionListener
 {
    JPanel InWordPanel, tablePanel, containerPanel;

    JLabel lbSrNo, lbApplicationDate, lbApplicantName, lbCertificateType, lbApplicationID, lbContactNo, lbBirthDate,
            lbReceivedBy, lbRemark, lbStatus;

    JTextField txApplicationDate, txApplicantName, txApplicationID, txContactNo, txBirthDate, txReceivedBy, txRemark,
            txStatus;

    JComboBox cmbSrNo, cmbCertificateType;

    JButton btnSave, btnUpdate, btnViewList, btnListSelected, btnHideList;

    private static JTable tblList;
    private JScrollPane jsp, scrollContainer;
    private static int selectedRow;

    int panelFullWidth = 540;
    int panelHeight = 550;

    frmInWord()
     {
        super("In-Word", true, true, true, true);
        // setSize(600,650);
        // setLocation(0,0);
        setTitle("In-Word");

        setLayout(new BorderLayout());

        // Container Panel with null layout

        containerPanel = new JPanel(null);
        // containerPanel.setPreferredSize(new Dimension(600,650));

        InWordPanel = new JPanel(null);
        InWordPanel.setBounds(20, 20, 540, 550);
        InWordPanel.setBorder(BorderFactory.createTitledBorder("In-Word"));
        containerPanel.add(InWordPanel);

        InWordPanel.setLayout(null);

        lbSrNo = new JLabel("Sr. No. :");
        lbSrNo.setBounds(50, 50, 100, 20);
        InWordPanel.add(lbSrNo);

        cmbSrNo = new JComboBox();
        cmbSrNo.setBounds(170, 50, 200, 20);
        InWordPanel.add(cmbSrNo);
        cmbSrNo.addItem("*");
        cmbSrNo.addActionListener(this);

        lbApplicationDate = new JLabel("Application Date :");
        lbApplicationDate.setBounds(50, 100, 100, 20);
        InWordPanel.add(lbApplicationDate);

        txApplicationDate = new JTextField();
        txApplicationDate.setBounds(170, 100, 200, 20);
        InWordPanel.add(txApplicationDate);

        txApplicationDate.addFocusListener(new FocusAdapter() 
        {
            public void focusLost(FocusEvent e) 
            {
                String input = txApplicationDate.getText().trim();

                try 
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date = LocalDate.parse(input, formatter);
                }
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid date, Please Enter Valid Date Format (dd-MM-yyyy)");
                    txApplicationDate.setText("");
                    txApplicationDate.requestFocus();
                }
            }
        });

        lbApplicantName = new JLabel("Applicant Name : ");
        lbApplicantName.setBounds(50, 150, 100, 20);
        InWordPanel.add(lbApplicantName);

        txApplicantName = new JTextField();
        txApplicantName.setBounds(170, 150, 200, 20);
        InWordPanel.add(txApplicantName);

        txApplicantName.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e) 
            {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') 
                {
                    e.consume();
                }
            }
        });

        lbCertificateType = new JLabel("Certificate Type : ");
        lbCertificateType.setBounds(50, 200, 100, 20);
        InWordPanel.add(lbCertificateType);

        cmbCertificateType = new JComboBox();
        cmbCertificateType.setBounds(170, 200, 200, 20);
        InWordPanel.add(cmbCertificateType);
        cmbCertificateType.addItem("*");
        cmbCertificateType.addItem("Caste Certificate");
        cmbCertificateType.addItem("Domicile");
        cmbCertificateType.addItem("Ration Card Name Reduction/Addition");
        cmbCertificateType.addItem("Income Ceritficate");
        cmbCertificateType.addItem("Ration Card (New/Divided)");
        cmbCertificateType.addItem("Passport");
        cmbCertificateType.addItem("Non-Creamy Layer");
        cmbCertificateType.addItem("Hilly Area Certificate");
        cmbCertificateType.addItem("Police Verification (Character Certificate)");
        cmbCertificateType.addItem("Caste Validity");
        cmbCertificateType.addItem("E.W.S.");
        cmbCertificateType.addItem("Farmer's Certificate (Applicant's Father)");
        cmbCertificateType.addItem("Women Non-Creamy Layer");
        cmbCertificateType.addItem("Shop Act");
        cmbCertificateType.addItem("RTO Driving Licence");
        cmbCertificateType.addItem("Certificate of Small Landholding");
        cmbCertificateType.addItem("Industry Aadhaar");
        cmbCertificateType.addItem("Food License");
        cmbCertificateType.addItem("Pancard");
        cmbCertificateType.addItem("Gazette");

        cmbCertificateType.addFocusListener(new FocusAdapter() 
        {
            public void focusLost(FocusEvent e) 
            {
                txContactNo.requestFocus();
            }
        });

        lbApplicationID = new JLabel("Application I'D : ");
        lbApplicationID.setBounds(50, 250, 100, 20);
        InWordPanel.add(lbApplicationID);

        txApplicationID = new JTextField("0");
        txApplicationID.setBounds(170, 250, 200, 20);
        InWordPanel.add(txApplicationID);
        txApplicationID.setEditable(false);

        txApplicationID.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e) 
            {
                char ch = e.getKeyChar();

                if (ch < '1' || ch > '9') 
                {
                    e.consume();
                    txStatus.setText("Process");
                }
            }
        });

        lbContactNo = new JLabel("Contact No. : ");
        lbContactNo.setBounds(50, 300, 100, 20);
        InWordPanel.add(lbContactNo);

        txContactNo = new JTextField();
        txContactNo.setBounds(170, 300, 200, 20);
        InWordPanel.add(txContactNo);

        txContactNo.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e) 
            {
                String text = txContactNo.getText();
                char ch = e.getKeyChar();

                if (ch < 0 || text.length() >= 10) 
                {
                    e.consume();
                }
            }
        });

        lbBirthDate = new JLabel("Birth Date : ");
        lbBirthDate.setBounds(50, 350, 100, 20);
        InWordPanel.add(lbBirthDate);

        txBirthDate = new JTextField();
        txBirthDate.setBounds(170, 350, 200, 20);
        InWordPanel.add(txBirthDate);

        txBirthDate.addFocusListener(new FocusAdapter() 
        {
            public void focusLost(FocusEvent e) 
            {
                String input = txBirthDate.getText().trim();

                try 
                {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date = LocalDate.parse(input, formatter);
                }
                catch (Exception ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid date, Please Enter Valid Date Format (dd-MM-yyyy)");
                    txBirthDate.setText("");
                    txBirthDate.requestFocus();
                }
            }
        });

        lbReceivedBy = new JLabel("Received By : ");
        lbReceivedBy.setBounds(50, 400, 100, 20);
        InWordPanel.add(lbReceivedBy);

        txReceivedBy = new JTextField();
        txReceivedBy.setBounds(170, 400, 200, 20);
        InWordPanel.add(txReceivedBy);

        txReceivedBy.addKeyListener(new KeyAdapter() 
        {
            public void keyTyped(KeyEvent e) 
            {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') 
                {
                    e.consume();
                }
            }
        });

        lbRemark = new JLabel("Remarks : ");
        lbRemark.setBounds(50, 450, 100, 20);
        InWordPanel.add(lbRemark);

        txRemark = new JTextField();
        txRemark.setBounds(170, 450, 200, 20);
        InWordPanel.add(txRemark);

        lbStatus = new JLabel("Status : ");
        lbStatus.setBounds(50, 500, 100, 20);
        InWordPanel.add(lbStatus);

        txStatus = new JTextField("In");
        txStatus.setBounds(170, 500, 200, 20);
        InWordPanel.add(txStatus);
        txStatus.setEditable(false);

        // Buttons

        btnSave = new JButton("Save");
        btnSave.setBounds(110, 580, 100, 20);
        containerPanel.add(btnSave);
        btnSave.addActionListener(this);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(230, 580, 100, 20);
        containerPanel.add(btnUpdate);
        btnUpdate.addActionListener(this);
        btnUpdate.setEnabled(false);

        btnViewList = new JButton("View List");
        btnViewList.setBounds(350, 580, 100, 20);
        containerPanel.add(btnViewList);
        btnViewList.addActionListener(this);

        btnListSelected = new JButton("List Selected");
        btnListSelected.setBounds(840, 350, 120, 20);
        containerPanel.add(btnListSelected);
        btnListSelected.setVisible(false);
        btnListSelected.addActionListener(this);

        btnHideList = new JButton("Hide List");
        btnHideList.setBounds(1010, 350, 120, 20);
        containerPanel.add(btnHideList);
        btnHideList.addActionListener(this);
        btnHideList.setVisible(false);

        tblList = new JTable(new AbstractTable());
        jsp = new JScrollPane(tblList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(10, 40, 850, 200);

        tablePanel = new JPanel(null);
        tablePanel.setBounds(600, 60, 880, 250);
        tablePanel.setBorder(BorderFactory.createTitledBorder("List"));
        tablePanel.add(jsp);
        tablePanel.setVisible(false);
        containerPanel.add(tablePanel);

        scrollContainer = new JScrollPane(containerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollContainer, BorderLayout.CENTER);

        adjustScrollSize(); // Key method to calculate full content size

        // Maximize on launch
        SwingUtilities.invokeLater(() -> {
            try 
            {
                setMaximum(true);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            animateWipeIn();
        });

        setVisible(true);

        try 
        {
            int i = GlobalClass.id_Reader("Select Max(srno) from inword");
            cmbSrNo.addItem(Integer.toString(i));
            cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);
        }
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Dynamically resize container to fit all children
    private void adjustScrollSize() 
    {
        SwingUtilities.invokeLater(() -> {
            Dimension maxBounds = new Dimension(0, 0);
            for (Component c : containerPanel.getComponents()) 
            {
                Rectangle bounds = c.getBounds();
                maxBounds.width = Math.max(maxBounds.width, bounds.x + bounds.width);
                maxBounds.height = Math.max(maxBounds.height, bounds.y + bounds.height);
            }
            maxBounds.width += 50;
            maxBounds.height += 50;

            containerPanel.setPreferredSize(maxBounds);
            containerPanel.revalidate();
            scrollContainer.revalidate();
            containerPanel.repaint();

        });
    }

    private void animateWipeIn() 
    {
        int centerX = (scrollContainer.getViewport().getWidth() - panelFullWidth) / 2;
        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() 
        {
            int width = 0;

            public void actionPerformed(ActionEvent e) 
            {
                if (width < panelFullWidth) 
                {
                    width += 10;
                    InWordPanel.setBounds(centerX, 20, width, panelHeight);
                    btnSave.setLocation(centerX + 90, 580);
                    btnUpdate.setLocation(centerX + 210, 580);
                    btnViewList.setLocation(centerX + 330, 580);
                    // btnListSelected.setLocation(centerX + 600, 350);
                    // btnHideList.setLocation(centerX + 740, 350);
                    containerPanel.repaint();
                }
                else 
                {
                    InWordPanel.setBounds(centerX, 20, panelFullWidth, panelHeight);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    /*
     * private void animateWipeOut() {
     * Timer timer = new Timer(5, null);
     * timer.addActionListener(new ActionListener() {
     * int width = panelFullWidth;
     * public void actionPerformed(ActionEvent e) {
     * if (width > 0) {
     * width -= 10;
     * int centerX = (scrollContainer.getViewport().getWidth() - width) / 2;
     * InWordPanel.setBounds(centerX, 20, width, panelHeight);
     * btnSave.setLocation(centerX + 90, 580);
     * btnUpdate.setLocation(centerX + 210, 580);
     * btnViewList.setLocation(centerX + 330, 580);
     * // btnListSelected.setLocation(centerX + 600, 350);
     * // btnHideList.setLocation(centerX + 740, 350);
     * containerPanel.repaint();
     * } else {
     * InWordPanel.setBounds(0, 20, 0, panelHeight);
     * timer.stop();
     * }
     * }
     * });
     * timer.start();
     * }
     */

    private void animateWipeInLeftAligned() 
    {
        int startX = 20; // Padding from the left edge
        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() 
        {
            int width = 0;

            public void actionPerformed(ActionEvent e) 
            {
                if (width < panelFullWidth) 
                {
                    width += 10;
                    InWordPanel.setBounds(startX, 20, width, panelHeight);
                    btnSave.setLocation(startX + 90, 580);
                    btnUpdate.setLocation(startX + 210, 580);
                    btnViewList.setLocation(startX + 330, 580);
                    containerPanel.repaint();
                }
                else 
                {
                    InWordPanel.setBounds(startX, 20, panelFullWidth, panelHeight);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    class AbstractTable extends AbstractTableModel 
    {
        private String[] ColumnName = { "SrNo", "ApplicationDate", "ApplicantName", "CertificateType", "ApplicationID",
                "ContactNo", "BirthDate", "ReceivedBy", "Remarks", "Status" };

        private Object[][] data = new Object[50][50];

        public int getColumnCount() 
        {
            return ColumnName.length;
        }

        public int getRowCount() 
        {
            return data.length;
        }

        public String getColumnName(int col) 
        {
            return ColumnName[col];
        }

        public Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }

        public void setValueAt(Object value, int row, int col) 
        {
            data[row][col] = value;
        }
    }

    public static int getSelectedRow() 
    {
        tblList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.ListSelectionModel rowSel = tblList.getSelectionModel();
        rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) 
            {
                if (e.getValueIsAdjusting()) 
                {
                    return;
                }

                javax.swing.ListSelectionModel sel = (ListSelectionModel) e.getSource();

                if (!sel.isSelectionEmpty()) 
                {
                    selectedRow = sel.getMinSelectionIndex();
                }
            }
        });
        return selectedRow;
    }

    void display() 
    {
        try 
        {
            cmbSrNo.setSelectedItem(GlobalClass.rsNavi.getString(1));
        }
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        try 
        {
            if (e.getSource() == btnSave) 
            {
                GlobalClass.Record_Manip("Insert into inword values(" + cmbSrNo.getSelectedItem().toString() + ",'"
                        + txApplicationDate.getText() + "','" + txApplicantName.getText() + "','"
                        + cmbCertificateType.getSelectedItem().toString() + "'," + txApplicationID.getText() + ","
                        + txContactNo.getText() + ",'" + txBirthDate.getText() + "','" + txReceivedBy.getText() + "','"
                        + txRemark.getText() + "','" + txStatus.getText() + "')");
                JOptionPane.showMessageDialog(null, "Record Save");

                int i = GlobalClass.id_Reader("Select Max(srno) from inword");
                cmbSrNo.addItem(Integer.toString(i));
                cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                // cmbSrNo.setSelectedIndex(0);
                txApplicationDate.setText("");
                txApplicantName.setText("");
                cmbCertificateType.setSelectedIndex(0);
                txApplicationID.setText("0");
                txContactNo.setText("");
                txBirthDate.setText("");
                txReceivedBy.setText("");
                txRemark.setText("");
                txStatus.setText("In");

                // txApplicationDate.requestFocus();

                refreshTableData();

            }

            if (e.getSource() == btnUpdate) 
            {
                GlobalClass.Record_Manip("Update inword set ApplicationDate='" + txApplicationDate.getText()
                        + "', ApplicantName='" + txApplicantName.getText() + "', CertificateType='"
                        + cmbCertificateType.getSelectedItem().toString() + "', ApplicationID="
                        + txApplicationID.getText() + ", ContactNo=" + txContactNo.getText() + ", BirthDate='"
                        + txBirthDate.getText() + "', ReceivedBy='" + txReceivedBy.getText() + "', Remarks='"
                        + txRemark.getText() + "', Status='" + txStatus.getText() + "' where SrNo="
                        + cmbSrNo.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Record Update");

                int i = GlobalClass.id_Reader("Select Max(srno) from inword");
                cmbSrNo.addItem(Integer.toString(i));
                cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                txApplicationDate.setText("");
                txApplicantName.setText("");
                cmbCertificateType.setSelectedIndex(0);
                txApplicationID.setText("0");
                txApplicationID.setEditable(false);
                txContactNo.setText("");
                txBirthDate.setText("");
                txReceivedBy.setText("");
                txRemark.setText("");

                btnSave.setEnabled(true);

                // txApplicationDate.requestFocus();

                // To Retext again show

                txStatus.setText("In");

                refreshTableData();

                // To Resize The Panel and Referesh The Table

            }

            if (e.getSource() == btnViewList) 
            {
                // setSize(1500,650);
                // setLocation(0,0);

                // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                // setSize(screenSize.width, screenSize.height);

                int Numrow = 0;

                GlobalClass.show_List("Select * from inword where not (Status='Completed')");

                while (GlobalClass.rs.next()) {
                    tblList.setValueAt(GlobalClass.rs.getString(1).trim(), Numrow, 0);
                    tblList.setValueAt(GlobalClass.rs.getString(2).trim(), Numrow, 1);
                    tblList.setValueAt(GlobalClass.rs.getString(3).trim(), Numrow, 2);
                    tblList.setValueAt(GlobalClass.rs.getString(4).trim(), Numrow, 3);
                    tblList.setValueAt(GlobalClass.rs.getString(5).trim(), Numrow, 4);
                    tblList.setValueAt(GlobalClass.rs.getString(6).trim(), Numrow, 5);
                    tblList.setValueAt(GlobalClass.rs.getString(7).trim(), Numrow, 6);
                    tblList.setValueAt(GlobalClass.rs.getString(8).trim(), Numrow, 7);
                    tblList.setValueAt(GlobalClass.rs.getString(9).trim(), Numrow, 8);
                    tblList.setValueAt(GlobalClass.rs.getString(10).trim(), Numrow, 9);

                    Numrow++;
                }
                tablePanel.setVisible(true);
                btnListSelected.setVisible(true);
                btnHideList.setVisible(true);
                animateWipeInLeftAligned();// Move form left
                adjustScrollSize(); // Recalculate scroll space
                // containerPanel.setPreferredSize(new Dimension(screenSize.width,
                // screenSize.height));
            }

            if (e.getSource() == btnListSelected) 
            {
                int c = Integer.parseInt(tblList.getValueAt(getSelectedRow(), 0).toString());

                cmbSrNo.addItem(Integer.toString(c));

                cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                JOptionPane.showMessageDialog(null, c, "Hello", JOptionPane.ERROR_MESSAGE);

                btnSave.setEnabled(false);

                txStatus.addFocusListener(new FocusAdapter() 
                {
                    public void focusLost(FocusEvent e) 
                    {
                        btnUpdate.requestFocus();
                    }
                });

                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
                txApplicationID.setEditable(true);
                txApplicationID.requestFocus();
            }
            if (e.getSource() == cmbSrNo) 
            {
                if (cmbSrNo.getSelectedItem().toString().equals("*")) 
                {
                    txApplicationDate.setText("");
                    txApplicantName.setText("");
                    cmbCertificateType.setSelectedIndex(0);
                    txApplicationID.setText("");
                    txContactNo.setText("");
                    txBirthDate.setText("");
                    txReceivedBy.setText("");
                    txRemark.setText("");
                }
                else 
                {
                    GlobalClass
                            .record_Reader("Select * from inword where SrNo=" + cmbSrNo.getSelectedItem().toString());

                    while (GlobalClass.rs.next()) 
                    {
                        txApplicationDate.setText(GlobalClass.rs.getString(2));
                        txApplicantName.setText(GlobalClass.rs.getString(3));
                        cmbCertificateType.setSelectedItem(GlobalClass.rs.getString(4));
                        txApplicationID.setText(GlobalClass.rs.getString(5));
                        txContactNo.setText(GlobalClass.rs.getString(6));
                        txBirthDate.setText(GlobalClass.rs.getString(7));
                        txReceivedBy.setText(GlobalClass.rs.getString(8));
                        txRemark.setText(GlobalClass.rs.getString(9));
                        txStatus.setText(GlobalClass.rs.getString(10));
                    }
                }
            }

            if (e.getSource() == btnHideList) 
            {
                tablePanel.setVisible(false);
                btnListSelected.setVisible(false);
                btnHideList.setVisible(false);
                animateWipeIn();
                adjustScrollSize(); // Recalculate scroll space
                // containerPanel.setPreferredSize(new Dimension(600,650));
            }
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void refreshTableData() 
    {
        try 
        {
            GlobalClass.show_List("Select * from inword where not (Status='Completed')");

            int row = 0;
            while (GlobalClass.rs.next()) 
            {
                for (int col = 0; col < 10; col++) 
                {
                    tblList.setValueAt(GlobalClass.rs.getString(col + 1).trim(), row, col);
                }
                row++;
            }

            // Optional: revalidate/repaint UI for safety
            tblList.revalidate();
            tblList.repaint();
        } 
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, "Error loading table: " + ex.getMessage());

        }
    }
}
