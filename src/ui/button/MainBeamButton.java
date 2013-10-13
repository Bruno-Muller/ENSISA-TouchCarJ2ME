package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import ui.Color;

public class MainBeamButton extends MyButton {

	public MainBeamButton(Car model, Controller controller) {
		super(model, controller);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics g = graphics;
                g.translate(super.getX()+super.getWidth()/2-20, super.getY()+super.getHeight()/2-10);
		if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.MAINBEAM).isOn() ||
				this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.MAINBEAM).isOn())
			this.paintMainBeam(g, Color.CYAN, Color.CYAN);
		else
			this.paintMainBeam(g, null, Color.CYAN);
                g.translate(-super.getX()-super.getWidth()/2+20, -super.getY()-super.getHeight()/2+10);

	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		super.controller.toggleMainBeam();
	}

	public void pointerReleased(int x, int y) {
		super.pointerReleased(x, y);
		if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.LOWBEAM).isOff() &&
				this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.LOWBEAM).isOff())
			super.controller.toggleMainBeam();
	}

	private void paintMainBeam(Graphics g, Color fill, Color border) {
		if (fill != null) {
			g.setColor(fill.value());
			g.fillArc(17, 0, 5, 20, 90, 180);
			g.fillRect(20, 0, 10, 20);
			g.fillArc(20, 0, 20, 20, -90, 180);
		}

		g.setColor(border.value());
		g.drawLine(0, 0, 15, 0);
		g.drawLine(0, 5, 13, 5);
		g.drawLine(0, 10, 12, 10);
		g.drawLine(0, 15, 13, 15);
		g.drawLine(0, 20, 15, 20);

		g.drawArc(17, 0, 5, 20, 90, 180);
		g.drawLine(20, 0, 30, 0);
		g.drawLine(20, 20, 30, 20);
		g.drawArc(20, 0, 20, 20, -90, 180);
	}

}
