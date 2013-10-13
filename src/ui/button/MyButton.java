package ui.button;

import car.Car;
import com.sun.lwuit.Button;
import com.sun.lwuit.geom.Dimension;
import touchcar.Controller;

/**
 *
 * @author bruno
 */
public abstract class MyButton extends Button {

	protected Car model;
	protected Controller controller;

	protected MyButton(Car model, Controller controller) {
		this.model = model;
		this.controller = controller;
		super.setPreferredSize(new Dimension(80, 40));
	}
       
}
