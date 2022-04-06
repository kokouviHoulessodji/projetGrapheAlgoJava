package Graphe;

import java.util.Scanner;

public class GrapheOriente extends Graphe{

	public GrapheOriente()
	{
		super(true);
	}
	/*
	Graphe_oriente Graphe_oriente::calculerGrapheReduit(int *prem, int *pilch, int *cfc, int *fs, int *aps, int *&fsr, int *&apsr) const {
	    int s, kr = 1, nbc = prem[0];
	    bool *deja_mis = new bool[nbc + 1];
	    fsr = new int[fs[0] + 1];
	    apsr = new int[nbc + 1];
	    apsr[0] = kr;
	    for (int i = 1; i < nbc; i++) {
	        apsr[i] = kr;
	        for (int i = 1; i <= nbc; i++) {
	            deja_mis[i] = false;
	        }
	        deja_mis[i] = true;
	        s = prem[0];
	        while (s != 0) {
	            for (int t = aps[s]; fs[t] != 0; t++) {
	                if (deja_mis[cfc[fs[t]]] == false) {
	                    fs[kr] = cfc[fs[t]];
	                    kr++;
	                    deja_mis[cfc[fs[t]]] = true;
	                }
	            }
	            s = pilch[s];
	        }
	        fsr[kr] = 0;
	        kr++;
	    }
	    fsr[0] = kr - 1;
	    delete[] deja_mis;
	}
	*/
	public void traverse(int s, int p) {
	    int t;
	    p++;
	    num[s] = p;
	    ro[s] = p; // numérote s et initialise ro[s]
	    empiler(s, tarj);
	    entarj[s] = true;
	    for (int k = d_aps[s]; (t = d_fs[k]) != 0; k++) {
	        if (num[t] == 0) // si t n'est pas encore numéroté { pred[t] = s;
	            traverse(t, p);
	        if (ro[t] < ro[s])
	            ro[s] = ro[t];
	        else {
	            if ((num[t] < ro[s]) && entarj[t])
	                ro[s] = num[t];
	        }
	    }
	    int k = 0;
	    if (ro[s] == num[s]) {
	       k++;
	       int u;
	       do {
	    	   u = depiler(tarj);
	           entarj[u] = false;
	           empiler(u, pilch);
	           cfc[u] = k;
	       } while (u != s);
	       prem[k] = pilch[0];
	       pilch[0] = 0;
	    }
	}


	public void graph_reduit() {
		int []fs = getFs();
		int []aps = getAps();
	    int s, kr = 1, nbc = prem[0];
	    boolean []deja_mis = new boolean[nbc + 1];
	    fsr = new int[fs[0] + 1];
	    apsr = new int[nbc + 1];
	    apsr[0] = kr;
	    for (int i = 1; i < nbc; i++) {
	        apsr[i] = kr;
	        for (int j = 1; j <= nbc; j++) {
	            deja_mis[j] = false;
	        }
	        deja_mis[i] = true;
	        s = prem[0];
	        while (s != 0) {
	            for (int t = aps[s]; fs[t] != 0; t++) {
	                if (deja_mis[cfc[fs[t]]] == false) {
	                    fs[kr] = cfc[fs[t]];
	                    kr++;
	                    deja_mis[cfc[fs[t]]] = true;
	                }
	            }
	            s = pilch[s];
	        }
	        fsr[kr] = 0;
	        kr++;
	    }
	    fsr[0] = kr - 1;
	}

	public void base_Greduit() {

	    int nr = apsr[0];
	    br = new int[nr + 1];
	    int []ddir = new int[nr + 1];
	    for (int i = 0; i <= nr; i++)
	        ddir[i] = 0;
	    for (int kr = 1; kr <= fsr[kr]; kr++)
	        ddir[fsr[kr]]++;
	    int nb = 0;
	    for (int c = 1; c <= nr; c++)
	        if (ddir[c] == 0) br[++nb] = c;
	    br[0] = nb;
	}

	public void afficher(int []base, int nb)
	{
		System.out.println("Bases : ");
	    for (int i = 0; i < nb; i++)
	        System.out.print( base[i] + " ");
	    System.out.println();
	}
	public void edition_bases() {

	    int nb = br[0]; // Nombre de CFC de l’unique base du graphe réduit
	    int []Base = new int[nb + 1]; // pile qui mémorise les sommets des bases du graphe initial Base[0] = nb;
	    int p = 1;
	    int som = prem[br[1]]; // premier sommet de la première CFC while (p >= 1)
	    {
	        if ((p <= nb) && (som != 0)) {
	            Base[p] = som;
	            p++;
	            if (p <= nb)
	                som = prem[br[p]];
	            else
	                afficher(Base, nb + 1); // Affiche le contenu du tableau Base //sommets d’une base du graphe initial
	            p--;
	            som = pilch[Base[p]];
	        }
	    }
	}

	public void fortconnexe(int []pred) {

	    int n = d_aps[0];
	    prem = new int[n + 1];
	    pilch = new int[n + 1];
	    cfc = new int[n + 1];
	    pred = new int[n + 1];
	    tarj = new int[n + 1];
	    entarj = new boolean[n + 1];
	    num = new int[n + 1];
	    ro = new int[n + 1];
	    int k = 0;
	    for (int i = 1; i <= n; i++) {
	        num[i] = 0;
	        pred[i] = 0;
	        ro[i] = 0;
	        entarj[i] = false;
	    }
	    pilch[0] = 0;
	    tarj[0] = 0;
	    for (int s = 1; s <= d_aps[0]; s++)
	        if (num[s] == 0)
	            traverse(s, k);
	    prem[0] = k;
	    System.out.print(">>PRED : [ ");
        for(int i=1; i<=n; i++)
        	System.out.print(pred[i]+" ");
        System.out.println(" ]");
	}

	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    do {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	        System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	        System.out.println( "1 - Traverser et numéroter les sommets du graphes : taper 1" );
	        System.out.println( "2 - Composantes fortement connexes : taper 2" );
	    	System.out.println( "3 - Afficher la matrice d'adjascence, FS et APS et les arêtes du graphe : taper 3" );
	    	System.out.println( "4 - Calculer la distance des sommets du graphe vers tous les autres sommets : taper 4");
	    	System.out.println( "5 - Calculer le rang des sommets du graphe : taper 5");
	        System.out.println( "6 - Pour quitter : taper 6" );
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
	    }while (choix < 1 || choix > 6);
	    return choix;
	}

	public void run() {
	    int choix = menu();
	    while(choix != 6)
	    {
	        if(choix == 1){
	            int s = 1, p = 0;
	            int n = d_aps[0];
	    	    prem = new int[n + 1];
	    	    pilch = new int[n + 1];
	    	    cfc = new int[n + 1];
	    	    tarj = new int[n + 1];
	    	    entarj = new boolean[n + 1];
	    	    num = new int[n + 1];
	    	    ro = new int[n + 1];
	            traverse(s, p);
	            System.out.print(">>NUM : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(num[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>RO : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(ro[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>PREM : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(prem[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>PILCH : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(pilch[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>CFC : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(cfc[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>TARJ : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(tarj[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>ENTARJ : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(entarj[i]);
	            System.out.println(" ]");
	        }
	        else if(choix == 2)
	        {
	        	int n = d_aps[0];
	            int []pred = null;
	            prem = new int[n + 1];
	    	    pilch = new int[n + 1];
	    	    cfc = new int[n + 1];
	    	    tarj = new int[n + 1];
	    	    entarj = new boolean[n + 1];
	    	    num = new int[n + 1];
	    	    ro = new int[n + 1];
	            fortconnexe(pred);
	            System.out.print(">>NUM : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(num[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>RO : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(ro[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>PREM : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(prem[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>PILCH : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(pilch[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>CFC : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(cfc[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>TARJ : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(tarj[i]+" ");
	            System.out.println(" ]");
	            System.out.print(">>ENTARJ : [ ");
	            for(int i=1; i<=n; i++)
	            	System.out.print(entarj[i]);
	            System.out.println(" ]");
	        }
	        else if(choix == 3) {
	        	afficheMatrice();
    			afficheFsAps();
    			afficheAretes();
	        }
	        else if(choix == 5) {
	        	int n = d_aps[0];
	        	int[] rang = rang();
	        	System.out.println(">>RANG : ");
	            for(int i=1; i<=n; i++)
	            	System.out.println("Le rang du sommet "+i+" est = "+rang[i]+" ");
	            System.out.println();
	            int max = rang[1];
	    	    for (int i = 2; i <= n ; ++i) {
	    	        if(rang[i] > max)
	    	            max = rang[i];
	    	    }
	    	    if(max == Integer.MAX_VALUE)
	    	    	System.out.println(">>Le rang du grand est plus l'infini");
	    	    else
	    	    	System.out.println(">>Le rang du grand est égal à "+max);
	        }
	        else if(choix == 4) {
	        	int n = d_aps[0];
	        	int[][] dist = distance();
	        	System.out.println(">>DISTANCE :");
	            for(int i=1; i<=n; i++) {
	            	System.out.print("Sommet "+i+" : [ ");
	            	for(int j=1; j<=n; j++) {
	            		System.out.print(dist[i][j]+" ");
	            	}
		            System.out.println(" ]");
	            }
	            	
	        }
	        else break;
	        choix = menu();
	    }
	}


}
