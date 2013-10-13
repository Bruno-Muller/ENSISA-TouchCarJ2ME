package ui.button;

import car.Car;

import com.sun.lwuit.Graphics;
import touchcar.Controller;
import ui.Color;

public abstract class BlinkerButton extends MyButton {

	private int xPoints[];
	private int yPoints[];

	public BlinkerButton(Car model, Controller controller, int[] xPoints, int[] yPoints) {
		super(model, controller);
		this.xPoints = xPoints;
		this.yPoints = yPoints;
	}

	public void paint(Graphics graphics, boolean on) {
		super.paint(graphics);
                Graphics g = graphics;
                g.translate(super.getX()+super.getWidth()/2-22, super.getY()+super.getHeight()/2-10);

		if (on)
			this.paintBlinker(g, Color.GREEN, Color.GREEN);
		else
			this.paintBlinker(g, null, Color.GREEN);
                g.translate(-super.getX()-super.getWidth()/2+22, -super.getY()-super.getHeight()/2+10);
	}
	
	private void paintBlinker(Graphics g, Color fill, Color border) {
		if (fill != null) {
			g.setColor(fill.value());
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}
		g.setColor(border.value());
		g.drawPolygon(xPoints, yPoints, xPoints.length);            
	}

}
