package Graphe;

import java.util.Scanner;

public class GrapheNonOrienteValue extends GrapheNonOriente {
	private int [][]d_cout;
	public GrapheNonOrienteValue() {
		super();
		saisir_cout();
	    matriceToAretes();
	}
	public GrapheNonOrienteValue(int val) {
		super(val);
	}
	public void saisir_cout() {
		Scanner in = new Scanner(System.in);
	    int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = d_nb_aretes;
	    d_cout = new int[n+1][n+1];
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
	    d_cout[0][0] = n;
	    for (int i = 0; i < m ; ++i) {
	        int p;
	        System.out.print("Saisir le poids de l'arc [ "+aretes[i].getD_sommet_depart()+", "+aretes[i].getD_sommet_arrive()+" ] : ");
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
	        aretes[i].setD_poids(p);
	        d_cout[aretes[i].getD_sommet_depart()][aretes[i].getD_sommet_arrive()] = p;
	    }
	}

	public void kruskal(int []prem, int []pilch, int []cfc) 
	{
	    trier();
	    afficheAretes();
	    int k=0, s, tt;
	    int n = d_matrice_d_adjascence[0][0];
	    //Initialiser cfc, pilch et prem
	    
	    GrapheNonOrienteValue t = new GrapheNonOrienteValue();
	    t.aretes = new Arete[n-1];
	    for (int i = 0; i < n-1; ++i)
	    {
	        s = aretes[i].getD_sommet_depart();
	        tt = aretes[i].getD_sommet_arrive();
	        if(cfc[s] != cfc[tt])
	        {
	        	t.aretes[k] = aretes[i];
	        	k++;
	            fusionner(cfc[s], cfc[tt], prem, pilch, cfc);
	            
	            System.out.println("---- i = "+i+" -----");
	            
	            System.out.print("CFC : ");
	            for (int j = 0; j <= n ; ++j) {
	            	System.out.print(cfc[j]+" ");
	            }
	            System.out.println();
	            System.out.print("PILCH : ");
	            for (int j = 0; j <= n ; ++j) {
	            	System.out.print(pilch[j]+" ");
	            }
	            System.out.println();
	            System.out.print("PREM : ");
	            for (int j = 0; j <= n ; ++j) {
	            	System.out.print(prem[j]+" ");
	            }
	            System.out.println();
	        }
	    }
	    t.aretesToMatrice();
	    t.matriceToFsAps();
	    System.out.println("Résultats");
        System.out.print(">>PREM [ ");
        for(int i=1; i<=d_nb_sommet; ++i)
        	System.out.print(prem[i]+" ");
        System.out.println();
        System.out.print(">>PILCH [ ");
        for(int i=1; i<=d_nb_sommet; ++i)
        	System.out.print(pilch[i]+" ");
        System.out.println();
        System.out.print(">>CFC [ ");
        for(int i=1; i<=d_nb_sommet; ++i)
        	System.out.print(cfc[i]+" ");
        System.out.println();
        System.out.println(">>Les caractéristiques du graphe recevrant minimal obtenu");
        t.afficheMatrice();
        t.afficheFsAps();
        t.afficheAretes();
	}

	public void fusionner(int i, int j, int []prem, int []pilch, int []cfc)
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
	public void fusionner2(int i, int j, int []prem, int []pilch, int []cfc)
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

	public boolean Dantzig() {
	    //c : matrice des coûts qui sera remplacée par la matrice des distances
	    //Renvoie false si le graphe contient un circuit absorbant
	    int n = (int)(d_cout[0][0]);
	    int [][]c = new int[n+1][n+1];
	    c = d_cout;
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
	                for (int l = 1; l <= n ; ++l) {
	                    for (int p = 1; p <= n ; ++p) {
	                    	System.out.print(c[l][p]+" ");
	                    }
	                    System.out.println();
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
        for (int l = 1; l <= n ; ++l) {
            for (int p = 1; p <= n ; ++p) {
            	System.out.print(c[l][p]+" ");
            }
            System.out.println();
        }
        System.out.println("Non présence d'un circuit absorbant");
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
	    			int[] prem = new int[d_nb_sommet+1];
		            int[] pilch = new int[d_nb_sommet+1];
		            int[] cfc = new int[d_nb_sommet+1];
		            for (int i = 0; i <= d_nb_sommet ; ++i) {
		    	        cfc[i] = i;
		    	        prem[i] = i;
		    	        pilch[i] = 0;
		    	    }
		            kruskal(prem, pilch, cfc);
		            /*
		            System.out.println("Résultats");
		            System.out.print(">>PREM [ ");
		            for(int i=1; i<=d_nb_sommet; ++i)
		            	System.out.print(prem[i]+" ");
		            System.out.println();
		            System.out.print(">>PILCH [ ");
		            for(int i=1; i<=d_nb_sommet; ++i)
		            	System.out.print(pilch[i]+" ");
		            System.out.println();
		            System.out.print(">>CFC [ ");
		            for(int i=1; i<=d_nb_sommet; ++i)
		            	System.out.print(cfc[i]+" ");
		            System.out.println();
		            System.out.println(">>Les caractéristiques du graphe recevrant minimal obtenu");
		            t.afficheMatrice();
		            t.afficheFsAps();*/
		            break;
	    		case 2:
	    			System.out.println(Dantzig());
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

}
