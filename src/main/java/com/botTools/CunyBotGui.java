package com.botTools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import org.openqa.selenium.WebDriver;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.border.MatteBorder;

public class CunyBotGui {

	private InputVars vars = new InputVars();
	
	private JFrame frmCunyBot;
	private JTextField textFieldCollegeName;
	private JTextField textFieldCollegeTerm;
	private JTextField textFieldMajorName;
	private JTextField textFieldClassNumbers;
	private JButton btnUpdate;
	private JButton btnStart;
	private JButton btnStop;
	private JCheckBox chckbxReload;
	private JCheckBox chckbxHornOnorOff;
	private JLabel lblNewLabel;
	private JSpinner spinnerReloadSecs;
	private JTextPane textPaneConsole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CunyBotGui window = new CunyBotGui();
					window.frmCunyBot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CunyBotGui() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCunyBot = new JFrame();
		frmCunyBot.setIconImage(Toolkit.getDefaultToolkit().getImage(CunyBotGui.class.getResource("/com/resources/upEwUCnq_400x400.jpg")));
		frmCunyBot.setTitle("CunyClassesOpenBot By Sim");
		frmCunyBot.setBounds(100, 100, 484, 409);
		frmCunyBot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblCollegeName = new JLabel("College Name:");
		
		textFieldCollegeName = new JTextField();
		textFieldCollegeName.setToolTipText("Ex. John Jay");
		textFieldCollegeName.setColumns(10);
		
		JLabel lblCollegeterm = new JLabel("College Term:");
		
		textFieldCollegeTerm = new JTextField();
		textFieldCollegeTerm.setToolTipText("Ex. 2020 Summer Term");
		textFieldCollegeTerm.setColumns(10);
		
		JLabel lblMajor = new JLabel("Major Name:");
		
		textFieldMajorName = new JTextField();
		textFieldMajorName.setToolTipText("Ex. Computer Science");
		textFieldMajorName.setColumns(10);
		
		JLabel lblClassesNumber = new JLabel("Class Numbers:");
		
		textFieldClassNumbers = new JTextField();
		textFieldClassNumbers.setToolTipText("Ex.123, 456, 6587, 698, 69");
		textFieldClassNumbers.setColumns(10);
		
		chckbxReload = new JCheckBox("Reload");
		chckbxReload.setSelected(true);
		chckbxReload.setToolTipText("Reloads the same page.");
		
		chckbxHornOnorOff = new JCheckBox("HornOnOrOff");
		chckbxHornOnorOff.setToolTipText("To make a horn sound when the class is open.");
		chckbxHornOnorOff.setSelected(true);
		
		btnUpdate = new JButton("Update");
		
		
		btnStop = new JButton("Stop");
		
		
		btnStart = new JButton("Start");
		
		lblNewLabel = new JLabel("Reload Secs:");
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(vars.getReloadsecs(), 5, 120,1);
		spinnerReloadSecs = new JSpinner(model1);
		spinnerReloadSecs.setToolTipText("Min: 5 sec, Max: 120 secs");
		
		JScrollPane scrollPaneConsole = new JScrollPane();
		
		btnClear = new JButton("Clear");
		
		GroupLayout ctpMain = new GroupLayout(frmCunyBot.getContentPane());
		ctpMain.setHorizontalGroup(
			ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(ctpMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(ctpMain.createParallelGroup(Alignment.LEADING)
						.addGroup(ctpMain.createSequentialGroup()
							.addGroup(ctpMain.createParallelGroup(Alignment.LEADING)
								.addGroup(ctpMain.createSequentialGroup()
									.addComponent(lblClassesNumber, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldClassNumbers, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
								.addGroup(ctpMain.createSequentialGroup()
									.addGroup(ctpMain.createParallelGroup(Alignment.LEADING)
										.addGroup(ctpMain.createSequentialGroup()
											.addComponent(lblCollegeName)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldCollegeName, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
										.addGroup(ctpMain.createSequentialGroup()
											.addComponent(lblCollegeterm)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldCollegeTerm, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
										.addGroup(ctpMain.createSequentialGroup()
											.addComponent(lblMajor, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldMajorName, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(ctpMain.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxReload)
										.addComponent(chckbxHornOnorOff)
										.addGroup(ctpMain.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(spinnerReloadSecs, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
									.addGap(47)))
							.addGap(33))
						.addGroup(ctpMain.createSequentialGroup()
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnStop, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
							.addComponent(btnClear))))
				.addComponent(scrollPaneConsole, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
		);
		ctpMain.setVerticalGroup(
			ctpMain.createParallelGroup(Alignment.LEADING)
				.addGroup(ctpMain.createSequentialGroup()
					.addGap(12)
					.addGroup(ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCollegeName)
						.addComponent(textFieldCollegeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxReload))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCollegeterm)
						.addComponent(textFieldCollegeTerm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(spinnerReloadSecs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(ctpMain.createParallelGroup(Alignment.LEADING)
						.addGroup(ctpMain.createSequentialGroup()
							.addGap(9)
							.addComponent(lblMajor))
						.addGroup(ctpMain.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(ctpMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldMajorName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxHornOnorOff))))
					.addGap(11)
					.addGroup(ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClassesNumber, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldClassNumbers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(ctpMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate)
						.addComponent(btnStart)
						.addComponent(btnStop)
						.addComponent(btnClear))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneConsole, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
		);
		
		textPaneConsole = new JTextPane();
		textPaneConsole.setEditable(false);
		textPaneConsole.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneConsole.setViewportView(textPaneConsole);
		frmCunyBot.getContentPane().setLayout(ctpMain);
	}
	
	///////////////////////////////////////////////////////
	///THIS METHOD CONTAINS ALL OF THE CODE CREATING EVENTS
	///////////////////////////////////////////////////////
	private WebDriver driver;
	private JButton btnClear;
	private void createEvents() {
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						vars.setCollegename(textFieldCollegeName.getText());
						appendToPane(textPaneConsole, "College Name: " +vars.getCollegename(), Color.BLACK, true);
						
						vars.setTerm(textFieldCollegeTerm.getText());
						appendToPane(textPaneConsole, "Term: " +vars.getTerm(), Color.BLACK, true);
						
						vars.setMajorname(textFieldMajorName.getText());
						appendToPane(textPaneConsole, "Major: " +vars.getMajorname(), Color.BLACK, true);
						
						vars.setClasses(textFieldClassNumbers.getText());
						appendToPane(textPaneConsole, "Class Numbers: " +vars.getClasses(), Color.BLACK, true);
						
						vars.setReload(chckbxReload.isSelected());
						appendToPane(textPaneConsole, "Reload: " +vars.getReload(), Color.BLACK, true);
						
						vars.setHornOnOrOff(chckbxHornOnorOff.isSelected());
						appendToPane(textPaneConsole, "HornOnorOff: " +vars.getHornOnOrOff(), Color.BLACK, true);
						
						vars.setReloadsecs((Integer)spinnerReloadSecs.getValue());
						appendToPane(textPaneConsole, "ReloadSecs: " +vars.getReloadsecs(), Color.BLACK, true);
						return null;
					}
					
				};
				
				worker.execute();
	
			//	JOptionPane.showMessageDialog(null, vars.getReloadsecs());
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						vars.setCollegename(textFieldCollegeName.getText());
						
						vars.setTerm(textFieldCollegeTerm.getText());
						
						vars.setMajorname(textFieldMajorName.getText());
						
						vars.setClasses(textFieldClassNumbers.getText());
						
						vars.setReload(chckbxReload.isSelected());
						
						vars.setHornOnOrOff(chckbxHornOnorOff.isSelected());
						
						vars.setReloadsecs((Integer)spinnerReloadSecs.getValue());
						
						appendToPane(textPaneConsole, "Starting the bot!", Color.BLACK, true);
						driver = AutoFuncs.intilizeChromeAndGetWebDriver(textPaneConsole);
						appendToPane(textPaneConsole, "Program Is started!", Color.green, true);
						
						AutoFuncs.navigatetothesite(driver, vars.getCollegename(), vars.getTerm(), vars.getMajorname(), textPaneConsole);
						AutoFuncs.getclassesAndReload(driver, vars.getReload(), vars.getHornOnOrOff(), false, vars.getReloadsecs(), vars.getClassesArray(), textPaneConsole);
						return null;
					}
					
				};
				
				worker.execute();
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AutoFuncs.stoptheprogram(driver);
				appendToPane(textPaneConsole, "Stoped!", Color.RED, true);
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPaneConsole.setEditable(true);
				textPaneConsole.setText("");
				textPaneConsole.setEditable(false);
			}
		});
	}
	
	void appendToPane(JTextPane tp, String msg, Color c, Boolean bold)
    {
		LocalDateTime now = LocalDateTime.now();
		String formatHHmm = now.format(DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH));
		
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Roboto");
        aset = sc.addAttribute(aset, StyleConstants.Bold, bold);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        tp.setEditable(true);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(formatHHmm +": "+msg+"\n");
        tp.setEditable(false);
//        StyledDocument doc = tp.getStyledDocument();
//        try {
//			doc.insertString(doc.getLength(), msg + "\n", aset);
//			tp.setEditable(true);
//			tp.setEditable(false);
//			
//		} catch (BadLocationException e) {
//			e.printStackTrace();
//			
//		}
    }
}
