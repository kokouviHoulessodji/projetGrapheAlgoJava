package Graphe;

import java.util.Scanner;

import javax.swing.JTextArea;

public class GrapheNonOriente extends Graphe {

	public GrapheNonOriente(boolean oriente) {
		super(oriente);
	}
	public GrapheNonOriente()
	{
		super();
	}
	public GrapheNonOriente(int val) {
		switch(val) {
			case 1:
				chargerMatriceFromFichier();
				break;
			case 2:
				chargerFsApsFromFichier();
				break;
			case 3:
				chargerAretesFromFichier();
				break;
		}
	}
	/*
	 * Déterminer la coloration d'un graphe
	 */
	public int[] coloration() //TESTED
	{
	    int n = d_aps[0];
	    int[] f = new int[n+1];
	    for (int i = 1; i <= n ; ++i) {
	        f[i] = 0;
	    }
	    f[0] = n;
	    for (int i = 1; i <= n ; ++i) {
	        int c = 1;
	        int k = d_aps[i];
	        while (d_fs[k]!=0 && f[d_fs[k]] == c) {
	            c++; k++;
	        }
	        f[i] = c;
	    }
	    return f;
	}
	/*
	 * Le nombre d'un graphe
	 */
	public int nombre_chromatique(int []f)//TESTED
	{
	    int max = f[1];
	    for (int i = 2; i <= f[0] ; ++i) {
	        if(f[i] > max)
	            max = f[i];
	    }
	    return max;
	}

	public int menu()
	{
		Scanner in = new Scanner(System.in);
	    int choix;
	    do {
	    	System.out.println( "-----------------------------MENU------------------------------------- " );
	    	System.out.println( "Quel algorithme souhaitez-vous testé sur ce graphe orienté? " );
	    	System.out.println( "1 - Coloration et nombre chromatique du graphe : taper 1" );
	    	System.out.println( "2 - Afficher la matrice d'adjascence, FS et APS et les arêtes du graphe : taper 2" );
	    	System.out.println( "3 - Pour quitter : taper 3" );
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
	    }while (choix < 1 || choix > 3);
	    return choix;
	}
	public void run()
	{
		Scanner in = new Scanner(System.in);
	    int choix = menu();
	    while(choix != 3)
	    {
	    	switch(choix) 
	    	{
	    		case 1 :
	    			int []f = coloration();
		            int nChr = nombre_chromatique(f);
		            System.out.println("La coloration correspondant à votre graphe est : \n[ ");
		            for (int i = 1; i <= f[0] ; ++i) {
		            	System.out.println("Couleur du sommet "+i+" = "+f[i]+" ");
		            }
		            System.out.println("]");
		            System.out.println("Le nombre chromatique du graphe est égal à "+nChr);
		            break;
	    		case 2 :
	    			afficheMatrice();
	    			afficheFsAps();
	    			afficheAretes();
	    			break;
	    		default :
	    			System.exit(0);
	    	}
	        choix = menu();
	    }
	}
	public void colorationText(JTextArea textArea) {
		int []f = coloration();
        int nChr = nombre_chromatique(f);
        StringBuilder data=new StringBuilder();
    	data.append(">>>>>>La coloration correspondant à votre graphe est : \n");
        for (int i = 1; i <= f[0] ; ++i) {
        	data.append("Couleur du sommet "+i+" = "+f[i]+" \n");
        }
        data.append("\n");
        data.append("Le nombre chromatique du graphe est égal à "+nChr+"\n");
        textArea.setText(data.toString());
	}
	

}
