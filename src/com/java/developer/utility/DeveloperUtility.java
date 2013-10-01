package com.java.developer.utility;

import java.awt.EventQueue;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeveloperUtility{

	private JFrame frmDeveloperUtilities;
	private JTextField customDateField;
	private JTextField outputText;
	private JLabel errorLabel;
	private JComboBox<UtilityTypes> utilitySelector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable(){
			public void run() {

				try {
					DeveloperUtility window = new DeveloperUtility();
					window.frmDeveloperUtilities.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeveloperUtility(){

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmDeveloperUtilities = new JFrame();
		frmDeveloperUtilities.setTitle("Developer Utilities");
		frmDeveloperUtilities.setBounds(100, 100, 602, 913);
		frmDeveloperUtilities.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDeveloperUtilities.setResizable(false);
		
		JLabel headerLabel = new JLabel("Developer Utilities");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		
		utilitySelector = new JComboBox<UtilityTypes>();
		utilitySelector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				errorLabel.setText("");	
				resetCustomDateField();
				if(utilitySelector.getSelectedIndex() == 2 || utilitySelector.getSelectedIndex() == 4){
					customDateField.setEnabled(true);
				}
				else{
					customDateField.setEnabled(false);
				}
			}
		});
		utilitySelector.setModel(new DefaultComboBoxModel(UtilityTypes.values()));
		
		customDateField = new JTextField();
		customDateField.setEnabled(false);
		customDateField.setText("YYYY/MM/DD/HH/MM/SS");
		customDateField.setColumns(10);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generate Button clicked");
				performUtilityFunction((UtilityTypes)utilitySelector.getSelectedItem());
			}
		});
		
		outputText = new JTextField();
		outputText.setColumns(10);
		
		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		errorLabel.setForeground(new Color(139, 0, 0));
		errorLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(frmDeveloperUtilities.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(outputText, Alignment.LEADING)
						.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(headerLabel, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
									.addComponent(utilitySelector, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(errorLabel, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
									.addComponent(customDateField, Alignment.LEADING)))))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(headerLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(utilitySelector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(customDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(errorLabel)
						.addComponent(btnNewButton))
					.addGap(18)
					.addComponent(outputText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(681, Short.MAX_VALUE))
		);
		frmDeveloperUtilities.getContentPane().setLayout(groupLayout);
	}
	
	private void performUtilityFunction(UtilityTypes utilityType){
		int[] dateAsInt = null ;
		String customDate = "";
		switch(utilityType){
			case SelectAUtility:
				System.out.println("No utility selected");
				errorLabel.setText("Select a utility");
				break;
			case CurrentTimestampGenerator:
				errorLabel.setText("");
				outputText.setText(CommonUtilities.getCurrentTimestamp());
				break;
			case CustomTimestampGenerator:
				errorLabel.setText("");
				customDate = customDateField.getText();
				if(isNotEmpty(customDate)){
					String[] splitDate = customDate.split("/");
					try{
						dateAsInt = CommonUtilities.convertStringArrayToIntArray(splitDate);
					}catch(NumberFormatException e){
						errorLabel.setText("Provide a valid date");
						return;
					}
					outputText.setText(CommonUtilities.getCustomTimestamp(dateAsInt[0],dateAsInt[1],dateAsInt[2],
																				dateAsInt[3],dateAsInt[4],dateAsInt[5]));				
				}
				
				break;
			case CurrentReverseTimestampGenerator:
				errorLabel.setText("");	
				outputText.setText(CommonUtilities.getCurrentReverseTimestamp());
				break;
			case CustomReverseTimestampGenerator:
				errorLabel.setText("");
				customDate = customDateField.getText();
				if(isNotEmpty(customDate)){
					String[] splitDate = customDate.split("/");
					try{
						dateAsInt = CommonUtilities.convertStringArrayToIntArray(splitDate);
					}catch(NumberFormatException e){
						errorLabel.setText("Provide a valid date");
						return;
					}
				}
				outputText.setText(CommonUtilities.getCustomReverseTimestamp(dateAsInt[0],dateAsInt[1],dateAsInt[2],
																				dateAsInt[3],dateAsInt[4],dateAsInt[5]));
				break;
			case GUIDGenerator:
				errorLabel.setText("");
				outputText.setText(CommonUtilities.generateRandomId());
				break;
			default:
				System.out.println("No utility selected");
				errorLabel.setText("Select a utility");
				break;
		}
	}
	
	private void resetCustomDateField(){
		customDateField.setText("YYYY/MM/DD/HH/MM/SS");
	}
}
