package team6.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import team6.Main;
import team6.timetable.Assignment;
import team6.timetable.Subject;
import team6.timetable.Time;
import team6.timetable.Timetable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddAsignmentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField assignname;

	/**
	 * Create the frame.
	 */
	public AddAsignmentFrame(String sub) {
		setBounds(100, 100, 300, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("과목명 : ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(6, 6, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel subname = new JLabel(sub);
		subname.setBounds(68, 6, 226, 16);
		contentPane.add(subname);
		
		JLabel lblNewLabel_2 = new JLabel("과제이름 : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(6, 34, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("마감 : ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setBounds(6, 66, 61, 16);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox end_year = new JComboBox();
		end_year.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023", "2024", "2025"}));
		end_year.setBounds(68, 62, 82, 27);
		contentPane.add(end_year);
		
		JComboBox end_month = new JComboBox();
		end_month.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		end_month.setBounds(148, 62, 61, 27);
		contentPane.add(end_month);
		
		JComboBox end_date = new JComboBox();
		end_date.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		end_date.setBounds(207, 62, 61, 27);
		contentPane.add(end_date);
		
		assignname = new JTextField();
		assignname.setBounds(68, 29, 226, 26);
		contentPane.add(assignname);
		assignname.setColumns(10);
		
		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = Integer.parseInt(TimetablePanel.comboBox.getSelectedItem().toString());
				int semester = Integer.parseInt(TimetablePanel.comboBox_1.getSelectedItem().toString());
				
				Timetable table = Main.getTimetable(year, semester);
				
				Subject subject = table.getSubject(sub);
				
				if(subject == null) {
					JOptionPane.showMessageDialog(AddAsignmentFrame.this, "수정하려는 과목이 변경 혹은 삭제되었습니다.","오류",JOptionPane.WARNING_MESSAGE);
					AddAsignmentFrame.this.setVisible(false);
					return;
				}
				if(subject.getAssignment(assignname.getText()) != null) {
					JOptionPane.showMessageDialog(AddAsignmentFrame.this, "이미 같은 이름의 과제가 있습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Time time = new Time(Integer.parseInt(end_year.getSelectedItem().toString()),
						Integer.parseInt(end_month.getSelectedItem().toString()),
						Integer.parseInt(end_date.getSelectedItem().toString()));
				
				Assignment assignment = new Assignment(assignname.getText(),time);
				
				subject.getAssign().add(assignment);
				try {
					Main.saveTimetables();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CalendarPanel.synchronizeCalendar();
				CalendarPanel.synchronizeInfoPanel();
				AddAsignmentFrame.this.setVisible(false);
			}
		});
		addBtn.setBounds(177, 94, 117, 29);
		contentPane.add(addBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddAsignmentFrame.this.setVisible(false);
			}
		});
		cancelBtn.setBounds(48, 94, 117, 29);
		contentPane.add(cancelBtn);
	}
}
