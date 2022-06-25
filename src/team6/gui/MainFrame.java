package team6.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try{CalculatorPanel.synchronizeTimetable();}catch(Exception e1) {}
			}
		});
		tabbedPane.setBounds(6, 6, 688, 460);
		
		tabbedPane.addTab("달력", new CalendarPanel());
		tabbedPane.addTab("시간표", new TimetablePanel(this));
		tabbedPane.addTab("학점계산", new CalculatorPanel());
		
		contentPane.add(tabbedPane);
	}
}
