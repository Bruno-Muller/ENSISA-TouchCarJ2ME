package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;

public class RightBlinkerButton extends BlinkerButton {

	private static int xPoints[] = {0, 25, 25, 45, 25, 25, 0};
	private static int yPoints[] = {5, 5, 0, 10, 20, 15, 15};

	public RightBlinkerButton(Car model, Controller controller) {
		super(model, controller, xPoints, yPoints);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics, this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.BLINKER).isOn() || this.model.getLight(OpticalBlock.REAR_RIGHT, Light.BLINKER).isOn());
	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		super.controller.toggleRightBlinker();
	}

}
