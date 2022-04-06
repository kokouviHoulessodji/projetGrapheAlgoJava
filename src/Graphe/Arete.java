package Graphe;

public class Arete {
		protected int d_sommet_depart, d_sommet_arrive;
		protected int d_poids;

	public Arete(int sommet_depart, int sommet_arrive, int poids) {
		this.d_poids = poids;
		this.d_sommet_arrive = sommet_arrive;
		this.d_sommet_depart = sommet_depart;
	}
	public Arete(int sommet_depart, int sommet_arrive) {
		this(sommet_depart, sommet_arrive, 0);
	}
	public int getD_sommet_depart() {
		return d_sommet_depart;
	}

	public void setD_sommet_depart(int d_sommet_depart) {
		this.d_sommet_depart = d_sommet_depart;
	}

	public int getD_sommet_arrive() {
		return d_sommet_arrive;
	}

	public void setD_sommet_arrive(int d_sommet_arrive) {
		this.d_sommet_arrive = d_sommet_arrive;
	}

	public int getD_poids() {
		return d_poids;
	}

	public void setD_poids(int d_poids) {
		this.d_poids = d_poids;
	}
	
}
