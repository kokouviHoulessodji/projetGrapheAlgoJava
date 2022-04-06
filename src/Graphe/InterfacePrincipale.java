package Graphe;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfacePrincipale extends JFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public InterfacePrincipale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 333, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGraphe_Orienter = new JButton("Graphe Orienté Non Valué");
		btnGraphe_Orienter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGrapheOrienteNonValue frame = new InterfaceGrapheOrienteNonValue();
				frame.setVisible(true);
				
			}
		});
		btnGraphe_Orienter.setBounds(81, 71, 138, 21);
		contentPane.add(btnGraphe_Orienter);
		
		JButton btnGraphe_Nom_Orienter = new JButton("Graphe Orienté Valué");
		btnGraphe_Nom_Orienter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceGrapheOrienteValue frame = new InterfaceGrapheOrienteValue();
				frame.setVisible(true);
			}
		});
		btnGraphe_Nom_Orienter.setBounds(81, 127, 180, 21);
		contentPane.add(btnGraphe_Nom_Orienter);
		
		
		JButton btnNewButton_3 = new JButton("Arbre");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfaceArbre frame = new InterfaceArbre();
				frame.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(81, 215, 138, 21);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("Aide");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { Desktop.getDesktop().browse(new URL("https://github.com/AllKings/projet-graph-algo").toURI()); } catch (Exception e1) {} 
			}
		});
		btnNewButton.setBounds(105, 288, 85, 21);
		contentPane.add(btnNewButton);
		
	}

}
