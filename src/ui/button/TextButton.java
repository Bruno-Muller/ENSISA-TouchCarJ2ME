package ui.button;

import car.Car;

import com.sun.lwuit.Font;
import com.sun.lwuit.Graphics;
import touchcar.Controller;
import ui.Color;

public abstract class TextButton extends MyButton {

	private String text;
	private Color activeColor;

	public TextButton(Car model, Controller controller, String text, Color activeColor) {
		super(model, controller);
		this.text = text;
		this.activeColor = activeColor;
	}

	public void paint(Graphics graphics, boolean on) {
		super.paint(graphics);
		Graphics g = graphics;
                g.translate(super.getX(), super.getY());
		if (on)
			this.paintText(g, this.activeColor);
		else
			this.paintText(g, Color.BLACK);
                g.translate(-super.getX(), -super.getY());
	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
	}

	protected void paintText(Graphics g, Color color) {
		g.setColor(color.value());
                g.setFont(Font.getDefaultFont());
		g.drawString(this.text, super.getWidth()/2-g.getFont().stringWidth(this.text)/2, super.getHeight()/2-g.getFont().getHeight()/2);
	}
}