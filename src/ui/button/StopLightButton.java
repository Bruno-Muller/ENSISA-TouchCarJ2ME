package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import ui.Color;

public class StopLightButton extends TextButton {

	public StopLightButton(Car model, Controller controller) {
		super(model, controller, "StopLight", Color.RED);
	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		super.controller.toggleStopLight();
	}

	public void paint(Graphics graphics) {
		super.paint(graphics, super.model.getLight(OpticalBlock.REAR_LEFT, Light.STOP_LIGHT).isOn() || super.model.getLight(OpticalBlock.REAR_RIGHT, Light.STOP_LIGHT).isOn());
	}

}
