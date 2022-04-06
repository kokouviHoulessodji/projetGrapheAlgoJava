package Graphe;

import java.util.Scanner;

public class GrapheOrienteValue extends GrapheOriente {
	private int[][] d_cout;
	public GrapheOrienteValue() {
		super();
		saisir_cout();
	    matriceToAretes();
	}

	public void saisir_cout() //TESTED
	{
		Scanner in = new Scanner(System.in);
	    int infini = Integer.MAX_VALUE;
	    int n = d_aps[0];
	    int m = d_nb_aretes;
	    d_cout = new int[n+1][n+1];
	    d_cout[0] = new int[2];
	    d_cout[0][0] = n;
	    for (int i = 1; i <= n ; ++i)
	        d_cout[i] = new int[n+1];
	    for (int i = 1; i <= n ; ++i)
	        for (int j = 1; j <= n ; ++j)
	            d_cout[i][j] = infini;
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
	public void ordonnancement(int []d, int []fpc, int []appc, int []lc)
	{
	    fs_aps_2_fp_app();
	    int n = app[0], m = fp[0];
	    fpc = new int[m+1];
	    appc = new int[n+1]; appc[0] = n; lc = new int[n+1]; lc[0] = n;
	    int kc, t, lg;
	    lc[1] = 0;
	    fpc[1] = 0; // fin de la liste
	    appc[1] = 1;
	    kc = 1; // indice de la dernière place remplie dans fpc
	    for(int s = 2; s <= n; s++)
	    {
	        //calcul de lc[s] en fonction des prédécesseurs de s
	        lc[s] = 0;
	        appc[s] = kc+1; // début de la liste des prédécesseurs critiques de s
	        for (int k = app[s]; (t = fp[k]) != 0; k++)
	        {
	            lg = lc[t] + d[t];
	            if (lg >= lc[s])
	            {
	                if (lg > lc[s])
	                {
	                    lc[s] = lg; // Nouvelle le candidate a être critique
	                    kc = appc[s];
	                    fpc[kc] = t;
	                }
	                else // lg == lc[s] : ajouter un nouveau prédécesseur critique
	                {
	                    kc++;
	                    fpc[kc] = t;
	                }
	            }
	        }
	        kc++ ;
	        fpc[kc] = 0;//fin de la liste des prédécesseurs critiques de s
	    }
	    fpc[0] = kc;
	}

	public void Dijkstra(int s, int []d, int []pr)//TESTED
	{
	    int ind; /* nombre d'elements qui restent a traiter */
	    int i, j = 0, k, v;
	    int n = d_aps[0];
	    int m = d_fs[0];
	    int []inS = new int[n+1];
	    /* sert a dire quels sont les sommets qui restent a traiter inS[i] = 0 ou 1*/
	    /* initialisation des tableaux d, pr et inS*/
	    for (i = 1; i <= n; i++)
	    {
	        d[i] = d_cout[s][i];
	        inS[i] = 1;
	        k = d_aps[s];
	        while(d_fs[k] != 0 && d_fs[k] != i)
	            k++;
	        if(d_fs[k] == i)
	            pr[i] = 1;
	        else
	            pr[i] = -1;
	    }
	    d[s] = 0;
	    pr[s] = 0;
	    inS[s] = 0; /* on supprime le sommet 1 */
	    ind = n - 1;
	    while (ind > 0)
	    {
	        /* calcule du minimum selon d des sommets de S */
	        m = Integer.MAX_VALUE;
	        for (i = 1; i <= n; i++)
	            if (inS[i] == 1)
	                if (d[i] < m) {
	                    m = d[i];
	                    j = i;
	                }
	        if (m == Integer.MAX_VALUE)
	            return;
	        inS[j] = 0;
	        ind--;
	        k = d_aps[j];
	        while (d_fs[k] != 0)
	        {
	            if (inS[d_fs[k]] == 1) {
	                v = d[j] + d_cout[j][d_fs[k]];
	                if (v < d[d_fs[k]]) {
	                    d[d_fs[k]] = v;
	                    pr[d_fs[k]] = j;
	                }
	            }
	            k++;
	        }
	    }
	}
	public void Dijkstra(int [][]mat_d, int [][]mat_pred)//TESTED
	{
	    int n = d_aps[0];
	    mat_d = new int[n+1][n+1];
	    mat_d[0][0] = n;
	    mat_pred = new int[n+1][n+1];
	    mat_pred[0][0] = n;
	    for(int i=1; i<=n; ++i)
	    {
	    	for(int j=1; j<=n; ++j)
	    	{
	    		mat_d[i][j] = 0;
	    		mat_pred[i][j] = 0;
	    	}
	    }
	    for (int s = 1; s <= n; ++s)
	        Dijkstra(s, mat_d[s], mat_pred[s]);
	    System.out.println(">>Remarque : la valeur "+Integer.MAX_VALUE+" correspond à 'plus l'infini.'");
	    System.out.println("------ Matrice des distances ------");
        for(int i=1;i<=n;i++){
            System.out.print("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            	System.out.print(mat_d[i][j]+" ");
            System.out.println("]");
        }
        System.out.println("------ Matrice des pred ------");
        for(int i=1;i<=n;i++){
        	System.out.print("sommet "+i+" : [ ");
            for(int j=1;j<=n;j++)
            	System.out.print(mat_pred[i][j]+" ");
            System.out.println("]");
        }
	    
	}
	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	        System.out.println( "1 - Djikstra : taper 1");
	        System.out.println( "2 - Ordonnancement : taper 2" );
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
	        if(choix == 1){
	            int[][] dist = new int[d_nb_sommet+1][d_nb_sommet+1], pred = new int[d_nb_sommet+1][d_nb_sommet+1];
	            
	            Dijkstra(dist, pred);
	            /*
	            System.out.println("------ Matrice des distances ------");
	            for(int i=1;i<=dist[0][0];i++){
	                System.out.print("sommet "+i+" : [ ");
	                for(int j=1;j<=dist[0][0];j++)
	                	System.out.print(dist[i][j]+" ");
	                System.out.println("]");
	            }
	            System.out.println("------ Matrice des pred ------");
	            for(int i=1;i<=dist[0][0];i++){
	            	System.out.print("sommet "+i+" : [ ");
	                for(int j=1;j<=dist[0][0];j++)
	                	System.out.print(pred[i][j]+" ");
	                System.out.println("]");
	            }*/
	        }
	        else if(choix == 2)
	        {
	        	//ordonnancement
	        }
	        else if(choix == 3) {
	        	afficheMatrice();
    			afficheFsAps();
    			afficheAretes();
	        }
	        else break;
	        choix = menu();
	    }
	}


}
