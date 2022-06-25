package team6.gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import team6.Main;
import team6.timetable.Assignment;
import team6.timetable.Day;
import team6.timetable.Subject;
import team6.timetable.Timetable;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class TimetablePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public static JComboBox<String> comboBox, comboBox_1;
	private static JPanel[][] subs = new JPanel[5][10];
	private static JLabel[][] labels = new JLabel[5][10];
	
	private JPopupMenu pm = new JPopupMenu();
	private JMenuItem item1 = new JMenuItem("과제 추가");
	private JMenuItem item2 = new JMenuItem("휴강/인강 추가");
	private JMenuItem item3 = new JMenuItem("시험 추가");
	private JMenuItem item4 = new JMenuItem("삭제");
	
	private static JPopupMenu pm2 = new JPopupMenu();
	private static JMenuItem item2_1 = new JMenuItem("항목 삭제");
	
	private static JPanel selected = null;
	private static JLabel selected_lb = null;
	static JPanel infoPanel;
	
	public TimetablePanel(JFrame frame) {
		
		pm.add(item1);
		pm.add(item2);
		pm.add(item3);
		pm.addSeparator();
		pm.add(item4);
		
		pm2.add(item2_1);
		
		setLayout(null);
		setBounds(0,0,688,460);
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 24, 163, 429);
		add(scrollPane);
		
		 infoPanel = new JPanel();
		scrollPane.setViewportView(infoPanel);
		infoPanel.setLayout(null);
		
		for(int i = 0; i < 5; i ++) {
			JPanel daypanel = new JPanel();
			JLabel daylabel = new JLabel(Day.values()[i].getName());
			daypanel.setLayout(null);
			daypanel.setBackground(Color.WHITE);
			daypanel.setBounds((i*94)+50, 24, 94, 39);
			daylabel.setBounds(0, 0, 94, 39);
			daylabel.setHorizontalAlignment(JLabel.CENTER);
			daypanel.add(daylabel);
			add(daypanel);
		}
		for(int i = 0; i <= 10; i ++) {
			JPanel daypanel = new JPanel();
			JLabel daylabel = new JLabel(i == 0 ? "" : i + "교시");
			daypanel.setLayout(null);
			daypanel.setBackground(Color.WHITE);
			daypanel.setBounds(0, (i*39)+24, 50, 39);
			daylabel.setBounds(0, 0, 50, 39);
			daylabel.setHorizontalAlignment(JLabel.CENTER);
			daypanel.add(daylabel);
			add(daypanel);
		}
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 10; j ++) {
				JPanel subpanel = new JPanel();
				JLabel title = new JLabel();
				subpanel.setLayout(null);
				subpanel.setBounds((i*94), (j) * 39, 94, 39);
				subpanel.setBackground(Color.GRAY);
				title.setBounds(0, 0, 94, 39);
				title.setText("");
				title.setHorizontalAlignment(JLabel.CENTER);
				subpanel.add(title);
				subpanel.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if(SwingUtilities.isLeftMouseButton(e)) {
							if(title.getText().equalsIgnoreCase("")) {
								AddSubjectFrame frame = new AddSubjectFrame();
								frame.setVisible(true);
							} else {
								if(selected != null) selected.setBorder(null);
								subpanel.setBorder(new LineBorder(Color.BLACK));
								selected = subpanel;
								selected_lb = title;
								synchronizeTimetable();
							}
						} else {
							if(!title.getText().equalsIgnoreCase("")) {
								for(ActionListener listener : item1.getActionListeners())
									item1.removeActionListener(listener);
								for(ActionListener listener : item2.getActionListeners())
									item2.removeActionListener(listener);
								for(ActionListener listener : item3.getActionListeners())
									item3.removeActionListener(listener);
								for(ActionListener listener : item4.getActionListeners())
									item4.removeActionListener(listener);
								
								
								int year = Integer.parseInt(comboBox.getSelectedItem().toString());
								int semester = Integer.parseInt(comboBox_1.getSelectedItem().toString());
								Timetable table = Main.getTimetable(year, semester);
								
								item1.addActionListener(e1 -> new AddAsignmentFrame(title.getText()).setVisible(true));
								item2.addActionListener(e1 -> new AddRestFrame(title.getText()).setVisible(true));
								item3.addActionListener(e1 -> new AddExamFrame(title.getText()).setVisible(true));
								item4.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										table.removeSubject(table.getSubject(title.getText()));
										try {
											Main.saveTimetables();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										CalendarPanel.synchronizeCalendar();
										CalendarPanel.synchronizeInfoPanel();
										synchronizeTimetable();
									}
								});
								pm.show(subpanel, e.getX(), e.getY());
							}
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {}

					@Override
					public void mouseReleased(MouseEvent e) {}

					@Override
					public void mouseEntered(MouseEvent e) {}

					@Override
					public void mouseExited(MouseEvent e) {}
					
				});
				panel.add(subpanel);
				labels[i][j] = title;
				subs[i][j] = subpanel;
			}
		}
		
		panel.setLayout(null);
		panel.setBounds(50, 63, 470, 391);
		add(panel);
		
		
		JLabel lblNewLabel = new JLabel("연도 : ");
		lblNewLabel.setBounds(0, 4, 34, 16);
		add(lblNewLabel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(e -> synchronizeTimetable());
		comboBox.setModel(new DefaultComboBoxModel<String>(Main.YEARS));
		comboBox.setBounds(46, 0, 87, 27);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("학기 : ");
		lblNewLabel_1.setBounds(145, 4, 34, 16);
		add(lblNewLabel_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.addItemListener(e -> synchronizeTimetable());
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2"}));
		comboBox_1.setBounds(191, 0, 87, 27);
		add(comboBox_1);
		
		synchronizeTimetable();
		
	}
	
	public static void synchronizeTimetable() {
		int year = Integer.parseInt(comboBox.getSelectedItem().toString());
		int semester = Integer.parseInt(comboBox_1.getSelectedItem().toString());
		Timetable table = Main.getTimetable(year, semester);
		for(int i = 0; i < 5; i ++) {
			for(int j = 0; j < 10; j ++) {
				Subject sub = table.getSubject(i, j);
				if(sub == null) {
					subs[i][j].setBackground(Color.LIGHT_GRAY);
					labels[i][j].setText("");
				} else {
					subs[i][j].setBackground(sub.getColor());
					labels[i][j].setText(sub.getName());
				}
			}
		}
		if(selected_lb == null) return;
		Subject subject = table.getSubject(selected_lb.getText());
		infoPanel.removeAll();
		int y = 6;
		int cnt = 0;
		JLabel assign_lb = new JLabel("과제");
		assign_lb.setBounds(6, y, 61, 16);
		infoPanel.add(assign_lb);
		y+=12+16;
		
		for(Assignment assign : subject.getAssign()) {
			if(assign.getTime().isPast()) continue;
			JPanel jp = new JPanel(null);
			jp.setBackground(Color.WHITE);
			jp.setBounds(6, y, 147, 59);
			JLabel name_lb = new JLabel(assign.getName());
			name_lb.setHorizontalAlignment(SwingConstants.LEADING);
			name_lb.setBounds(6, 6, 61, 16);
			jp.add(name_lb);
			
			JLabel time_lb = new JLabel(assign.getTime().getStart_year()+"년 "+assign.getTime().getStart_month()+"월 "+assign.getTime().getStart_date()+"일까지");
			time_lb.setBounds(6, 34, 135, 16);
			time_lb.setHorizontalAlignment(SwingConstants.LEADING);
			jp.add(time_lb);
			jp.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!SwingUtilities.isLeftMouseButton(e)) {
						for(ActionListener listener : item2_1.getActionListeners())
							item2_1.removeActionListener(listener);
						item2_1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(subject.getAssignment(assign.getName()) != null) {
									subject.getAssign().remove(subject.getAssignment(assign.getName()));
									try {
										Main.saveTimetables();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									synchronizeTimetable();
								}
							}
						});
						pm2.show(jp, e.getX(), e.getY());
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			infoPanel.add(jp);
			y+=12+59;
			cnt++;
		}
		if(cnt == 0) {
			JLabel no_lb = new JLabel("일정 없음");
			no_lb.setHorizontalAlignment(SwingConstants.CENTER);
			no_lb.setBounds(6, y, 147, 16);
			infoPanel.add(no_lb);
			y+=12+16;
		}
		
		JLabel rest_lb = new JLabel("휴강/인강");
		rest_lb.setBounds(6, y, 61, 16);
		infoPanel.add(rest_lb);
		y+=12+16;
		
		 cnt = 0;
		for(Assignment assign : subject.getRest()) {
			if(assign.getTime().isPast()) continue;
			JPanel jp = new JPanel(null);
			jp.setBackground(Color.WHITE);
			jp.setBounds(6, y, 147, 59);
			JLabel name_lb = new JLabel(assign.getName());
			name_lb.setHorizontalAlignment(SwingConstants.LEADING);
			name_lb.setBounds(6, 6, 61, 16);
			jp.add(name_lb);
			
			JLabel time_lb = new JLabel(assign.getTime().getStart_year()+"년 "+assign.getTime().getStart_month()+"월 "+assign.getTime().getStart_date()+"일");
			time_lb.setBounds(6, 34, 135, 16);
			time_lb.setHorizontalAlignment(SwingConstants.LEADING);
			jp.add(time_lb);
			jp.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!SwingUtilities.isLeftMouseButton(e)) {
						for(ActionListener listener : item2_1.getActionListeners())
							item2_1.removeActionListener(listener);
						item2_1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(subject.getRest(assign.getName()) != null) {
									subject.getRest().remove(subject.getRest(assign.getName()));
									try {
										Main.saveTimetables();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									synchronizeTimetable();
								}
							}
						});
						pm2.show(jp, e.getX(), e.getY());
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			infoPanel.add(jp);
			y+=12+59;
			cnt++;
		}
		if(cnt == 0) {
			JLabel no_lb = new JLabel("일정 없음");
			no_lb.setHorizontalAlignment(SwingConstants.CENTER);
			no_lb.setBounds(6, y, 147, 16);
			infoPanel.add(no_lb);
			y+=12+16;
		}
		
		JLabel test_lb = new JLabel("시험");
		test_lb.setBounds(6, y, 61, 16);
		infoPanel.add(test_lb);
		y+=12+16;
		cnt = 0;
		for(Assignment assign : subject.getExam()) {
			if(assign.getTime().isPast()) continue;
			JPanel jp = new JPanel(null);
			jp.setBackground(Color.WHITE);
			jp.setBounds(6, y, 147, 59);
			JLabel name_lb = new JLabel(assign.getName());
			name_lb.setBounds(6, 6, 61, 16);
			name_lb.setHorizontalAlignment(SwingConstants.LEADING);
			jp.add(name_lb);
			
			JLabel time_lb = new JLabel(assign.getTime().getStart_year()+"년 "+assign.getTime().getStart_month()+"월 "+assign.getTime().getStart_date()+"일");
			time_lb.setBounds(6, 34, 135, 16);
			time_lb.setHorizontalAlignment(SwingConstants.LEADING);
			jp.add(time_lb);
			jp.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(!SwingUtilities.isLeftMouseButton(e)) {
						for(ActionListener listener : item2_1.getActionListeners())
							item2_1.removeActionListener(listener);
						item2_1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(subject.getExam(assign.getName()) != null) {
									subject.getExam().remove(subject.getExam(assign.getName()));
									try {
										Main.saveTimetables();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									synchronizeTimetable();
								}
							}
						});
						pm2.show(jp, e.getX(), e.getY());
					}
					
				}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}
				
			});
			infoPanel.add(jp);
			y+=12+59;
			cnt++;
		}
		if(cnt == 0) {
			JLabel no_lb = new JLabel("일정 없음");
			no_lb.setHorizontalAlignment(SwingConstants.CENTER);
			no_lb.setBounds(6, y, 147, 16);
			infoPanel.add(no_lb);
			y+=12+16;
		}
		infoPanel.repaint();
		infoPanel.revalidate();
	}
}
