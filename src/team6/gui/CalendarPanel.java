package team6.gui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import team6.Main;
import team6.schedule.Schedule;
import team6.timetable.Time;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class CalendarPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public static JPanel calendar;
	public static JLabel monthlabel;
	public static JPanel infoPanel;
	
	public static int sel_year, sel_month, sel_date;
	
	public static int year, month;
	
	private static JPopupMenu pm = new JPopupMenu();
	private static JMenuItem item1 = new JMenuItem("일정 추가");
	
	private static JPopupMenu pm2 = new JPopupMenu();
	private static JMenuItem item2 = new JMenuItem("항목 삭제");
	
	public CalendarPanel() {
		
		pm.add(item1);
		pm2.add(item2);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH) + 1;
		
		setLayout(null);
		setBounds(0,0,668,414);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(490, 6, 172, 402);
		add(scrollPane);
		
		infoPanel = new JPanel();
		scrollPane.setViewportView(infoPanel);
		infoPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("<");
		btnNewButton.addActionListener(e -> {
			if(year <= 1 && month <= 1) return;
			month --;
			if(month <= 0) {
				year --;
				month += 12;
			}
			synchronizeCalendar();
		});
		btnNewButton.setBounds(6, 6, 58, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">");
		btnNewButton_1.addActionListener(e -> {
			month ++;
			if(month > 12) {
				year ++;
				month -= 12;
			}
			synchronizeCalendar();
		});
		btnNewButton_1.setBounds(429, 6, 58, 29);
		add(btnNewButton_1);
		
		monthlabel = new JLabel();
		monthlabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		monthlabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthlabel.setBounds(76, 11, 341, 16);
		add(monthlabel);
		
		calendar = new JPanel();
		calendar.setBounds(16, 58, 462, 350);
		add(calendar);
		calendar.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 36, 66, 22);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("SUN");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(82, 36, 66, 22);
		add(panel_1_1);
		panel_1_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("MON");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_1.add(lblNewLabel_2, BorderLayout.CENTER);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(148, 36, 66, 22);
		add(panel_1_2);
		panel_1_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("TUE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_2.add(lblNewLabel_3, BorderLayout.CENTER);
		
		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBounds(214, 36, 66, 22);
		add(panel_1_3);
		panel_1_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("WED");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_3.add(lblNewLabel_4, BorderLayout.CENTER);
		
		JPanel panel_1_4 = new JPanel();
		panel_1_4.setBounds(280, 36, 66, 22);
		add(panel_1_4);
		panel_1_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("THU");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_4.add(lblNewLabel_5, BorderLayout.CENTER);
		
		JPanel panel_1_5 = new JPanel();
		panel_1_5.setBounds(346, 36, 66, 22);
		add(panel_1_5);
		panel_1_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("FRI");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_5.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JPanel panel_1_5_1 = new JPanel();
		panel_1_5_1.setBounds(412, 36, 66, 22);
		add(panel_1_5_1);
		panel_1_5_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("SAT");
		lblNewLabel_7.setForeground(Color.BLUE);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1_5_1.add(lblNewLabel_7, BorderLayout.CENTER);
		synchronizeCalendar();
	}
	private static JPanel selected = null;
	public static void synchronizeCalendar() {
		team6.schedule.Calendar.reschedulingTimetable(year, month);
		monthlabel.setText(year + "년 " + month + "월");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.DATE, -1);
		
		int end_day = cal.get(Calendar.DATE);
		int y = 0;
		cal.set(Calendar.MONTH, month-1);
		calendar.removeAll();
		for(int i = 1; i <= end_day; i++ ) {
			final int date_v = i;
			cal.set(Calendar.DATE, i);
			int day = cal.get(Calendar.DAY_OF_WEEK);
			int x = 66 * (day-1);
			
			JPanel datepanel = new JPanel(new BorderLayout(0,0));
			datepanel.setBounds(x, y, 66, 70);
			JLabel date = new JLabel(Integer.toString(i));
			
			if(day == 1) date.setForeground(Color.RED);
			else if(day == 7) date.setForeground(Color.BLUE);
			
			List<Schedule> list = team6.schedule.Calendar.getInstance(year, month).getSchedules(i);
			List<Schedule> others = team6.schedule.Calendar.getInstance(year, month).getOthers(i);
			
			if(list.size() + others.size() != 0) {
				JLabel label = new JLabel((list.size() + others.size()) + "개의 일정");
				label.setOpaque(true);
				label.setBackground(Color.CYAN);
				label.setHorizontalAlignment(SwingConstants.RIGHT);
				datepanel.add(label, BorderLayout.SOUTH);
			}
			int f_year = year;
			int f_month = month;
			datepanel.add(date, BorderLayout.NORTH);
			datepanel.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						if(selected != null) selected.setBorder(null);
						datepanel.setBorder(new LineBorder(Color.BLACK));
						selected = datepanel;
						sel_year = year;
						sel_month = month;
						sel_date = date_v;
						synchronizeInfoPanel();
					} else {
						for(ActionListener listener : item1.getActionListeners())
							item1.removeActionListener(listener);
						item1.addActionListener(e1 -> new AddScheduleFrame(f_year, f_month, date_v).setVisible(true));
						pm.show(datepanel, e.getX(), e.getY());
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
			calendar.add(datepanel);
			
			if(day == 7)
				y += 70;
		}
		calendar.repaint();
		calendar.revalidate();
	}
	
	public static void synchronizeInfoPanel() {
		infoPanel.removeAll();
		if(selected == null) return;
		int y = 6;
		JLabel tableschedule = new JLabel("강의 일정");
		tableschedule.setBounds(6, y, 156, 16);
		infoPanel.add(tableschedule);
		List<Schedule> list = team6.schedule.Calendar.getInstance(sel_year, sel_month).getSchedules(sel_date);
		List<Schedule> tables = list.stream().filter(schedule -> schedule.getType() == 0).collect(Collectors.toList());
		tables.sort((o1, o2) -> ((Schedule)o1).getTime().getStart_hour() - ((Schedule)o2).getTime().getStart_hour());
		List<Schedule> assign = list.stream().filter(schedule -> schedule.getType() == 1).collect(Collectors.toList());
		assign.sort((o1, o2) -> ((Schedule)o1).getTime().getStart_hour() - ((Schedule)o2).getTime().getStart_hour());
		List<Schedule> exam = list.stream().filter(schedule -> schedule.getType() == 2).collect(Collectors.toList());
		exam.sort((o1, o2) -> ((Schedule)o1).getTime().getStart_hour() - ((Schedule)o2).getTime().getStart_hour());
		List<Schedule> other = team6.schedule.Calendar.getInstance(sel_year, sel_month).getOthers(sel_date);
		y += 12+16;
		if(tables.isEmpty()) {
			JLabel no_schedule = new JLabel("일정 없음");
			no_schedule.setHorizontalAlignment(SwingConstants.CENTER);
			no_schedule.setBounds(6, y, 156, 16);
			infoPanel.add(no_schedule);
			y+= 12+16;
		} else {
			for(Schedule schedule : tables) {
				JPanel tablepanel = new JPanel(null);
				tablepanel.setBackground(Color.WHITE);
				tablepanel.setBounds(6, y, 156, 67);
				JLabel title = new JLabel(schedule.getName());
				title.setBounds(6, 6, 144, 16);
				tablepanel.add(title);
				
				JLabel loc = new JLabel(schedule.getDescription());
				loc.setBounds(6, 23, 144, 16);
				tablepanel.add(loc);
				
				Time t = schedule.getTime();
				String starthour = ""+t.getStart_hour();
				String startmin = ""+t.getStart_minute();
				String endhour = ""+t.getEnd_hour();
				String endmin = ""+t.getEnd_minute();
				if(starthour.length() == 1) starthour = "0"+starthour; 
				if(startmin.length() == 1) startmin = "0"+startmin; 
				if(endhour.length() == 1) endhour = "0"+endhour; 
				if(endmin.length() == 1) endmin = "0"+endmin; 
				JLabel time = new JLabel(starthour + ":" + startmin + " ~ " + endhour + ":" + endmin);
				time.setBounds(6, 45, 144, 16);
				tablepanel.add(time);
				infoPanel.add(tablepanel);
				y += 12+67;
			}
		}
		JLabel assignchedule = new JLabel("과제 마감");
		assignchedule.setBounds(6, y, 156, 16);
		infoPanel.add(assignchedule);
		y += 12+16;
		if(assign.isEmpty()) {
			JLabel no_schedule = new JLabel("일정 없음");
			no_schedule.setHorizontalAlignment(SwingConstants.CENTER);
			no_schedule.setBounds(6, y, 156, 16);
			infoPanel.add(no_schedule);
			y+= 12+16;
		} else {
			for(Schedule schedule : assign) {
				JPanel tablepanel = new JPanel(null);
				tablepanel.setBackground(Color.WHITE);
				tablepanel.setBounds(6, y, 156, 28);
				JLabel title = new JLabel(schedule.getName());
				title.setBounds(6, 6, 144, 16);
				tablepanel.add(title);
				
				infoPanel.add(tablepanel);
				y += 12+28;
			}
		}
		JLabel examchedule = new JLabel("시험 일정");
		examchedule.setBounds(6, y, 156, 16);
		infoPanel.add(examchedule);
		y += 12+16;
		if(exam.isEmpty()) {
			JLabel no_schedule = new JLabel("일정 없음");
			no_schedule.setHorizontalAlignment(SwingConstants.CENTER);
			no_schedule.setBounds(6, y, 156, 16);
			infoPanel.add(no_schedule);
			y+= 12+16;
		} else {
			for(Schedule schedule : exam) {
				JPanel tablepanel = new JPanel(null);
				tablepanel.setBackground(Color.WHITE);
				tablepanel.setBounds(6, y, 156, 67);
				JLabel title = new JLabel(schedule.getName());
				title.setBounds(6, 6, 144, 16);
				tablepanel.add(title);
				
				JLabel loc = new JLabel(schedule.getDescription());
				loc.setBounds(6, 23, 144, 16);
				tablepanel.add(loc);
				
				Time t = schedule.getTime();
				String starthour = ""+t.getStart_hour();
				String startmin = ""+t.getStart_minute();
				if(starthour.length() == 1) starthour = "0"+starthour; 
				if(startmin.length() == 1) startmin = "0"+startmin; 
				JLabel time = new JLabel(starthour + ":" + startmin);
				time.setBounds(6, 45, 144, 16);
				tablepanel.add(time);
				infoPanel.add(tablepanel);
				y += 12+67;
			}
		}
		JLabel otherschedule = new JLabel("기타 일정");
		otherschedule.setBounds(6, y, 156, 16);
		infoPanel.add(otherschedule);
		y += 12+16;
		if(other.isEmpty()) {
			JLabel no_schedule = new JLabel("일정 없음");
			no_schedule.setHorizontalAlignment(SwingConstants.CENTER);
			no_schedule.setBounds(6, y, 156, 16);
			infoPanel.add(no_schedule);
			y+= 12+16;
		} else {
			for(Schedule schedule : other) {
				JPanel tablepanel = new JPanel(null);
				tablepanel.setBackground(Color.WHITE);
				tablepanel.setBounds(6, y, 156, 67);
				JLabel title = new JLabel(schedule.getName());
				title.setBounds(6, 6, 144, 16);
				tablepanel.add(title);
				
				JLabel loc = new JLabel(schedule.getDescription());
				loc.setBounds(6, 23, 144, 16);
				tablepanel.add(loc);
				
				Time t = schedule.getTime();
				String starthour = ""+t.getStart_hour();
				String startmin = ""+t.getStart_minute();
				String endhour = ""+t.getEnd_hour();
				String endmin = ""+t.getEnd_minute();
				if(starthour.length() == 1) starthour = "0"+starthour;
				if(startmin.length() == 1) startmin = "0"+startmin;
				if(endhour.length() == 1) endhour = "0"+endhour;
				if(endmin.length() == 1) endmin = "0"+endmin; 
				JLabel time = new JLabel(starthour + ":" + startmin + " ~ " + endhour + ":" + endmin);
				time.setBounds(6, 45, 144, 16);
				tablepanel.add(time);
				final int f_year = sel_year;
				final int f_month = sel_month;
				final int f_date = sel_date;
				tablepanel.addMouseListener(new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						if(!SwingUtilities.isLeftMouseButton(e)) {
							for(ActionListener listener : item2.getActionListeners())
								item2.removeActionListener(listener);
							item2.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									team6.schedule.Calendar cal = team6.schedule.Calendar.getInstance(f_year, f_month);
									List<Schedule> sch = cal.getOthers(f_date);
									Schedule now = null;
									for(Schedule s : sch)
										if(s.getName().equalsIgnoreCase(schedule.getName())) {
											now = s;
											break;
										}
									if(now != null) {
										cal.getOthers(f_date).remove(now);
										synchronizeCalendar();
										synchronizeInfoPanel();
										try {
											Main.saveSchedules();
										} catch (IOException e1) {
											e1.printStackTrace();
										}
									}
								}
							});
							pm2.show(tablepanel, e.getX(), e.getY());
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
				infoPanel.add(tablepanel);
				y += 12+67;
			}
		}
		infoPanel.setPreferredSize(new Dimension(172,y));
		infoPanel.repaint();
		infoPanel.revalidate();
	}
}
