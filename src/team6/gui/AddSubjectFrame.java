package team6.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team6.Main;
import team6.timetable.Day;
import team6.timetable.Subject;
import team6.timetable.Time;
import team6.timetable.Timetable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AddSubjectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private AddSubjectFrame self;
	/**
	 * Create the frame.
	 */
	public AddSubjectFrame() {
		self = this;
		setTitle("과목 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 269);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("과목명");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(6, 6, 61, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(79, 1, 286, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("담당 교수");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(6, 39, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(79, 34, 286, 26);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("위치");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(6, 72, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(79, 67, 286, 26);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("학점");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(6, 105, 61, 16);
		contentPane.add(lblNewLabel_2_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(79, 100, 286, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(79, 138, 90, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("시간1");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setBounds(6, 143, 61, 16);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(197, 138, 90, 26);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("시간2");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setBounds(6, 176, 61, 16);
		contentPane.add(lblNewLabel_2_1_1_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(79, 171, 90, 26);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(197, 171, 90, 26);
		contentPane.add(textField_7);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"월", "화", "수", "목", "금"}));
		comboBox.setBounds(292, 138, 73, 27);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"월", "화", "수", "목", "금"}));
		comboBox_1.setBounds(292, 172, 73, 27);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("취소");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				self.setVisible(false);
			}
		});
		btnNewButton.setBounds(16, 204, 168, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("추가");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int start1hour, start1min, end1hour, end1min, start2hour, start2min, end2hour, end2min;
				String[] starttime1 = textField_4.getText().split(":");
				String[] endtime1 = textField_5.getText().split(":");
				String[] starttime2 = textField_6.getText().split(":");
				String[] endtime2 = textField_7.getText().split(":");
				Time time1 = null, time2 = null;
				Day time1day = Day.getDay(comboBox.getSelectedItem().toString());
				Day time2day = Day.getDay(comboBox_1.getSelectedItem().toString());
				if(starttime1.length != 2 || endtime1.length != 2) {
					JOptionPane.showMessageDialog(null, "시간1 형식이 잘못되었습니다.", "형식 오류",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {
					start1hour = Integer.parseInt(starttime1[0]);
					start1min = Integer.parseInt(starttime1[1]);
					end1hour = Integer.parseInt(endtime1[0]);
					end1min = Integer.parseInt(endtime1[1]);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "시간1 형식이 잘못되었습니다.", "형식 오류",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				time1 = new Time(start1hour, start1min, end1hour, end1min, time1day);
				if(!textField_6.getText().equals("") && !textField_7.getText().equals("")) {
					if(starttime2.length != 2 || endtime2.length != 2) {
						JOptionPane.showMessageDialog(null, "시간2 형식이 잘못되었습니다.", "형식 오류",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					try {
						start2hour = Integer.parseInt(starttime2[0]);
						start2min = Integer.parseInt(starttime2[1]);
						end2hour = Integer.parseInt(endtime2[0]);
						end2min = Integer.parseInt(endtime2[1]);
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "시간2 형식이 잘못되었습니다.", "형식 오류",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					time2 = new Time(start2hour, start2min, end2hour, end2min, time2day);
				}
				Subject subject = null;
				if(time2 == null) {
					subject = new Subject(textField.getText(),
							textField_1.getText(),
							textField_2.getText(),
							Integer.parseInt(textField_3.getText()), time1);
				} else {
					subject = new Subject(textField.getText(),
							textField_1.getText(),
							textField_2.getText(),
							Integer.parseInt(textField_3.getText()), time1,time2);
				}
				int year = Integer.parseInt(TimetablePanel.comboBox.getSelectedItem().toString());
				int semester = Integer.parseInt(TimetablePanel.comboBox_1.getSelectedItem().toString());
				Timetable table = Main.getTimetable(year, semester);
				if(table.canAddable(subject)) {
					table.addSubject(subject);
					TimetablePanel.synchronizeTimetable();
					self.setVisible(false);
					try {
						Main.saveTimetables();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "다른 과목과 시간이 중복됩니다.", "시간 중복",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		btnNewButton_1.setBounds(197, 204, 168, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("~");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(170, 143, 23, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("~");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setBounds(170, 176, 23, 16);
		contentPane.add(lblNewLabel_3_1);

	}
}
