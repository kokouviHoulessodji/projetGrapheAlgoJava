package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DessinGraphe extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		gr.setColor(Color.white);
		gr.fillRect(0,  0, getWidth(), getHeight());
		gr.setColor(Color.red);
		gr.drawLine(0, 0, getWidth()/2, getHeight()/2);
		gr.fillOval(getWidth()/2, getHeight()/2, 40, 40);
		char[] data = new char[1];
		data[0] = '1';
		gr.drawChars(data, 0, 1, 40, 40);;
	}
}
