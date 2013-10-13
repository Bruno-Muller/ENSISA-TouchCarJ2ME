package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import ui.Color;

public class WarningButton extends MyButton {


	private static int xPoints[] = {0, 15, 30};
	private static int yPoints[] = {25, 0, 25};

	public WarningButton(Car model, Controller controller) {
		super(model, controller);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics g = graphics;
                g.translate(super.getX()+super.getWidth()/2-15, super.getY()+super.getHeight()/2-15);
		if ((super.model.getLight(OpticalBlock.FRONT_LEFT, Light.BLINKER).isOn() || super.model.getLight(OpticalBlock.REAR_LEFT, Light.BLINKER).isOn()) &&
				(super.model.getLight(OpticalBlock.FRONT_RIGHT, Light.BLINKER).isOn() || super.model.getLight(OpticalBlock.REAR_RIGHT, Light.BLINKER).isOn()))
			this.paintWarning(g, Color.RED, Color.ORANGE);
		else
			this.paintWarning(g, null, Color.RED);
                g.translate(-super.getX()-super.getWidth()/2+15, -super.getY()-super.getHeight()/2+15);

	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		this.controller.toggleWarning();
	}

	private void paintWarning(Graphics g, Color fill, Color border) {
		if (fill != null) {
			g.setColor(fill.value());
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}
		g.setColor(border.value());
		g.drawPolygon(xPoints, yPoints, xPoints.length);

		g.drawLine(15, 7, 15, 17);
		g.drawLine(15, 20, 15, 20);
	}

}