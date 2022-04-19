package Graphe;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Objects;

public class Arete {
		protected Sommet d_sommet_depart, d_sommet_arrive;
		protected int d_poids;

	public Arete(Sommet sommet_depart, Sommet sommet_arrive, int poids) {
		this.d_poids = poids;
		this.d_sommet_arrive = sommet_arrive;
		this.d_sommet_depart = sommet_depart;
	}
	public Arete(Sommet sommet_depart, Sommet sommet_arrive) {
		this(sommet_depart, sommet_arrive, 0);
	}
	public Sommet getD_sommet_depart() {
		return d_sommet_depart;
	}

	public void setD_sommet_depart(Sommet d_sommet_depart) {
		this.d_sommet_depart = d_sommet_depart;
	}

	public Sommet getD_sommet_arrive() {
		return d_sommet_arrive;
	}

	public void setD_sommet_arrive(Sommet d_sommet_arrive) {
		this.d_sommet_arrive = d_sommet_arrive;
	}

	public int getD_poids() {
		return d_poids;
	}

	public void setD_poids(int d_poids) {
		this.d_poids = d_poids;
	}
	public void draw(Graphics2D gr, Color couleur) {
		gr.setColor(couleur);
		gr.drawLine(getD_sommet_depart().getD_x()+5, getD_sommet_depart().getD_y()+5, getD_sommet_arrive().getD_x()+5, getD_sommet_arrive().getD_y()+5);
	}
	@Override
	public int hashCode() {
		return Objects.hash(d_poids, d_sommet_arrive, d_sommet_depart);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arete other = (Arete) obj;
		return d_poids == other.d_poids && this.d_sommet_depart.equals(other.d_sommet_depart)
				&& this.d_sommet_arrive.equals(other.d_sommet_arrive);
	}
	
}
