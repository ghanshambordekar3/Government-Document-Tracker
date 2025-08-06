import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;

public class MDIForm extends JFrame implements ActionListener 
{
    JMenuBar mBar;

    JMenu mnuMaster, mnuView, mnuTools;

    JMenuItem mnuInWord, mnuOutWord, mnuApplicationStatus, mnuBackup;

    JDesktopPane Desktop;

    JLabel lbName, lbText;

    MDIForm() 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set main layout
        getContentPane().setLayout(new BorderLayout());

        setTitle("In Word - Out Word");

        mBar = new JMenuBar();
        setJMenuBar(mBar);

        // setLayout(null);

        mnuMaster = new JMenu("Master");
        mBar.add(mnuMaster);
        mnuMaster.setMnemonic(KeyEvent.VK_M); // Shortcut key i.e, Alt + M;

        mnuInWord = new JMenuItem("In-Word");
        mnuMaster.add(mnuInWord);
        mnuInWord.addActionListener(this);

        mnuOutWord = new JMenuItem("Out-Word");
        mnuMaster.add(mnuOutWord);
        mnuOutWord.addActionListener(this);

        mnuView = new JMenu("View");
        mBar.add(mnuView);
        mnuView.setMnemonic(KeyEvent.VK_V); // Shortcut key i.e, Alt + M;

        mnuApplicationStatus = new JMenuItem("Application Status");
        mnuView.add(mnuApplicationStatus);
        mnuApplicationStatus.addActionListener(this);

        mnuTools = new JMenu("Tools");
        mBar.add(mnuTools);
        mnuTools.setMnemonic(KeyEvent.VK_T); // Shortcut key i.e, Alt + M;

        mnuBackup = new JMenuItem("Backup");
        mnuTools.add(mnuBackup);
        mnuBackup.addActionListener(this);

        // Top panel with greeting
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lbText = new JLabel("Hello " + GlobalClass.uName);
        topPanel.add(lbText);
        getContentPane().add(topPanel, BorderLayout.NORTH);

        // Center desktop pane
        Desktop = new JDesktopPane();
        getContentPane().add(Desktop, BorderLayout.CENTER);

        // Footer panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lbName = new JLabel(
                "© 2025 Ghansham Bordekar. All Rights Reserved | Contact: 8849826386 / ghanshambordekar@gmail.com");
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(lbName);
        bottomPanel.add(Box.createHorizontalGlue());

        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);

        // Center desktop pane wrapped in JScrollPane

        // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // setSize(screenSize.width, screenSize.height);

        Desktop = new JDesktopPane();
        // Desktop.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        JScrollPane scrollPane = new JScrollPane(Desktop,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == mnuInWord)
        {
            
        }
        if(e.getSource() == mnuOutWord)
        {
            
        }
    }
}
