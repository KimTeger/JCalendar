package team6.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import team6.Main;
import team6.calculator.Calculator2;
import team6.timetable.Subject;
import team6.timetable.Timetable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ScrollPaneConstants;

public class CalculatorPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	static List<JPanel> registered = new ArrayList<>();
	static List<JComboBox<String>> registered_combo = new ArrayList<>();
	static JComboBox<String> comboBox, comboBox_1;
	static JPanel panel_1;
	static JLabel lblNewLabel_1, lblNewLabel_1_1;
	
	public static JLabel getcredit, getgrade;
	
	public CalculatorPanel() {
		setLayout(null);
		setBounds(0,0,668,414);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(6, 6, 650, 84);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_5_1 = new JLabel("이수 학점");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(225, 19, 61, 16);
		panel.add(lblNewLabel_5_1);
		
		getcredit = new JLabel("49");
		getcredit.setHorizontalAlignment(SwingConstants.CENTER);
		getcredit.setBounds(225, 43, 61, 16);
		panel.add(getcredit);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("/");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setBounds(298, 19, 61, 16);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("/");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(298, 43, 61, 16);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("현재 평점");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setBounds(371, 19, 61, 16);
		panel.add(lblNewLabel_2_2_1);
		
		getgrade = new JLabel("4.50");
		getgrade.setHorizontalAlignment(SwingConstants.CENTER);
		getgrade.setBounds(371, 43, 61, 16);
		panel.add(getgrade);
		
		JLabel lblNewLabel_4 = new JLabel("연도 : ");
		lblNewLabel_4.setBounds(6, 106, 34, 16);
		add(lblNewLabel_4);
		
		ItemListener listener = e->synchronizeTimetable();
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(listener);
		comboBox.setBounds(52, 102, 87, 27);
		comboBox.setModel(new DefaultComboBoxModel<String>(Main.YEARS));
		add(comboBox);
		
		JLabel lblNewLabel_1_2 = new JLabel("학기 : ");
		lblNewLabel_1_2.setBounds(151, 106, 34, 16);
		add(lblNewLabel_1_2);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItemListener(listener);
		comboBox_1.setBounds(197, 102, 87, 27);
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1","2"}));
		add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("학기 이수 학점 : ");
		lblNewLabel.setBounds(514, 106, 95, 16);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("19");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(609, 106, 61, 16);
		add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 134, 650, 27);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("과목명");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(6, 6, 275, 16);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("학점");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(293, 6, 143, 16);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("성적");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(448, 6, 222, 16);
		panel_2.add(lblNewLabel_5);
		
		panel_1 = new JPanel(null);
		JScrollPane scrollPane = new JScrollPane(panel_1);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(6, 162, 650, 246);
		add(scrollPane);
		
		
		JLabel lblNewLabel_8 = new JLabel("학기 평점 : ");
		lblNewLabel_8.setBounds(368, 106, 61, 16);
		add(lblNewLabel_8);
		
		lblNewLabel_1_1 = new JLabel("4.5");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(441, 106, 61, 16);
		add(lblNewLabel_1_1);
		
		synchronizeTimetable();
	}
	public static void synchronizeTimetable() {
		panel_1.removeAll();
		registered.clear();
		registered_combo.clear();
		int year = Integer.parseInt(comboBox.getSelectedItem().toString());
		int semester = Integer.parseInt(comboBox_1.getSelectedItem().toString());
		Timetable table = Main.getTimetable(year, semester);
		Calculator2 cal = new Calculator2();
		int totalcredit = cal.getTotalCredit(table.getSubjects());
		lblNewLabel_1.setText(Integer.toString(totalcredit));
		
		if(totalcredit == 0)
			lblNewLabel_1_1.setText("0");
		else {
			double avg = cal.getAverageGrade(table.getSubjects());
			lblNewLabel_1_1.setText(String.format("%.2f", avg));
		}
		int allcredit = cal.getAllCredit(Main.tables);
		getcredit.setText(Integer.toString(allcredit));
		double allavg = cal.getTotalAverageGrade(Main.tables);
		getgrade.setText(String.format("%.2f", allavg));
		int cnt = 0;
		for(Subject sub : table.getSubjects()) {
			
			JLabel title = new JLabel(sub.getName());
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setBounds(6, 6+(cnt*30), 272, 16);
			
			JLabel credit = new JLabel(sub.getCredit() + "학점");
			credit.setHorizontalAlignment(SwingConstants.CENTER);
			credit.setBounds(290, 6+(cnt*30), 131, 16);
			
			JComboBox<String> combo = new JComboBox<String>();
			combo.setModel(new DefaultComboBoxModel<String>(new String[] {"A+", "A", "B+", "B", "C+", "C", "D+", "D", "F"}));
			combo.setBounds(517, 2+(cnt*30), 70, 27);
			combo.setSelectedItem(sub.getGrade());
			combo.addActionListener(e -> {
				sub.setGrade(combo.getSelectedItem().toString());
				try {
					Main.saveTimetables();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				synchronizeTimetable();
			});
			
			panel_1.add(title);
			panel_1.add(credit);
			panel_1.add(combo);
			registered_combo.add(combo);
			cnt++;
		}
		panel_1.setPreferredSize(new Dimension(676, 6+(cnt*30)+6));
		panel_1.repaint();
		panel_1.revalidate();
		
	}
}
