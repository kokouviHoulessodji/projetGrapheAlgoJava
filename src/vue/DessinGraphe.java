package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JPanel;

import Graphe.Arete;
import Graphe.Sommet;

public class DessinGraphe extends JComponent {
	/**
	 * 
	 */
	private int nb_points;
	private Arete[] aretes;
	private static final long serialVersionUID = 1L;

	public DessinGraphe(int nb, Arete[] arete) {
		this.aretes = arete;
		this.nb_points = nb;
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		Random r = new Random();
		char[][] num = new char[nb_points+1][1];
		Sommet xy[] = new Sommet[nb_points+1];
		for(int i=1; i<=nb_points; i++) {
			num[i][0] = String.valueOf(i).charAt(0);
			int x = r.nextInt(600)+100;
			int y = r.nextInt(600)+100;
	        int j = 1;
	        while (j < i && Math.abs(xy[j].getD_x() - x) >= 50 && Math.abs(xy[j].getD_y() - y) >= 50)
	            j++;
	        if (j == i)
	        	xy[i] = new Sommet(x, y, i);
	        else
	            i--;
		}
		gr.setColor(Color.BLACK);
		for(int i=1; i<=nb_points; i++) {
			xy[i].draw(gr);
		}
		gr.setColor(Color.RED);
		for(int i=0; i<aretes.length; i++) {
			gr.drawChars(num[aretes[i].getD_sommet_depart().getD_numero()], 0, 1, xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y());
			gr.drawChars(num[aretes[i].getD_sommet_arrive().getD_numero()], 0, 1, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x(), xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y());
			gr.drawLine(xy[aretes[i].getD_sommet_depart().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_depart().getD_numero()].getD_y()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_x()+5, xy[aretes[i].getD_sommet_arrive().getD_numero()].getD_y()+5);
			
		}
	}
}
