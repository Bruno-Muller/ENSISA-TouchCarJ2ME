package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import ui.Color;

public class LowBeamPositionLightButton extends MyButton {

	public LowBeamPositionLightButton(Car model, Controller controller) {
		super(model, controller);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics g = graphics;
                g.translate(super.getX()+super.getWidth()/2-33, super.getY()+super.getHeight()/2-10);
		if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.POSITION_LIGHT).isOn() ||
				this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.POSITION_LIGHT).isOn() ||
				this.model.getLight(OpticalBlock.REAR_LEFT, Light.POSITION_LIGHT).isOn() ||
				this.model.getLight(OpticalBlock.REAR_RIGHT, Light.POSITION_LIGHT).isOn())
			this.paintPositionLight(g, Color.GREEN, Color.GREEN);
		else
			this.paintPositionLight(g, null, Color.GREEN);

		if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.LOWBEAM).isOn() ||
				this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.LOWBEAM).isOn())
			this.paintLowBeam(g, Color.GREEN, Color.GREEN);
		else
			this.paintLowBeam(g, null, Color.GREEN);
                g.translate(-super.getX()-super.getWidth()/2+33, -super.getY()-super.getHeight()/2+10);

	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		super.controller.toggleLowBeamPositionLight();
	}

	private void paintLowBeam(Graphics g, Color fill, Color border) {
		if (fill != null) {
			g.setColor(fill.value());
			g.fillArc(47, 0, 5, 20, 90, 180);
			g.fillRect(47, 0, 10, 20);
			g.fillArc(47, 0, 20, 20, -90, 180);
		}

		g.setColor(border.value());
		g.drawLine(30, 3, 45, 0);
		g.drawLine(30, 8, 43, 5);
		g.drawLine(30, 13, 42, 10);
		g.drawLine(30, 18, 43, 15);
		g.drawLine(30, 23, 45, 20);

		g.drawArc(47, 0, 5, 20, 90, 180);
		g.drawLine(47, 0, 60, 0);
		g.drawLine(47, 20, 60, 20);
		g.drawArc(47, 0, 20, 20, -90, 180);
	}

	private void paintPositionLight(Graphics g, Color fill, Color border) {
		if (fill != null) {
			g.setColor(fill.value());
			g.fillArc(5, 2, 15, 15, 0, 360);
			g.fillRect(10, 0, 5, 3);
		}

		g.setColor(border.value());
		g.drawArc(5, 2, 15, 15, 0, 360);
		int xPoints[] = {10, 10, 15, 15};
		int yPoints[] = {2, 0, 0, 2};
		g.drawPolygon(xPoints, yPoints, xPoints.length);

		g.drawLine(3, 1, 5, 3);
		g.drawLine(0, 10, 2, 10);
		g.drawLine(3, 19, 5, 17);
		g.drawLine(22, 10, 25, 10);
		g.drawLine(21, 19, 19, 17);
		g.drawLine(12, 20, 12, 22);
		g.drawLine(21, 1, 19, 3);
	}

}
