package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;

/**
 * @SER516 Project3_Team03
 * @Version 1.0
 */

public class ClientUi {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUi window = new ClientUi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
/*		try {
	           UIManager.setLookAndFeel(
	                   "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	       } catch(Exception e) {   
	       }*/
		frame = new JFrame();
		frame.setBounds(new Rectangle(0, 0, 710, 432));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 698, 402);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 22, 698, 380);
		panel.add(tabbedPane);
		
		JPanel expressivePanel = new JPanel();
		tabbedPane.addTab("Expressive", null, expressivePanel, null);
		expressivePanel.setLayout(null);
		
		JPanel facePanel = new JPanel();
		facePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		facePanel.setBounds(0, 28, 326, 313);
		expressivePanel.add(facePanel);
		
		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		graphPanel.setBounds(325, 28, 368, 313);
		expressivePanel.add(graphPanel);
		graphPanel.setLayout(null);
		
		JLabel blinkLabel = new JLabel("Blink\r\n");
		blinkLabel.setForeground(Color.WHITE);
		blinkLabel.setOpaque(true);
		blinkLabel.setBackground(Color.GRAY);
		blinkLabel.setHorizontalAlignment(SwingConstants.LEFT);
		blinkLabel.setBounds(10, 11, 68, 20);
		graphPanel.add(blinkLabel);
		
		JLabel rgtWinkLabel = new JLabel("Right Wink");
		rgtWinkLabel.setBackground(Color.GRAY);
		rgtWinkLabel.setForeground(Color.WHITE);
		rgtWinkLabel.setOpaque(true);
		rgtWinkLabel.setHorizontalAlignment(SwingConstants.LEFT);
		rgtWinkLabel.setBounds(10, 36, 68, 18);
		graphPanel.add(rgtWinkLabel);
		
		JLabel lftWinkLabel = new JLabel("Left Wink");
		lftWinkLabel.setOpaque(true);
		lftWinkLabel.setForeground(Color.WHITE);
		lftWinkLabel.setBackground(Color.GRAY);
		lftWinkLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lftWinkLabel.setBounds(10, 65, 68, 20);
		graphPanel.add(lftWinkLabel);
		
		JLabel lookLabel = new JLabel("Look R/L");
		lookLabel.setOpaque(true);
		lookLabel.setForeground(Color.WHITE);
		lookLabel.setBackground(Color.GRAY);
		lookLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lookLabel.setBounds(10, 90, 68, 20);
		graphPanel.add(lookLabel);
		
		JLabel furrowLabel = new JLabel("Furrow Brow\r\n");
		furrowLabel.setOpaque(true);
		furrowLabel.setForeground(Color.WHITE);
		furrowLabel.setBackground(Color.GRAY);
		furrowLabel.setHorizontalAlignment(SwingConstants.LEFT);
		furrowLabel.setBounds(10, 115, 68, 20);
		graphPanel.add(furrowLabel);
		
		JLabel raiseLabel = new JLabel("Raise Brow");
		raiseLabel.setOpaque(true);
		raiseLabel.setForeground(Color.WHITE);
		raiseLabel.setBackground(Color.GRAY);
		raiseLabel.setBounds(10, 140, 68, 20);
		graphPanel.add(raiseLabel);
		
		JLabel smileLabel = new JLabel("Smile");
		smileLabel.setOpaque(true);
		smileLabel.setForeground(Color.WHITE);
		smileLabel.setBackground(Color.GRAY);
		smileLabel.setBounds(10, 165, 68, 20);
		graphPanel.add(smileLabel);
		
		JLabel clenchLabel = new JLabel("Clench");
		clenchLabel.setOpaque(true);
		clenchLabel.setForeground(Color.WHITE);
		clenchLabel.setBackground(Color.GRAY);
		clenchLabel.setBounds(10, 190, 68, 20);
		graphPanel.add(clenchLabel);
		
		JLabel lftSmirkLabel = new JLabel("Left Smirk");
		lftSmirkLabel.setOpaque(true);
		lftSmirkLabel.setForeground(Color.WHITE);
		lftSmirkLabel.setBackground(Color.GRAY);
		lftSmirkLabel.setBounds(10, 215, 68, 20);
		graphPanel.add(lftSmirkLabel);
		
		JLabel rgtSmirkLabel = new JLabel("Right Smirk");
		rgtSmirkLabel.setOpaque(true);
		rgtSmirkLabel.setForeground(Color.WHITE);
		rgtSmirkLabel.setBackground(Color.GRAY);
		rgtSmirkLabel.setBounds(10, 245, 68, 20);
		graphPanel.add(rgtSmirkLabel);
		
		JLabel laughLabel = new JLabel("Laugh");
		laughLabel.setOpaque(true);
		laughLabel.setForeground(Color.WHITE);
		laughLabel.setBackground(Color.GRAY);
		laughLabel.setBounds(10, 276, 68, 20);
		graphPanel.add(laughLabel);
		
		JPanel blinkGraphPanel = new JPanel();
		blinkGraphPanel.setBounds(82, 11, 276, 20);
		graphPanel.add(blinkGraphPanel);
		
		JPanel rgtWinkGraphPanel = new JPanel();
		rgtWinkGraphPanel.setBounds(82, 36, 276, 20);
		graphPanel.add(rgtWinkGraphPanel);
		
		JPanel lftWinkGraphPanel = new JPanel();
		lftWinkGraphPanel.setBounds(82, 65, 276, 20);
		graphPanel.add(lftWinkGraphPanel);
		
		JPanel lookGraphPanel = new JPanel();
		lookGraphPanel.setBounds(82, 90, 276, 20);
		graphPanel.add(lookGraphPanel);
		
		JPanel furrowGraphPanel = new JPanel();
		furrowGraphPanel.setBounds(82, 115, 276, 20);
		graphPanel.add(furrowGraphPanel);
		
		JPanel raiseGraphPanel = new JPanel();
		raiseGraphPanel.setBounds(82, 140, 276, 20);
		graphPanel.add(raiseGraphPanel);
		
		JPanel smileGraphPanel = new JPanel();
		smileGraphPanel.setBounds(82, 165, 276, 20);
		graphPanel.add(smileGraphPanel);
		
		JPanel clenchGraphPanel = new JPanel();
		clenchGraphPanel.setBounds(82, 190, 276, 20);
		graphPanel.add(clenchGraphPanel);
		
		JPanel lftSmrGraphPanel = new JPanel();
		lftSmrGraphPanel.setBounds(82, 215, 276, 20);
		graphPanel.add(lftSmrGraphPanel);
		
		JPanel rgtSmrGraphPanel = new JPanel();
		rgtSmrGraphPanel.setBounds(82, 245, 276, 20);
		graphPanel.add(rgtSmrGraphPanel);
		
		JPanel laughGraphPanel = new JPanel();
		laughGraphPanel.setBounds(82, 276, 276, 18);
		graphPanel.add(laughGraphPanel);
		
		JLabel faceLabel = new JLabel("    Face Expression");
		faceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		faceLabel.setBounds(0, 3, 336, 26);
		expressivePanel.add(faceLabel);
		
		JLabel graphLabel = new JLabel("    Graph Charts");
		graphLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		graphLabel.setBounds(336, 0, 357, 29);
		expressivePanel.add(graphLabel);
		
		JPanel affectivePanel = new JPanel();
		tabbedPane.addTab("Affective", null, affectivePanel, null);
		affectivePanel.setLayout(null);
		
		JPanel perfPanel = new JPanel();
		perfPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		perfPanel.setBounds(0, 30, 350, 311);
		affectivePanel.add(perfPanel);
		
		JPanel colPanel = new JPanel();
		colPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		colPanel.setBounds(350, 30, 343, 311);
		affectivePanel.add(colPanel);
		
		JLabel perfLabel = new JLabel("    Performance Matrix");
		perfLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		perfLabel.setBounds(0, 0, 350, 32);
		affectivePanel.add(perfLabel);
		
		JLabel colLabel = new JLabel("    Colors");
		colLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		colLabel.setBounds(350, 0, 343, 32);
		affectivePanel.add(colLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 698, 22);
		panel.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mntmApplication = new JMenu("Application");
		mnFile.add(mntmApplication);
		
		JMenu mntmConnection = new JMenu("Connection");
		mnFile.add(mntmConnection);
		
		JMenuItem serverConsole = new JMenuItem("Server");
		mntmApplication.add(serverConsole);
		
		JMenuItem serverConnect = new JMenuItem("Connect to Server");
		serverConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField host = new JTextField();
				JTextField port = new JTextField();
				
				Object[] fields= {
						"Host Name", host,
						"Port", port
				};
				JOptionPane.showConfirmDialog(null, fields, "Connect To Server", JOptionPane.OK_CANCEL_OPTION);
			}
		});
		mntmConnection.add(serverConnect);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(exitMenuItem);
	}
}
