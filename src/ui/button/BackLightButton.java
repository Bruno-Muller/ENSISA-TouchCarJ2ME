package ui.button;

import touchcar.Controller;
import car.Car;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import ui.Color;

public class BackLightButton extends TextButton {

	public BackLightButton(Car model, Controller controller) {
		super(model, controller, "BackLight", Color.ORANGE);
	}

	public void pointerPressed(int x, int y) {
		super.pointerPressed(x, y);
		super.controller.toggleBackLight();
	}

	public void paint(Graphics graphics) {
		super.paint(graphics, super.model.getLight(OpticalBlock.REAR_LEFT, Light.BACK_LIGHT).isOn() || super.model.getLight(OpticalBlock.REAR_RIGHT, Light.BACK_LIGHT).isOn());
	}

}
