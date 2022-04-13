package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Graphe.Arbre;

public class InterfaceArbre extends JPanel {
	private static final long serialVersionUID = 1L;
	private Arbre graphe;
	private JLabel erreur;
	private JTextArea resultat;
	private JButton afficherMatrice, afficherFsAps, afficherArcOuAretes, Rang, Distance, coloration, pruferCodage, pruferDecodage;
	JComboBox<String> combo;
	JButton BtSais;
	public InterfaceArbre() {
		super(null);
		this.setBackground(Color.white);
        this.setLayout(null);
		JLabel sais = new JLabel("Importer le graphe");
        sais.setBounds(10, 10, 150, 30);
        add(sais);
		erreur = new JLabel("Erreur");
		erreur.setBounds(340, 30, 130, 21);
		erreur.setVisible(false);
		add(erreur);
		afficherMatrice = new JButton("Afficher matrice");
        afficherMatrice.setBounds(10, 80, 150, 30);
        add(afficherMatrice);
		
        afficherFsAps = new JButton("Afficher FS et APS");
        afficherFsAps.setBounds(150, 80, 150, 30);
        add(afficherFsAps);
		
		afficherArcOuAretes = new JButton("Afficher les arêtes");
		afficherArcOuAretes.setBounds(300, 80, 150, 30);
		add(afficherArcOuAretes);
		
		pruferDecodage = new JButton("Décodage de prufer");
		pruferDecodage.setBounds(450, 80, 150, 30);
		add(pruferDecodage);
		
		Rang = new JButton("Le rang du graphe");
		Rang.setBounds(300, 150, 150, 30);
		add(Rang);

		coloration = new JButton("Coloration");
		coloration.setBounds(10, 150, 150, 30);
		add(coloration);
		
		Distance = new JButton("Distance");
		Distance.setBounds(150, 150, 150, 30);
		add(Distance);
		
		pruferCodage = new JButton("Codage de prufer");
		pruferCodage.setBounds(450, 150, 150, 30);
		add(pruferCodage);
		
		combo = new JComboBox<String>();
        combo.addItem("Veuillez choisir");
        combo.addItem("Importer la matrice d'adjascence");
        combo.addItem("Importer FS et APS");
        combo.addItem("Importer les arêtes");
        combo.setBounds(150, 10, 150, 30);
        combo.setEditable(false);
        add(combo);
        
        BtSais = new JButton("Choisir");
        BtSais.setBounds(300, 10, 150, 30);
        BtSais.setEnabled(false);
        add(BtSais);
        
        //page_oriente_non_value.add(js);
        resultat = new JTextArea(10, 10);
        JScrollPane js = new JScrollPane(resultat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(js);
        resultat.setBounds(10, 200, 520, 421);
        resultat.setOpaque(true);
        resultat.setBackground(new Color(100,128,128));
        resultat.setBackground(Color.LIGHT_GRAY);
        resultat.setAutoscrolls(true);
        resultat.setEditable(false);
        add(resultat);
		event();
	}

	public InterfaceArbre(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public InterfaceArbre(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public InterfaceArbre(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	public InterfaceArbre(int selectedIndex) {
		// TODO Auto-generated constructor stub
	}

	public void event() {
		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combo.getSelectedIndex() == 0)
		        	BtSais.setEnabled(false);
		        else
		        	BtSais.setEnabled(true);
			}
		});
		BtSais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphe = new Arbre(combo.getSelectedIndex());
			}
		});
		Rang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					
		        	int[] rang = graphe.rang();
		        	int n = rang[0];
		        	StringBuilder data=new StringBuilder();
		    		data.append(">>>>>>RANG DES SOMMETS<<<<<<\n");
		    		  for(int i=1;i<=n;i++)
		    		  {
		    			  data.append("Le rang du sommet "+i+" est = "+rang[i]+" \n");
		    		  }
		            int max = rang[1];
		    	    for (int i = 2; i <= n ; ++i) {
		    	        if(rang[i] > max)
		    	            max = rang[i];
		    	    }
		    	    if(max == Integer.MAX_VALUE)
		    	    	data.append(">>Le rang du grand est plus l'infini");
		    	    else
		    	    	data.append(">>Le rang du grand est égal à "+max);
					resultat.setText(data.toString());
				}
			}
		});
		afficherMatrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheMatricetext(resultat);
				}
			}
		});
		afficherFsAps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheFsApstext(resultat);
				}
			}
		});
		afficherArcOuAretes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheAretestext(resultat);
				}
			}
		});
		Distance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
		        	int[][] dist = graphe.distance();
					int n = dist[0][0];
					StringBuilder data=new StringBuilder();
		        	data.append(">>>>>>DISTANCE <<<<<<\n");
		            for(int i=1; i<=n; i++) {
		            	data.append("Sommet "+i+" : [ ");
		            	for(int j=1; j<=n; j++) {
		            		data.append(dist[i][j]+" ");
		            	}
		            	data.append(" ]\n");
		            }
		            resultat.setText(data.toString());
				}
			}
		});
		coloration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.colorationText(resultat);
				}
			}
		});
		pruferCodage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.pruferCodageText(resultat);
				}
			}
		});
		pruferDecodage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arbre.pruferDecodageText(resultat);
			}
		});
	}
	/*
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setColor(Color.red);
		g2D.fillRect(0,  0, getWidth(), getHeight());
	}*/

}