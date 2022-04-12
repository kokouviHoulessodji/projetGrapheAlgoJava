package vue;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Graphe.GrapheOrienteValue;

public class InterfaceOrienteValue extends JPanel {
	private static final long serialVersionUID = 1L;
	private GrapheOrienteValue graphe;
	private JLabel erreur;
	private JTextArea resultat;
	private JButton afficherMatrice, afficherFsAps, afficherArcOuAretes, Rang, Tarjan, Distance, Djikstra, ordonnancement;
	JComboBox<String> combo;
	JButton BtSais;

	public InterfaceOrienteValue() {
		super(null);
		this.setBackground(Color.white);
        this.setLayout(null);
		JLabel sais = new JLabel("Importer le graphe");
        sais.setBounds(10, 10, 160, 21);
        add(sais);
        erreur = new JLabel("Erreur");
		erreur.setBounds(340, 30, 130, 21);
		erreur.setVisible(false);
		add(erreur);
		afficherMatrice = new JButton("Afficher matrice");
        afficherMatrice.setBounds(10, 80, 130, 21);
        add(afficherMatrice);
		
        afficherFsAps = new JButton("Afficher FS et APS");
        afficherFsAps.setBounds(130, 80, 130, 21);
        add(afficherFsAps);
		
		afficherArcOuAretes = new JButton("Afficher les arêtes ou les arcs");
		afficherArcOuAretes.setBounds(260, 80, 130, 21);
		add(afficherArcOuAretes);
		
		Rang = new JButton("Le rang du graphe");
		Rang.setBounds(260, 150, 130, 21);
		add(Rang);
		
		Distance = new JButton("Distance");
		Distance.setBounds(130, 150, 130, 21);
		add(Distance);

		Tarjan = new JButton("Tarjan");
		Tarjan.setBounds(10, 150, 130, 21);
		add(Tarjan);
		
		
		Djikstra = new JButton("Djikstra");
		Djikstra.setBounds(390, 150, 130, 21);
		add(Djikstra);
		
		ordonnancement = new JButton("Ordonnancement");
		ordonnancement.setBounds(390, 80, 130, 21);
		add(ordonnancement);
		
		combo = new JComboBox<String>();
        combo.addItem("Veuillez choisir");
        combo.addItem("Importer la matrice d'adjascence");
        combo.addItem("Importer FS et APS");
        combo.addItem("Importer les arcs");
        combo.setBounds(160, 10, 184, 21);
        combo.setEditable(false);
        add(combo);
        
        BtSais = new JButton("Choisir");
        BtSais.setBackground(Color.blue);
        BtSais.setBounds(390, 10, 130, 21);
        BtSais.setOpaque(true);
        BtSais.setBackground(new Color(100,128,128));
        BtSais.setEnabled(false);
        add(BtSais);
        //JScrollPane js = new JScrollPane(resultat, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //page_oriente_non_value.add(js);
        resultat = new JTextArea(10, 10);
        resultat.setBounds(10, 200, 520, 421);
        resultat.setOpaque(true);
        resultat.setBackground(new Color(100,128,128));
        resultat.setBackground(Color.LIGHT_GRAY);
        resultat.setAutoscrolls(true);
        resultat.setEditable(false);
        add(resultat);
		event();
	}

	public InterfaceOrienteValue(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public InterfaceOrienteValue(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public InterfaceOrienteValue(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
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
				graphe = new GrapheOrienteValue(combo.getSelectedIndex());
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
		Tarjan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.afficheTarjanText(resultat);
				}
			}
		});
		Djikstra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.DijkstraText(resultat);
				}
			}
		});
		ordonnancement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(graphe == null)
					resultat.setText("Importer d'abord un graphe avant.");
				else
				{
					graphe.ordonnancementTexte(resultat);
				}
			}
		});
	}

}
