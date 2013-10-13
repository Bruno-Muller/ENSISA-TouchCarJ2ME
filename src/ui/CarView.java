package ui;

import car.Car;
import car.can.CanMessage;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Graphics;
import com.sun.lwuit.Label;
import com.sun.lwuit.geom.Dimension;

public class CarView extends Label {

	private Car model;

	public CarView(Car model) {
		super.setPreferredSize(new Dimension(160,280));
		this.model = model;
	}

	public void paint(Graphics graphics) {
                super.paint(graphics);
		Graphics g = graphics;

                g.translate(super.getX()+super.getWidth()/2-80, super.getY());

		this.paintFrontLeftOpticalBlock(g);
		this.paintFrontRightOpticalBlock(g);
		this.paintRearLeftOpticalBlock(g);
		this.paintRearRightOpticalBlock(g);
		this.paintCar(g);

                g.translate(-super.getX()-super.getWidth()/2+80, -super.getY());
	}

	private void paintCar(Graphics g) {

		g.setColor(Color.BLACK.value());

		// Contour de la voiture
		int xPoints1[] = {10, 20, 140, 150, 150,140, 20, 10};
		int yPoints1[] = {20, 10, 10, 20, 260, 270, 270, 260};
		g.drawPolygon(xPoints1, yPoints1, xPoints1.length);

		// Toit
		g.drawRect(40, 110, 80, 100);

		// Parre brise avant
		int xPoints2[] = {40, 10, 150, 120};
		int yPoints2[] = {110, 70, 70, 110};
		g.drawPolygon(xPoints2, yPoints2, xPoints2.length);

		// Parre brise arrière
		g.drawLine(10, 240, 40, 210);
		g.drawLine(150, 240, 120, 210);

		// Roue avant gauche
		int xPoints3[] = {10, 5, 5, 10};
		int yPoints3[] = {80, 80, 40, 40};
		g.drawPolygon(xPoints3, yPoints3, xPoints3.length);

		// Roue avant droite
		int xPoints4[] = {150, 155, 155, 150};
		int yPoints4[] = {80, 80, 40, 40};
		g.drawPolygon(xPoints4, yPoints4, xPoints4.length);

		// Roue arrière gauche
		int xPoints5[] = {10, 5, 5, 10};
		int yPoints5[] = {240, 240, 200, 200};
		g.drawPolygon(xPoints5, yPoints5, xPoints5.length);

		// Roue arrière droite
		int xPoints6[] = {150, 155, 155, 150};
		int yPoints6[] = {240, 240, 200, 200};
		g.drawPolygon(xPoints6, yPoints6, xPoints6.length);
	}

	private void paintFrontLeftOpticalBlock(Graphics g) {

		// Blinker
		if (this.model.getError(OpticalBlock.FRONT_LEFT, CanMessage.ERROR_BLINKER)) {
			g.setColor(Color.RED.value());
			g.drawLine(10, 30, 20, 10);
			g.drawLine(15, 15, 20, 30);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.BLINKER).isOn()) {
			g.setColor(Color.ORANGE.value());
			int xPoints[] = {10, 20, 20, 10};
			int yPoints[] = {20, 10, 30, 30};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}

		// Position
		if (this.model.getError(OpticalBlock.FRONT_LEFT, CanMessage.ERROR_POSITION_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(20, 10, 30, 20);
			g.drawLine(20, 20, 30, 10);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.POSITION_LIGHT).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(20, 10, 10, 10);
		}

		// LowBeam
		if (this.model.getError(OpticalBlock.FRONT_LEFT, CanMessage.ERROR_LOWBEAM)) {
			g.setColor(Color.RED.value());
			g.drawLine(30, 10, 50, 20);
			g.drawLine(30, 20, 50, 10);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.LOWBEAM).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(30, 10, 20, 10);
		}

		// MainBean
		if (this.model.getError(OpticalBlock.FRONT_LEFT, CanMessage.ERROR_MAINBEAM)) {
			g.setColor(Color.RED.value());
			g.drawLine(20, 20, 50, 30);
			g.drawLine(20, 30, 50, 20);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_LEFT, Light.MAINBEAM).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(20, 20, 30, 10);
		}

		// Optical block
		if (this.model.getError(OpticalBlock.FRONT_LEFT, CanMessage.ERROR_FUSE))
			g.setColor(Color.RED.value());
		else
			g.setColor(Color.DARK_GRAY.value());
		g.drawLine(10, 30, 50, 30);
		g.drawLine(50, 30, 50, 10);
		g.drawLine(20, 30, 20, 10);
		g.drawLine(20, 20, 50, 20);
		g.drawLine(30, 20, 30, 10);
	}

	private void paintFrontRightOpticalBlock(Graphics g) {

		// Blinker
		if (this.model.getError(OpticalBlock.FRONT_RIGHT, CanMessage.ERROR_BLINKER)) {
			g.setColor(Color.RED.value());
			g.drawLine(150, 30, 140, 10);
			g.drawLine(145, 15, 140, 30);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.BLINKER).isOn()) {
			g.setColor(Color.ORANGE.value());
			int xPoints[] = {150, 140, 140, 150};
			int yPoints[] = {20, 10, 30, 30};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}

		// Position
		if (this.model.getError(OpticalBlock.FRONT_RIGHT, CanMessage.ERROR_POSITION_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(140, 10, 130, 20);
			g.drawLine(140, 20, 130, 10);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.POSITION_LIGHT).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(130, 10, 10, 10);
		}

		// LowBeam
		if (this.model.getError(OpticalBlock.FRONT_RIGHT, CanMessage.ERROR_LOWBEAM)) {
			g.setColor(Color.RED.value());
			g.drawLine(130, 10, 110, 20);
			g.drawLine(130, 20, 110, 10);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.LOWBEAM).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(110, 10, 20, 10);
		}

		// MainBeam
		if (this.model.getError(OpticalBlock.FRONT_RIGHT, CanMessage.ERROR_MAINBEAM)) {
			g.setColor(Color.RED.value());
			g.drawLine(140, 20, 110, 30);
			g.drawLine(140, 30, 110, 20);
		}
		else if (this.model.getLight(OpticalBlock.FRONT_RIGHT, Light.MAINBEAM).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(110, 20, 30, 10);
		}

		// Optical block
		if (this.model.getError(OpticalBlock.FRONT_RIGHT, CanMessage.ERROR_FUSE))
			g.setColor(Color.RED.value());
		else
			g.setColor(Color.DARK_GRAY.value());
		g.drawLine(150, 30, 110, 30);
		g.drawLine(110, 30, 110, 10);
		g.drawLine(140, 30, 140, 10);
		g.drawLine(140, 20, 110, 20);
		g.drawLine(130, 20, 130, 10);
	}

	private void paintRearLeftOpticalBlock(Graphics g) {

		// Blinker error
		if (this.model.getError(OpticalBlock.REAR_LEFT, CanMessage.ERROR_BLINKER)) {
			g.setColor(Color.RED.value());
			g.drawLine(10, 250, 20, 270);
			g.drawLine(15, 265, 20, 250);
		}
		else if (this.model.getLight(OpticalBlock.REAR_LEFT, Light.BLINKER).isOn()) {
			g.setColor(Color.ORANGE.value());
			int xPoints[] = {10, 20, 20, 10};
			int yPoints[] = {260, 270, 250, 250};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}

		// Position
		if (this.model.getError(OpticalBlock.REAR_LEFT, CanMessage.ERROR_POSITION_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(20, 270, 30, 260);
			g.drawLine(20, 260, 30, 270);
		}
		else if (this.model.getLight(OpticalBlock.REAR_LEFT, Light.POSITION_LIGHT).isOn()) {
			g.setColor(Color.RED.value());
			g.fillRect(20, 260, 10, 10);
		}

		// Backlight
		if (this.model.getError(OpticalBlock.REAR_LEFT, CanMessage.ERROR_BACK_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(30, 270, 50, 260);
			g.drawLine(30, 260, 50, 270);
		}
		else if (this.model.getLight(OpticalBlock.REAR_LEFT, Light.BACK_LIGHT).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(30, 260, 20, 10);
		}

		// Stoplight
		if (this.model.getError(OpticalBlock.REAR_LEFT, CanMessage.ERROR_STOP_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(20, 260, 50, 250);
			g.drawLine(20, 250, 50, 260);
		}
		else if (this.model.getLight(OpticalBlock.REAR_LEFT, Light.STOP_LIGHT).isOn()) {
			g.setColor(Color.RED.value());
			g.fillRect(20, 250, 30, 10);
		}

		// Optical block
		if (this.model.getError(OpticalBlock.REAR_LEFT, CanMessage.ERROR_FUSE))
			g.setColor(Color.RED.value());
		else
			g.setColor(Color.DARK_GRAY.value());
		g.drawLine(10, 250, 50, 250);
		g.drawLine(50, 250, 50, 270);
		g.drawLine(20, 250, 20, 270);
		g.drawLine(20, 260, 50, 260);
		g.drawLine(30, 260, 30, 270);
	}

	private void paintRearRightOpticalBlock(Graphics g) {

		// Blinker
		if (this.model.getError(OpticalBlock.REAR_RIGHT, CanMessage.ERROR_BLINKER)) {
			g.setColor(Color.RED.value());
			g.drawLine(150, 250, 140, 270);
			g.drawLine(145, 265, 140, 250);
		}
		else if (this.model.getLight(OpticalBlock.REAR_RIGHT, Light.BLINKER).isOn()) {
			g.setColor(Color.ORANGE.value());
			int xPoints[] = {150, 140, 140, 150};
			int yPoints[] = {260, 270, 250, 250};
			g.fillPolygon(xPoints, yPoints, xPoints.length);
		}

		// Position
		if (this.model.getError(OpticalBlock.REAR_RIGHT, CanMessage.ERROR_POSITION_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(140, 270, 130, 260);
			g.drawLine(140, 260, 130, 270);
		}
		else if (this.model.getLight(OpticalBlock.REAR_RIGHT, Light.POSITION_LIGHT).isOn()) {
			g.setColor(Color.RED.value());
			g.fillRect(130, 260, 10, 10);
		}

		// Backlight
		if (this.model.getError(OpticalBlock.REAR_RIGHT, CanMessage.ERROR_BACK_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(130, 270, 110, 260);
			g.drawLine(130, 260, 110, 270);
		}
		else if (this.model.getLight(OpticalBlock.REAR_RIGHT, Light.BACK_LIGHT).isOn()) {
			g.setColor(Color.YELLOW.value());
			g.fillRect(110, 260, 20, 10);
		}

		// Stoplight
		if (this.model.getError(OpticalBlock.REAR_RIGHT, CanMessage.ERROR_STOP_LIGHT)) {
			g.setColor(Color.RED.value());
			g.drawLine(140, 260, 110, 250);
			g.drawLine(140, 250, 110, 260);
		}
		else if (this.model.getLight(OpticalBlock.REAR_RIGHT, Light.STOP_LIGHT).isOn()) {
			g.setColor(Color.RED.value());
			g.fillRect(110, 250, 30, 10);
		}

		// Optical block
		if (this.model.getError(OpticalBlock.REAR_RIGHT, CanMessage.ERROR_FUSE))
			g.setColor(Color.RED.value());
		else
			g.setColor(Color.DARK_GRAY.value());
		g.drawLine(150, 250, 110, 250);
		g.drawLine(110, 250, 110, 270);
		g.drawLine(140, 250, 140, 270);
		g.drawLine(140, 260, 110, 260);
		g.drawLine(130, 260, 130, 270);
	}

}
