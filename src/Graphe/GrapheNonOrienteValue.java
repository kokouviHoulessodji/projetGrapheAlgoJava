package Graphe;

import java.util.Scanner;

import javax.swing.JTextArea;

public class GrapheNonOrienteValue extends GrapheNonOriente {
	private int [][]d_cout;
	public GrapheNonOrienteValue(boolean oriente) {
		super(oriente);
		saisir_cout();
	    matriceToAretes();
	}
	public GrapheNonOrienteValue(int val) {
		super(val);
		remplirCout();
	}
	public GrapheNonOrienteValue()
	{
		super();
	}
	public void remplirCout() {
		int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    d_cout = new int[n+1][n+1];
	    d_cout[0] = new int[2];
	    d_cout[0][0] = n;
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
	    for (int i = 0; i < m ; ++i) {
	        d_cout[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = getAretes()[i].getD_poids();
	    }
	}
	public void saisir_cout() {
		Scanner in = new Scanner(System.in);
	    int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = getD_nb_aretes();
	    d_cout = new int[n+1][n+1];
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
	    d_cout[0][0] = n;
	    for (int i = 0; i < m ; ++i) {
	        int p;
	        System.out.print("Saisir le poids de l'arc [ "+getAretes()[i].getD_sommet_depart()+", "+getAretes()[i].getD_sommet_arrive()+" ] : ");
	        while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				p = Integer.parseInt(input);
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
	        getAretes()[i].setD_poids(p);
	        d_cout[getAretes()[i].getD_sommet_depart().getD_numero()][getAretes()[i].getD_sommet_arrive().getD_numero()] = p;
	    }
	}

	public void kruskal(GrapheNonOrienteValue t) 
	{
	    trier();
	    afficheAretes();
	    int k=0, s, tt;
	    int n = d_matrice_d_adjascence[0][0];
	    //Initialiser cfc, pilch et prem
	    
	    t = new GrapheNonOrienteValue();
	    t.setD_nb_sommet(n);
	    t.setD_nb_aretes(n-1);
	    
	    t.setAretes(new Arete[n-1]);
	    System.out.println(">>>>>Le nouveau graphe obtenu contient les arêtes suivants : \n");
	    for (int i = 0; i < n-1; ++i)
	    {
	        s = getAretes()[i].getD_sommet_depart().getD_numero();
	        tt = getAretes()[i].getD_sommet_arrive().getD_numero();
	        if(cfc[s] != cfc[tt])
	        {
	        	System.out.println("[ "+s+" "+tt+" ]");
	        	t.getAretes()[k] = getAretes()[i];
	        	k++;
	            fusionner(cfc[s], cfc[tt]);
	        }
	    }
	    System.out.println("Résultats");
        System.out.print(">>PREM [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	System.out.print(prem[i]+" ");
        System.out.println("]");
        System.out.print(">>PILCH [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	System.out.print(pilch[i]+" ");
        System.out.println("]");
        System.out.print(">>CFC [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	System.out.print(cfc[i]+" ");
        System.out.println("]");
        
        //t.afficheMatrice();
        //t.afficheFsAps();
        //t.afficheAretes();
	}

	public void fusionner(int i, int j)
	// i et j sont les numéros des composantes à fusionner
	// en une seule composante qui portera le numéro le plus
	// petit des deux
	{
	    int k = prem[i];
	    while (pilch[k] != 0)
	    {
	        k = pilch[k];
	    }
	    pilch[k] = prem[j];
	    k = prem[j];
	    while (k != 0)
	    {
	        cfc[k] = i;
	        k = pilch[k];
	    }
	}
	//TESTED
	//2e version avec un seul parcours de prem de i
	public void fusionner2(int i, int j)
	{
	    //Si on veut favoriser la composante ayant le plus petit numéro
	    //if(i < j) std::swap(i, j);
	    int k = prem[i];
	    while (pilch[k] != 0)
	    {
	        cfc[k] = j;
	        k = pilch[k];
	    }
	    cfc[k] = j;
	    pilch[k] = prem[j];
	    prem[j] = prem[i];
	}

	public boolean Dantzig(StringBuilder data) {
	    //c : matrice des coûts qui sera remplacée par la matrice des distances
	    //Renvoie false si le graphe contient un circuit absorbant
	    int n = (int)(d_cout[0][0]);
	    int [][]c = d_cout;
	    int k, i ,j;
	    int x;
	    for (k = 1; k < n; ++k)
	    {
	        for (i = 1; i <= k; ++i)
	        {
	            for (j = 1; j <= k; ++j)
	            {
	                if((x = c[i][j] + c[j][k+1]) < c[i][k+1])
	                    c[i][k+1] = x;
	                if((x = c[k+1][j] + c[j][i]) < c[k+1][i])
	                    c[k+1][i] = x;
	            }//fin for j
	            if((c[i][k+1] + c[k+1][i]) < 0)
	            {
	                System.out.println("Présence d'un circuit absorbant passant par " + i + " et "+ (k+1));
	                System.out.println("Nouvelle Matrice des couts :");
	                data.append("Présence d'un circuit absorbant passant par " + i + " et "+ (k+1)+"\n");
	                data.append("Nouvelle Matrice des couts :\n");
	                for (int l = 1; l <= n ; ++l) {
	                    for (int p = 1; p <= n ; ++p) {
	                    	if(c[l][p] == Integer.MAX_VALUE) {
	                    		System.out.print("∞ ");
		                    	data.append("∞ ");
	                    	}
	                    	else {
	                    		System.out.print(c[l][p]+" ");
		                    	data.append(c[l][p]+" ");
	                    	}
	                    	
	                    }
	                    System.out.println();
	                    data.append("\n");
	                }
	                return false;
	            }
	        }//fin for i
	        for (i = 1; i <= k; ++i)
		        for (j = 1; j <= k; ++j)
		            if((x = c[i][k+1] + c[k+1][j]) < c[i][j])
		                c[i][j] = x;
	    }//fin for k
	    
	    System.out.println("Nouvelle Matrice des couts :");
	    data.append("Nouvelle Matrice des couts :\n");
	    for (int l = 1; l <= n ; ++l) {
            for (int p = 1; p <= n ; ++p) {
            	if(c[l][p] == Integer.MAX_VALUE) {
            		System.out.print("∞ ");
                	data.append("∞ ");
            	}
            	else {
            		System.out.print(c[l][p]+" ");
                	data.append(c[l][p]+" ");
            	}
            	
            }
            System.out.println();
            data.append("\n");
        }
        System.out.println("Non présence d'un circuit absorbant");
        data.append("Non présence d'un circuit absorbant\n");
	    return true;
	}

	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	        System.out.println( "1 - Kruskal : taper 1");
	        System.out.println( "2 - Dantzig : taper 2" );
	    	System.out.println( "3 - Afficher la matrice d'adjascence, FS et APS et les arêtes du graphe : taper 3" );
	        System.out.println( "4 - Pour quitter : taper 4" );
	        while(true)
    		{
    			String input = in.nextLine();
    			try
    			{	
    				choix = Integer.parseInt(input);
    				break;
    			}
    			catch(NumberFormatException ex)
    			{
    				System.out.print("Vous devez taper une valeur numérique:");
    			}
    		}
	    }
	    while (choix < 1 || choix > 4);
	    return choix;
	}
	public void run()
	{
	    int choix = menu();
	    while (choix != 4)
	    {
	    	switch(choix)
	    	{
	    		case 1:
	    			prem = new int[getD_nb_sommet()+1];
		            pilch = new int[getD_nb_sommet()+1];
		            cfc = new int[getD_nb_sommet()+1];
		            for (int i = 0; i <= getD_nb_sommet() ; ++i) {
		    	        cfc[i] = i;
		    	        prem[i] = i;
		    	        pilch[i] = 0;
		    	    }
		            GrapheNonOrienteValue t = new GrapheNonOrienteValue();
		            kruskal(t);
		            System.out.println(">>Les caractéristiques du graphe recevrant minimal obtenu");
		            t.aretesToMatrice();
		            t.matriceToFsAps();
		            t.afficheMatrice();
		            t.afficheFsAps();
		            
		            //System.out.println(">>Les caractéristiques du graphe recevrant minimal obtenu");
		            //t.afficheMatrice();
		            //t.afficheFsAps();
		            break;
	    		case 2:
	    			System.out.println(Dantzig(null));
	    		case 3:
	    			afficheMatrice();
	    			afficheFsAps();
	    			afficheAretes();
	    			break;
	    		default: System.exit(0);
	    	}
	        choix = menu();
	    }
	}
	@Override
	public void afficheAretestext(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		data.append(">>>>>>ARETES<<<<<<\n");
		data.append("Nombre d'arêtes = "+getD_nb_aretes()+"\n");
		  for(int i=0;i<getD_nb_aretes();i++)
		  {
			  data.append("Arête n "+(i+1)+" : [ "+getAretes()[i].getD_sommet_depart()+" "+getAretes()[i].getD_sommet_arrive()+" ] - coût : "+getAretes()[i].getD_poids()+"\n");
		  }
		textArea.setText(data.toString());
		//textArea.setEditable(false);
	}
	public void kurskalText(JTextArea textArea)
	{	
		StringBuilder data=new StringBuilder();
		prem = new int[getD_nb_sommet()+1];
        pilch = new int[getD_nb_sommet()+1];
        cfc = new int[getD_nb_sommet()+1];
        for (int i = 0; i <= getD_nb_sommet() ; ++i) {
	        cfc[i] = i;
	        prem[i] = i;
	        pilch[i] = 0;
	    }
        GrapheNonOrienteValue t = new GrapheNonOrienteValue();
        kruskal(t);
        data.append(">>PREM [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(prem[i]+" ");
        data.append("]\n");
        data.append(">>PILCH [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(pilch[i]+" ");
        data.append("]\n");
        data.append(">>CFC [ ");
        for(int i=1; i<=getD_nb_sommet(); ++i)
        	data.append(cfc[i]+" ");
        data.append("]\n");
        data.append(">>Les caractéristiques du graphe recevrant minimal obtenu\n");
        //t.afficheMatrice();
        //t.afficheFsAps();
		textArea.setText(data.toString());
		//textArea.setEditable(false);
	}
	public void dantzigText(JTextArea resultat) {
		StringBuilder data = new StringBuilder();
		System.out.println(Dantzig(data));
		resultat.setText(data.toString());
	}
	public void ajoutNouvelArete(int sommet1, int sommet2, int poids) {
		d_matrice_d_adjascence[0][1] = d_matrice_d_adjascence[0][1] + 2;
		d_matrice_d_adjascence[sommet1][sommet2] = 1;
		d_matrice_d_adjascence[sommet2][sommet1] = 1;
		d_cout[sommet1][sommet2] = poids;
		d_cout[sommet2][sommet1] = poids;
		
		matriceToFsAps();
		Arete[] NAretes = new Arete[d_nb_aretes+1];
		for(int i=0; i<d_nb_aretes; i++) {
			NAretes[i] = aretes[i];
		}
		NAretes[d_nb_aretes] = new Arete(new Sommet(sommet1), new Sommet(sommet2), poids);
		aretes = NAretes;
		setD_nb_aretes(getD_nb_aretes() + 1);
	}
	public void supprimerArete(int arete) {
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_depart().getD_numero()][getAretePos(arete).getD_sommet_arrive().getD_numero()] = 0;
		d_matrice_d_adjascence[getAretePos(arete).getD_sommet_arrive().getD_numero()][getAretePos(arete).getD_sommet_depart().getD_numero()] = 0;
		d_cout[getAretePos(arete).getD_sommet_depart().getD_numero()][getAretePos(arete).getD_sommet_arrive().getD_numero()] = Integer.MAX_VALUE;
		d_cout[getAretePos(arete).getD_sommet_arrive().getD_numero()][getAretePos(arete).getD_sommet_depart().getD_numero()] = Integer.MAX_VALUE;
		d_matrice_d_adjascence[0][1] = d_matrice_d_adjascence[0][1] - 2;
		
		matriceToFsAps();
		Arete[] NAretes = new Arete[d_nb_aretes-1];
		for(int i=0; i<arete; i++) {
			NAretes[i] = aretes[i];
		}
		for(int i=arete; i<d_nb_aretes-1; i++) {
			NAretes[i] = aretes[i+1];
		}
		aretes = NAretes;
		setD_nb_aretes(getD_nb_aretes() - 1);
	}

}
