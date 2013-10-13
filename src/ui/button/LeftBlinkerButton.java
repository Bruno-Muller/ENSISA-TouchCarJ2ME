package ui.button;

import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;

import com.sun.lwuit.Graphics;
import touchcar.Controller;

public class LeftBlinkerButton extends BlinkerButton {

	private static int xPoints[] = {0, 20, 20, 45, 45, 20, 20};
	private static int yPoints[] = {10, 0, 5, 5, 15, 15, 20};

	public LeftBlinkerButton(Car model, Controller controller) {
		super(model, controller, xPoints, yPoints);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics, this.model.getLight(OpticalBlock.FRONT_LEFT, Light.BLINKER).isOn() || this.model.getLight(OpticalBlock.REAR_LEFT, Light.BLINKER).isOn());
	}

        public void pointerPressed(int x, int y) {
            super.pointerPressed(x, y);
            super.controller.toggleLeftBlinker();
        }
}