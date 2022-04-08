package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;


import Graphe.*;

public class Fenetre extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int l, h;
	private CardLayout pile;
	private GridLayout gl;
	private JPanel conteneur, droite, gauche, menu , contenant, saisieGraphe;
	private JPanel page_accueil, page_oriente_non_value, page_oriente_value;
	private JPanel page_non_oriente_non_value, page_non_oriente_value, page_arbre, page_saisie;
	private JLabel accueil, oriente_non_value, oriente_value;
	private JLabel non_oriente_non_value, non_oriente_value, arbre;
	private String grapheCourant;
	private JTextArea resultat;
	
	private Graphe graphe;
	JComboBox<String> combo;
	JButton BtSais;

	@Override
	public void run() {

	}
	public Fenetre() {
		
		pile = new CardLayout();
		gl = new GridLayout(6,1,0,2);
		
		conteneur = new JPanel();
		contenant = new JPanel();
		droite = new JPanel();
		gauche = new JPanel();
		menu = new JPanel();
		saisieGraphe = new JPanel();
		page_accueil = new JPanel();
		page_oriente_non_value = new JPanel();
		page_oriente_value = new JPanel();
		page_non_oriente_non_value = new JPanel();
		page_non_oriente_value = new JPanel();
		page_arbre = new JPanel();
		page_saisie = new JPanel();
		
		
		accueil = new JLabel();
		oriente_non_value = new JLabel();
		oriente_value = new JLabel();
		non_oriente_non_value = new JLabel();
		non_oriente_value = new JLabel();
		arbre = new JLabel();
		
		/*Dimension de l'ecran*/
		l = 900;
        h = 600;
        
        /*Boutton*/
        accueil.setBackground(new Color(129,20,83));
        accueil.setText("ACCUEIL");
        accueil.setHorizontalAlignment(SwingConstants.CENTER);
        accueil.setVerticalAlignment(SwingConstants.CENTER);
        accueil.setFont(new Font("Calibri", 1, 18));
        accueil.setForeground(Color.white);
        accueil.setOpaque(true);
        
        oriente_non_value.setBackground(new Color(128,128,128));
        oriente_non_value.setText("ORIENTE NON VALUE");
        oriente_non_value.setHorizontalAlignment(SwingConstants.CENTER);
        oriente_non_value.setVerticalAlignment(SwingConstants.CENTER);
        oriente_non_value.setFont(new Font("Calibri", 1, 18));
        oriente_non_value.setForeground(Color.white);
        oriente_non_value.setOpaque(true);
        
        oriente_value.setBackground(new Color(128,128,128));
        oriente_value.setText("ORIENTE VALUE");
        oriente_value.setHorizontalAlignment(SwingConstants.CENTER);
        oriente_value.setVerticalAlignment(SwingConstants.CENTER);
        oriente_value.setFont(new Font("Calibri", 1, 18));
        oriente_value.setForeground(Color.white);
        oriente_value.setOpaque(true);
        
        non_oriente_non_value.setBackground(new Color(128,128,128));
        non_oriente_non_value.setText("NON ORIENTE NON VALUE");
        non_oriente_non_value.setHorizontalAlignment(SwingConstants.CENTER);
        non_oriente_non_value.setVerticalAlignment(SwingConstants.CENTER);
        non_oriente_non_value.setFont(new Font("Calibri", 1, 18));
        non_oriente_non_value.setForeground(Color.white);
        non_oriente_non_value.setOpaque(true);
        
        non_oriente_value.setBackground(new Color(128,128,128));
        non_oriente_value.setText("NON ORIENTE VALUE");
        non_oriente_value.setHorizontalAlignment(SwingConstants.CENTER);
        non_oriente_value.setVerticalAlignment(SwingConstants.CENTER);
        non_oriente_value.setFont(new Font("Calibri", 1, 18));
        non_oriente_value.setForeground(Color.white);
        non_oriente_value.setOpaque(true);
        
        arbre.setBackground(new Color(128,128,128));
        arbre.setText("ARBRE");
        arbre.setHorizontalAlignment(SwingConstants.CENTER);
        arbre.setVerticalAlignment(SwingConstants.CENTER);
        arbre.setFont(new Font("Calibri", 1, 18));
        arbre.setForeground(Color.white);
        arbre.setOpaque(true);
        
        
        
        
        /*Conteneur des bouttons*/
        menu.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, Color.white));
        menu.setPreferredSize(new Dimension(l-600,h));
        menu.setBackground(Color.white);
        menu.setLayout(gl);
        menu.add(accueil);
        menu.add(oriente_non_value);
        menu.add(oriente_value);
        menu.add(non_oriente_non_value);
        menu.add(non_oriente_value);
        menu.add(arbre);
        
        
        page_accueil.setBackground(Color.white);
        page_accueil.setLayout(new BorderLayout());
        
        //Remplir des éléments de la page Graphe Orienté Non Valué
        page_oriente_non_value.setBackground(Color.white);
        page_oriente_non_value.setLayout(null);
        
        resultat = new JTextArea();
        resultat.setBounds(10, 160, 400, 221);
        resultat.setOpaque(true);
        resultat.setBackground(new Color(100,128,128));
        resultat.setBackground(Color.LIGHT_GRAY);
        resultat.setAutoscrolls(true);
        page_oriente_non_value.add(resultat);
        JLabel sais = new JLabel("Importer le graphe");
        sais.setBounds(10, 10, 160, 21);
        page_oriente_non_value.add(sais);
        combo = new JComboBox<String>();
        combo.addItem("Veuillez choisir");
        combo.addItem("Importer la matrice d'adjascence");
        combo.addItem("Importer FS et APS");
        combo.addItem("Importer les arcs ou arêtes");
        
       
        combo.setBounds(160, 10, 184, 21);
        combo.setEditable(false);
        
        page_oriente_non_value.add(combo);
        BtSais = new JButton("Choisir");
        BtSais.setBackground(Color.blue);
        BtSais.setBounds(340, 10, 184, 21);
        BtSais.setOpaque(true);
        BtSais.setBackground(new Color(100,128,128));
        BtSais.setEnabled(false);
        
        
        page_oriente_non_value.add(BtSais);
        
        /*
        JButton btnAjouterSommet = new JButton("Ajouter Sommet");
		btnAjouterSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAjouterSommet.setBounds(315, 31, 184, 21);
		page_oriente_non_value.add(btnAjouterSommet);
		btnAjouterSommet.setEnabled(false);
		JButton btnSupprimerSommet = new JButton("Supprimer Sommet");
		btnSupprimerSommet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSupprimerSommet.setBounds(315, 62, 184, 21);
		page_oriente_non_value.add(btnSupprimerSommet);
		btnSupprimerSommet.setEnabled(false);
		JButton btnAjouterArc = new JButton("Ajouter Arc");
		btnAjouterArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAjouterArc.setBounds(315, 139, 184, 21);
		page_oriente_non_value.add(btnAjouterArc);
		JButton btnSupprimerArc = new JButton("Supprimer Arc");
		btnSupprimerArc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnSupprimerArc.setBounds(22, 329, 184, 21);
		page_oriente_non_value.add(btnSupprimerArc);*/
        
		//Remplir des éléments de la page Graphe Orienté Valué
        page_oriente_value.setBackground(Color.white);
        page_oriente_value.setLayout(new BorderLayout());
        
        page_non_oriente_non_value.setBackground(Color.white);
        page_non_oriente_non_value.setLayout(new BorderLayout());
        
        page_non_oriente_value.setBackground(Color.white);
        page_non_oriente_value.setLayout(new BorderLayout());
        
        page_arbre.setBackground(Color.white);
        page_arbre.setLayout(new BorderLayout());
        
        page_saisie.setBackground(Color.white);
        page_saisie.setLayout(new BorderLayout());
        //page_saisie.add(corps_saisie);
        //page_saisie.add(pieds_saisie, BorderLayout.SOUTH);
        
        contenant.setLayout(pile);
        contenant.setPreferredSize(new Dimension(l - 200,h - 30));
        contenant.setBackground(Color.white);
        contenant.setBorder(new MatteBorder(0,2,0,0,new Color(128, 128, 128)));
        contenant.add(page_accueil,"accueil");  
        contenant.add(page_oriente_non_value,"onv");  
        contenant.add(page_oriente_value,"ov"); 
        contenant.add(page_non_oriente_non_value,"nonv"); 
        contenant.add(page_non_oriente_value,"nov");
        contenant.add(page_arbre,"a");
        contenant.add(page_saisie,"sg");
        pile.show(contenant, "accueil");
        
        saisieGraphe.setLayout(new BorderLayout());
        saisieGraphe.setBorder(new MatteBorder(0,2,0,0,new Color(128, 128, 128)));
        saisieGraphe.setPreferredSize(new Dimension(l - 600,25));
        saisieGraphe.setBackground(Color.white);
        
        gauche.setLayout(new BorderLayout());
        gauche.setPreferredSize(new Dimension(l - 600,h));
        gauche.setBackground(Color.white);
        gauche.add(menu);
        
        droite.setLayout(new BorderLayout());
        droite.setPreferredSize(new Dimension(l - 200,h));
        droite.setBackground(Color.white);
        droite.setBorder(new MatteBorder(0,2,0,0,new Color(128,128,128)));
        droite.add(saisieGraphe, BorderLayout.NORTH);
        droite.add(contenant);
        
        conteneur.setBorder(new MatteBorder(0,0,2,0,new Color(128, 128, 128)));
        conteneur.setLayout(new BorderLayout());
        conteneur.add(gauche, BorderLayout.WEST);
        conteneur.add(droite);
        
        
        getContentPane().add(conteneur);
	    setSize(l,h);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    setTitle("GRAPHE");
	    event();
	}
	private void event() {
		accueil.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
            	accueil.setBackground(new Color(129,20,83));
                oriente_non_value.setBackground(new Color(128,128,128));
                oriente_value.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                pile.show(contenant, "accueil");
                
            }
        });
		oriente_non_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                
            	accueil.setBackground(new Color(128,128,128));
                oriente_non_value.setBackground(new Color(129,20,83));
                oriente_value.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                grapheCourant = "onv";
                pile.show(contenant, "onv");
                
            }
        });
		
		
		oriente_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                accueil.setBackground(new Color(128,128,128));
                non_oriente_non_value.setBackground(new Color(128,128,128));
                oriente_value.setBackground(new Color(129,20,83));
                oriente_non_value.setBackground(new Color(128,128,128));
                non_oriente_value.setBackground(new Color(128,128,128));
                arbre.setBackground(new Color(128,128,128));
                grapheCourant = "ov";
                pile.show(contenant, "ov");
                
            }
        });
		non_oriente_non_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(129,20,83));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(128,128,128));
                 arbre.setBackground(new Color(128,128,128));
                 grapheCourant = "nonv";
                 pile.show(contenant, "nonv");
                
            }
        });
		
		non_oriente_value.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(128,128,128));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(129,20,83));
                 arbre.setBackground(new Color(128,128,128));
                 grapheCourant = "nov";
                 pile.show(contenant, "nov");
                
            }
        });
		arbre.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt) 
            {
                 accueil.setBackground(new Color(128,128,128));
                 non_oriente_non_value.setBackground(new Color(128,128,128));
                 oriente_value.setBackground(new Color(128,128,128));
                 oriente_non_value.setBackground(new Color(128,128,128));
                 non_oriente_value.setBackground(new Color(128,128,128));
                 arbre.setBackground(new Color(129,20,83));
                 grapheCourant = "abr";
                 pile.show(contenant, "a");
                
            }
        });
		
		
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
				switch(grapheCourant) {
				case "onv" :
					graphe = new GrapheOriente(combo.getSelectedIndex());
					break;
				case "ov" :
					graphe = new GrapheOrienteValue(combo.getSelectedIndex());
					break;
				case "nonv" :
					graphe = new GrapheNonOriente(combo.getSelectedIndex());
					break;
				case "nov" :
					graphe = new GrapheNonOrienteValue(combo.getSelectedIndex());
					break;
				case "abr" :
					graphe = new Arbre(combo.getSelectedIndex());
					break;
				}
				
				//graphe.afficheMatrice();
				//graphe.afficheFsAps();
				//graphe.afficheAretes();
				graphe.afficheMatricetext(resultat);
			}
		});
	}

}
