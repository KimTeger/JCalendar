package team6.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team6.Main;
import team6.schedule.Schedule;
import team6.timetable.Time;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddScheduleFrame extends JFrame {

	private int year;
	private int month;
	private int date;
	
	private JPanel contentPane;
	private JTextField schedulename;
	private JTextField location;

	/**
	 * Create the frame.
	 */
	public AddScheduleFrame(int year, int month, int date) {
		this.year = year;
		this.month = month;
		this.date = date;
		setBounds(100, 100, 300, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("이름 : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(6, 11, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("날짜 : ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(6, 76, 61, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox start_year = new JComboBox();
		start_year.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025"}));
		start_year.setBounds(68, 72, 82, 27);
		start_year.setSelectedItem(Integer.toString(year));
		contentPane.add(start_year);
		
		JComboBox start_month = new JComboBox();
		start_month.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		start_month.setBounds(148, 72, 61, 27);
		start_month.setSelectedItem(Integer.toString(month));
		contentPane.add(start_month);
		
		JComboBox start_date = new JComboBox();
		start_date.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		start_date.setBounds(207, 72, 61, 27);
		start_date.setSelectedItem(Integer.toString(date));
		contentPane.add(start_date);
		
		schedulename = new JTextField();
		schedulename.setBounds(68, 6, 226, 26);
		contentPane.add(schedulename);
		schedulename.setColumns(10);
		
		JLabel lblNewLabel_2_2 = new JLabel("위치 : ");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_2.setBounds(6, 44, 61, 16);
		contentPane.add(lblNewLabel_2_2);
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(68, 39, 226, 26);
		contentPane.add(location);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("시작 : ");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(6, 108, 61, 16);
		contentPane.add(lblNewLabel_2_1_1);
		
		JComboBox start_hour = new JComboBox();
		start_hour.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		start_hour.setBounds(68, 104, 82, 27);
		contentPane.add(start_hour);
		
		JComboBox start_min = new JComboBox();
		start_min.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		start_min.setBounds(158, 104, 82, 27);
		contentPane.add(start_min);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("종료 : ");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(6, 135, 61, 16);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		JComboBox end_hour = new JComboBox();
		end_hour.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		end_hour.setBounds(68, 131, 82, 27);
		contentPane.add(end_hour);
		
		JComboBox end_min = new JComboBox();
		end_min.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		end_min.setBounds(158, 131, 82, 27);
		contentPane.add(end_min);
		
		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(schedulename.getText().isEmpty()) {
					JOptionPane.showMessageDialog(AddScheduleFrame.this, "이름을 입력하세요.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				team6.schedule.Calendar cal = team6.schedule.Calendar.getInstance(year, month);
				List<Schedule> list = cal.getSchedules(date);
				for(Schedule schedule : list)
					if(schedule.getName().equalsIgnoreCase(schedulename.getText())) {
						JOptionPane.showMessageDialog(AddScheduleFrame.this, "이미 같은 이름의 일정이 존재합니다.","경고",JOptionPane.WARNING_MESSAGE);
						return;
					}
				Time time = new Time(Integer.parseInt(start_year.getSelectedItem().toString()),
						Integer.parseInt(start_month.getSelectedItem().toString()),
						Integer.parseInt(start_date.getSelectedItem().toString()),
						Integer.parseInt(start_hour.getSelectedItem().toString()),
						Integer.parseInt(start_min.getSelectedItem().toString()),
						Integer.parseInt(start_year.getSelectedItem().toString()),
						Integer.parseInt(start_month.getSelectedItem().toString()),
						Integer.parseInt(start_date.getSelectedItem().toString()),
						Integer.parseInt(end_hour.getSelectedItem().toString()),
						Integer.parseInt(end_min.getSelectedItem().toString()));
				cal.addOther(date, new Schedule(schedulename.getText(), location.getText(), time, 4));
				CalendarPanel.synchronizeCalendar();
				CalendarPanel.synchronizeInfoPanel();
				try {
					Main.saveSchedules();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AddScheduleFrame.this.setVisible(false);
			}
		});
		addBtn.setBounds(177, 170, 117, 29);
		contentPane.add(addBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddScheduleFrame.this.setVisible(false);
			}
		});
		cancelBtn.setBounds(48, 170, 117, 29);
		contentPane.add(cancelBtn);
		
		
		
	}
}
