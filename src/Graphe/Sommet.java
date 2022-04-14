package Graphe;

import java.awt.Graphics2D;

public class Sommet {
	private int d_x, d_y;
	private int d_numero;
	
	public Sommet(int x, int y, int numero){
		d_x = x;
		d_y = y; 
		d_numero = numero;
	}
	
	public Sommet(){
		this(0);
	}

	public Sommet(int numero){
		this(0,0, numero);
	}

	

	private Sommet cartesian(int x, int y){
	    return new Sommet(x, y, 0);
	}

	

	public int getD_x()
	{
	    return d_x;
	}

	public int getD_y()
	{
	    return d_y;
	}

	public int getD_numero()
	{
	    return d_numero;
	}
	public void setD_numero(int numero)
	{
	    d_numero = numero;
	}
	private double distance(Sommet p)
	{
	    double dx = p.d_x-d_x, dy = p.d_y-d_y;
	    return Math.sqrt(dx*dx + dy*dy);
	}

	private void moveTo(int x, int y)
	{
	    d_x = x;
	    d_y = y;
	}

	private void move(int dx, int dy)
	{
	    d_x += dx;
	    d_y += dy;
	}

	private void print()
	{
	    System.out.print("("+d_x+", "+d_y+")");
	}
	public void draw(Graphics2D gr) {
		gr.fillOval(getD_x(), getD_y(), 20, 20);
	}
	
}
