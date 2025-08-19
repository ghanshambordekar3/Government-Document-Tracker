import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class frmOutWord extends JInternalFrame implements ActionListener {
    JPanel InWordPanel, tablePanel, tablePanel1, containerPanel;

    JLabel lbSrNo, lbDeliveryDate, lbApplicantName, lbCertificateType, lbCertificateNo, lbApplicationID, lbDeliverd,
            lbReceivedBy, lbDeliverdBy, lbRemark;

    JTextField txDeliveryDate, txApplicantName, txCertificateNo, txApplicationID, txDeliverd, txReceivedBy,
            txDeliverdBy, txRemark;

    JComboBox cmbSrNo, cmbCertificateType;

    JButton btnSave, btnUpdate, btnViewList, btnListSelected, btnListSelected1, btnHideList;

    private static JTable tblList, tblList1;
    private JScrollPane jsp, jsp1, scrollContainer;
    private static int selectedRow, selectedRow1;

    int selectedRowIndex = -1;

    // String c = "";

    int c;

    int panelFullWidth = 540;
    int panelHeight = 550;

    frmOutWord() {
        super("Out-Word", true, true, true, true);
        // setSize(600,650);
        // setLocation(0,0);
        setTitle("Out-Word");

        // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // setSize(screenSize.width, screenSize.height-160);
        setLayout(new BorderLayout());

        // Container Panel with null layout

        containerPanel = new JPanel(null);
        // containerPanel.setPreferredSize(new Dimension(screenSize.width,
        // screenSize.height-160));

        InWordPanel = new JPanel(null);
        InWordPanel.setBounds(20, 20, 540, 550); // Above Code Convert into Responsive withour changed the Layout using
                                                 // java and provide full code
        InWordPanel.setBorder(BorderFactory.createTitledBorder("Out-Word"));
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
        cmbSrNo.setEnabled(false);

        lbDeliveryDate = new JLabel("Delivery Date :");
        lbDeliveryDate.setBounds(50, 100, 100, 20);
        InWordPanel.add(lbDeliveryDate);

        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());

        txDeliveryDate = new JTextField(formattedDate);
        txDeliveryDate.setBounds(170, 100, 200, 20);
        txDeliveryDate.setFont(new Font("Arial", Font.PLAIN, 12));
        InWordPanel.add(txDeliveryDate);
        txDeliveryDate.setEditable(false);

        lbApplicantName = new JLabel("Applicant Name : ");
        lbApplicantName.setBounds(50, 150, 100, 20);
        InWordPanel.add(lbApplicantName);

        txApplicantName = new JTextField();
        txApplicantName.setBounds(170, 150, 200, 20);
        InWordPanel.add(txApplicantName);
        txApplicantName.setEditable(false);

        txApplicantName.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') {
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
        cmbCertificateType.setEditable(false);

        lbCertificateNo = new JLabel("Certificate No. : ");
        lbCertificateNo.setBounds(50, 250, 100, 20);
        InWordPanel.add(lbCertificateNo);

        txCertificateNo = new JTextField();
        txCertificateNo.setBounds(170, 250, 200, 20);
        InWordPanel.add(txCertificateNo);
        txCertificateNo.setEditable(false);

        txCertificateNo.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (ch < '0' || ch > '9') {
                    e.consume();
                }
            }
        });

        lbApplicationID = new JLabel("Application I'D : ");
        lbApplicationID.setBounds(50, 300, 100, 20);
        InWordPanel.add(lbApplicationID);

        txApplicationID = new JTextField();
        txApplicationID.setBounds(170, 300, 200, 20);
        InWordPanel.add(txApplicationID);
        txApplicationID.setEditable(false);

        lbDeliverd = new JLabel("Deliverd : ");
        lbDeliverd.setBounds(50, 350, 100, 20);
        InWordPanel.add(lbDeliverd);

        txDeliverd = new JTextField();
        txDeliverd.setBounds(170, 350, 200, 20);
        InWordPanel.add(txDeliverd);
        txDeliverd.setEditable(false);

        txDeliverd.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') {
                    e.consume();
                }
            }
        });

        lbReceivedBy = new JLabel("Received By : ");
        lbReceivedBy.setBounds(50, 400, 100, 20);
        InWordPanel.add(lbReceivedBy);

        txReceivedBy = new JTextField();
        txReceivedBy.setBounds(170, 400, 200, 20);
        InWordPanel.add(txReceivedBy);
        txReceivedBy.setEditable(false);

        txReceivedBy.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') {
                    e.consume();
                }
            }
        });

        lbDeliverdBy = new JLabel("Deliverd By : ");
        lbDeliverdBy.setBounds(50, 450, 100, 20);
        InWordPanel.add(lbDeliverdBy);

        txDeliverdBy = new JTextField();
        txDeliverdBy.setBounds(170, 450, 200, 20);
        InWordPanel.add(txDeliverdBy);
        txDeliverdBy.setEditable(false);

        txDeliverdBy.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetter(ch) && ch != ' ') {
                    e.consume();
                }
            }
        });

        lbRemark = new JLabel("Remarks : ");
        lbRemark.setBounds(50, 500, 100, 20);
        InWordPanel.add(lbRemark);

        txRemark = new JTextField();
        txRemark.setBounds(170, 500, 200, 20);
        InWordPanel.add(txRemark);
        txRemark.setEditable(false);

        // Buttons

        btnSave = new JButton("Save");
        btnSave.setBounds(110, 580, 100, 20);
        containerPanel.add(btnSave);
        btnSave.addActionListener(this);
        btnSave.setEnabled(false);

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
        btnListSelected.setBounds(870, 290, 120, 20);
        containerPanel.add(btnListSelected);
        btnListSelected.setVisible(false);
        btnListSelected.addActionListener(this);

        btnHideList = new JButton("Hide List");
        btnHideList.setBounds(1040, 290, 120, 20);
        containerPanel.add(btnHideList);
        btnHideList.addActionListener(this);
        btnHideList.setVisible(false);

        btnListSelected1 = new JButton("List Selected");
        btnListSelected1.setBounds(950, 580, 150, 20);
        containerPanel.add(btnListSelected1);
        btnListSelected1.addActionListener(this);
        btnListSelected1.setVisible(false);

        // Table 1

        tblList = new JTable(new AbstractTable());
        jsp = new JScrollPane(tblList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setBounds(10, 40, 850, 200);

        tablePanel = new JPanel(null);
        tablePanel.setBounds(600, 320, 880, 250);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Out-Word List"));
        tablePanel.add(jsp);
        tablePanel.setVisible(false);
        containerPanel.add(tablePanel);

        // Table 2

        tblList1 = new JTable(new AbstractTable1());
        jsp1 = new JScrollPane(tblList1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp1.setBounds(10, 40, 850, 200);

        tablePanel1 = new JPanel(null);
        tablePanel1.setBounds(600, 20, 880, 250);
        tablePanel1.setBorder(BorderFactory.createTitledBorder("In-Word List"));
        tablePanel1.add(jsp1);
        tablePanel1.setVisible(false);
        containerPanel.add(tablePanel1);

        scrollContainer = new JScrollPane(containerPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollContainer, BorderLayout.CENTER);

        adjustScrollSize(); // Key method to calculate full content size

        // Maximize on launch
        SwingUtilities.invokeLater(() -> {
            try {
                setMaximum(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            animateWipeIn();
        });

        setVisible(true);

        try {
            int i = GlobalClass.id_Reader("Select Max(srno) from outword");
            cmbSrNo.addItem(Integer.toString(i));
            cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

            btnViewList.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        display1();
    }

    // Dynamically resize container to fit all children
    private void adjustScrollSize() {
        SwingUtilities.invokeLater(() -> {
            Dimension maxBounds = new Dimension(0, 0);
            for (Component c : containerPanel.getComponents()) {
                Rectangle bounds = c.getBounds();
                maxBounds.width = Math.max(maxBounds.width, bounds.x + bounds.width);
                maxBounds.height = Math.max(maxBounds.height, bounds.y + bounds.height);
            }
            containerPanel.setPreferredSize(maxBounds);
            containerPanel.revalidate();
            scrollContainer.revalidate();
            containerPanel.repaint();
        });
    }

    // Animation

    private void animateWipeIn() {
        int centerX = (scrollContainer.getViewport().getWidth() - panelFullWidth) / 2;
        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() {
            int width = 0;

            public void actionPerformed(ActionEvent e) {
                if (width < panelFullWidth) {
                    width += 10;
                    InWordPanel.setBounds(centerX, 20, width, panelHeight);
                    btnSave.setLocation(centerX + 90, 580);
                    btnUpdate.setLocation(centerX + 210, 580);
                    btnViewList.setLocation(centerX + 330, 580);
                    // btnListSelected.setLocation(centerX + 600, 350);
                    // btnHideList.setLocation(centerX + 740, 350);
                    containerPanel.repaint();
                } else {
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

    private void animateWipeInLeftAligned() {
        int startX = 20; // Padding from the left edge
        Timer timer = new Timer(5, null);
        timer.addActionListener(new ActionListener() {
            int width = 0;

            public void actionPerformed(ActionEvent e) {
                if (width < panelFullWidth) {
                    width += 10;
                    InWordPanel.setBounds(startX, 20, width, panelHeight);
                    btnSave.setLocation(startX + 90, 580);
                    btnUpdate.setLocation(startX + 210, 580);
                    btnViewList.setLocation(startX + 330, 580);
                    containerPanel.repaint();
                } else {
                    InWordPanel.setBounds(startX, 20, panelFullWidth, panelHeight);
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    // Table 1

    class AbstractTable extends AbstractTableModel {
        private String[] ColumnName = { "SrNo", "DeliveryDate", "ApplicantName", "CertificateType", "CertificateNo",
                "ApplicationID", "Deliverd", "ReceivedBy", "DeliverdBy", "Remarks" };

        private Object[][] data = new Object[50][50];

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
        }
    }

    public static int getSelectedRow() {
        tblList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.ListSelectionModel rowSel = tblList.getSelectionModel();
        rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                javax.swing.ListSelectionModel sel = (ListSelectionModel) e.getSource();

                if (!sel.isSelectionEmpty()) {
                    selectedRow = sel.getMinSelectionIndex();
                }
            }
        });
        return selectedRow;
    }

    void display() {
        try {
            cmbSrNo.setSelectedItem(GlobalClass.rsNavi.getString(1));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Table 2

    class AbstractTable1 extends AbstractTableModel {
        private String[] ColumnName = { "SrNo", "ApplicationDate", "ApplicantName", "CertificateType", "ApplicationID",
                "ContactNo", "BirthDate", "ReceivedBy", "Remarks", "Status" };

        private Object[][] data = new Object[50][50];

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
        }
    }

    public static int getSelectedRow1() {
        tblList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.ListSelectionModel rowSel = tblList1.getSelectionModel();
        rowSel.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }

                javax.swing.ListSelectionModel sel = (ListSelectionModel) e.getSource();

                if (!sel.isSelectionEmpty()) {
                    selectedRow1 = sel.getMinSelectionIndex();
                }
            }
        });
        return selectedRow1;
    }

    void display1() {
        try {
            String p = "Process";

            int Numrow = 0;

            GlobalClass.show_List("Select * from inword where Status='" + p + "'");

            while (GlobalClass.rs.next()) {
                tblList1.setValueAt(GlobalClass.rs.getString(1).trim(), Numrow, 0);
                tblList1.setValueAt(GlobalClass.rs.getString(2).trim(), Numrow, 1);
                tblList1.setValueAt(GlobalClass.rs.getString(3).trim(), Numrow, 2);
                tblList1.setValueAt(GlobalClass.rs.getString(4).trim(), Numrow, 3);
                tblList1.setValueAt(GlobalClass.rs.getString(5).trim(), Numrow, 4);
                tblList1.setValueAt(GlobalClass.rs.getString(6).trim(), Numrow, 5);
                tblList1.setValueAt(GlobalClass.rs.getString(7).trim(), Numrow, 6);
                tblList1.setValueAt(GlobalClass.rs.getString(8).trim(), Numrow, 7);
                tblList1.setValueAt(GlobalClass.rs.getString(9).trim(), Numrow, 8);
                tblList1.setValueAt(GlobalClass.rs.getString(10).trim(), Numrow, 9);

                Numrow++;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnSave) {
                GlobalClass.Record_Manip("Insert into outword values(" + cmbSrNo.getSelectedItem().toString() + ",'"
                        + txDeliveryDate.getText() + "','" + txApplicantName.getText() + "','"
                        + cmbCertificateType.getSelectedItem().toString() + "'," + txCertificateNo.getText() + ","
                        + txApplicationID.getText() + ",'" + txDeliverd.getText() + "','" + txReceivedBy.getText()
                        + "','" + txDeliverdBy.getText() + "','" + txRemark.getText() + "')");
                JOptionPane.showMessageDialog(null, "Record Save");

                // Update txStatus in the UI table directly
                int txStatusColumnIndex = 9;

                if (selectedRowIndex >= 0 && selectedRowIndex < tblList1.getRowCount()) {
                    tblList1.setValueAt("Completed", selectedRowIndex, txStatusColumnIndex);

                    // Update MySQL inword table's Status to 'Completed'

                    String selectedSrNo = tblList1.getValueAt(selectedRowIndex, 0).toString();
                    GlobalClass.Record_Manip("Update inword set Status='Completed' Where SrNo='" + selectedSrNo + "'");
                }

                /*
                 * // ✅ Remove selected row only from tblList1 (UI table)
                 * if (selectedRowIndex >= 0 && selectedRowIndex < tblList1.getRowCount()) {
                 * for (int col = 0; col < tblList1.getColumnCount(); col++) {
                 * tblList1.setValueAt("", selectedRowIndex, col); // Clear row data
                 * }
                 * }
                 */

                updateTblListSorted();

                int i = GlobalClass.id_Reader("Select Max(srno) from outword");
                cmbSrNo.addItem(Integer.toString(i));
                cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                // cmbSrNo.setSelectedIndex(0);
                // txDeliveryDate.setText("");
                txApplicantName.setText("");
                cmbCertificateType.setSelectedIndex(0);
                txCertificateNo.setText("");
                txApplicationID.setText("");
                txDeliverd.setText("");
                txReceivedBy.setText("");
                txDeliverdBy.setText("");
                txRemark.setText("");

                txDeliveryDate.requestFocus();

                txApplicantName.setEditable(false);
                cmbCertificateType.setEditable(false);
                txCertificateNo.setEditable(false);
                txApplicationID.setEditable(false);
                txDeliverd.setEditable(false);
                txReceivedBy.setEditable(false);
                txDeliverdBy.setEditable(false);
                txRemark.setEditable(false);

                btnSave.setEnabled(false);
                // btnUpdate.setEnabled(false);

                refreshTableData();

            }

            if (e.getSource() == btnUpdate) {
                GlobalClass.Record_Manip("Update outword set DeliveryDate='" + txDeliveryDate.getText()
                        + "', ApplicantName='" + txApplicantName.getText() + "', CertificateType='"
                        + cmbCertificateType.getSelectedItem().toString() + "', CertificateNo="
                        + txCertificateNo.getText() + ", ApplicationID=" + txApplicationID.getText() + ", Deliverd='"
                        + txDeliverd.getText() + "', ReceivedBy='" + txReceivedBy.getText() + "', DeliverdBy='"
                        + txDeliverdBy.getText() + "', Remarks='" + txRemark.getText() + "' where SrNo="
                        + cmbSrNo.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, "Record Update");

                int i = GlobalClass.id_Reader("Select Max(srno) from outword");
                cmbSrNo.addItem(Integer.toString(i));
                cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                txDeliveryDate.setText("");
                txApplicantName.setText("");
                cmbCertificateType.setSelectedIndex(0);
                txCertificateNo.setText("");
                txApplicationID.setText("");
                txDeliverd.setText("");
                txReceivedBy.setText("");
                txDeliverdBy.setText("");
                txRemark.setText("");

                txDeliveryDate.requestFocus();

                refreshTableData();
            }

            if (e.getSource() == btnViewList) {
                // setSize(1500,650);
                // setLocation(0,0);

                // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                // setSize(screenSize.width, screenSize.height-160);

                int Numrow = 0;

                GlobalClass.show_List("Select * from outword");

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
                tablePanel1.setVisible(true);
                btnListSelected.setVisible(true);
                btnHideList.setVisible(true);
                btnListSelected1.setVisible(true);

                animateWipeInLeftAligned();// Move form left
                adjustScrollSize(); // Recalculate scroll space

                // containerPanel.setPreferredSize(new Dimension(screenSize.width,
                // screenSize.height-160));

            }

            if (e.getSource() == btnListSelected) {
                selectedRowIndex = tblList1.getSelectedRow();

                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row first");
                } else {
                    c = Integer.parseInt(tblList1.getValueAt(getSelectedRow1(), 0).toString());

                    cmbSrNo.addItem(Integer.toString(c));

                    cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                    GlobalClass
                            .record_Reader("Select * from inword where SrNo=" + cmbSrNo.getSelectedItem().toString());

                    while (GlobalClass.rs.next()) {
                        txApplicantName.setText(GlobalClass.rs.getString(3));
                        cmbCertificateType.setSelectedItem(GlobalClass.rs.getString(4));
                        txApplicationID.setText(GlobalClass.rs.getString(5));
                    }

                    JOptionPane.showMessageDialog(null, c, "Hello", JOptionPane.ERROR_MESSAGE);

                    btnUpdate.setEnabled(false);
                }

                txApplicantName.setEditable(true);
                cmbCertificateType.setEditable(true);
                txCertificateNo.setEditable(true);
                txApplicationID.setEditable(true);
                txDeliverd.setEditable(true);
                txReceivedBy.setEditable(true);
                txDeliverdBy.setEditable(true);
                txRemark.setEditable(true);

                btnSave.setEnabled(true);
                // btnUpdate.setEnabled(true);

                txCertificateNo.requestFocus();

                txRemark.addFocusListener(new FocusAdapter() {
                    public void focusLost(FocusEvent e) {
                        btnSave.requestFocus();
                    }
                });
            }

            if (e.getSource() == btnListSelected1) {
                selectedRowIndex = tblList.getSelectedRow();

                if (selectedRowIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row first");
                } else {
                    c = Integer.parseInt(tblList.getValueAt(getSelectedRow(), 0).toString());

                    cmbSrNo.addItem(Integer.toString(c));

                    cmbSrNo.setSelectedIndex(cmbSrNo.getItemCount() - 1);

                    GlobalClass
                            .record_Reader("Select * from outword where SrNo=" + cmbSrNo.getSelectedItem().toString());

                    while (GlobalClass.rs.next()) {
                        txDeliveryDate.setText(GlobalClass.rs.getString(2));
                        txApplicantName.setText(GlobalClass.rs.getString(3));
                        cmbCertificateType.setSelectedItem(GlobalClass.rs.getString(4));
                        txCertificateNo.setText(GlobalClass.rs.getString(5));
                        txApplicationID.setText(GlobalClass.rs.getString(6));
                        txDeliverd.setText(GlobalClass.rs.getString(7));
                        txReceivedBy.setText(GlobalClass.rs.getString(8));
                        txDeliverdBy.setText(GlobalClass.rs.getString(9));
                        txRemark.setText(GlobalClass.rs.getString(10));
                    }

                    JOptionPane.showMessageDialog(null, c, "Hello", JOptionPane.ERROR_MESSAGE);

                    txApplicantName.setEditable(true);
                    cmbCertificateType.setEditable(true);
                    txCertificateNo.setEditable(true);
                    txApplicationID.setEditable(true);
                    txDeliverd.setEditable(true);
                    txReceivedBy.setEditable(true);
                    txDeliverdBy.setEditable(true);
                    txRemark.setEditable(true);

                    btnSave.setEnabled(false);
                    btnUpdate.setEnabled(true);

                    // btnUpdate.setEnabled(true);
                }
            }
            if (e.getSource() == cmbSrNo) {
                if (cmbSrNo.getSelectedItem().toString().equals("*")) {
                    txDeliveryDate.setText("");
                    txApplicantName.setText("");
                    cmbCertificateType.setSelectedIndex(0);
                    txApplicationID.setText("");
                    txDeliverd.setText("");
                    txReceivedBy.setText("");
                    txDeliverdBy.setText("");
                    txRemark.setText("");
                }
            }

            if (e.getSource() == btnHideList) {
                // setSize(600,650);
                // setLocation(0,0);
                tablePanel.setVisible(false);
                btnListSelected.setVisible(false);
                btnHideList.setVisible(false);
                tablePanel1.setVisible(false);
                btnListSelected1.setVisible(false);
                animateWipeIn();
                adjustScrollSize();
                // containerPanel.setPreferredSize(new Dimension(600,650));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void updateTblListSorted() {
        try {
            int row = 0;
            GlobalClass.record_Reader("SELECT * FROM outword ORDER BY SrNo ASC");

            while (GlobalClass.rs.next()) {
                for (int col = 0; col < 10; col++) {
                    tblList.setValueAt(GlobalClass.rs.getString(col + 1).trim(), row, col);
                }
                row++;
            }

            // Clear remaining old rows if any
            for (int i = row; i < tblList.getRowCount(); i++) {
                for (int j = 0; j < tblList.getColumnCount(); j++) {
                    tblList.setValueAt("", i, j);
                }
            }

            tablePanel.setVisible(true);
            containerPanel.setPreferredSize(new Dimension(1550, 650));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating sorted Outword table: " + e.getMessage());
        }
    }

    public void refreshTableData() {
        try {
            GlobalClass.show_List("SELECT * FROM outword");

            int row = 0;
            while (GlobalClass.rs.next()) {
                for (int col = 0; col < 10; col++) {
                    tblList.setValueAt(GlobalClass.rs.getString(col + 1).trim(), row, col);
                }
                row++;
            }

            // Optional: revalidate/repaint UI for safety
            tblList.revalidate();
            tblList.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading table: " + ex.getMessage());

        }
    }

}
