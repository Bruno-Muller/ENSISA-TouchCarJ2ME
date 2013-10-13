package ui;

import car.Car;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.layouts.GridLayout;
import java.io.IOException;
import touchcar.Controller;
import ui.button.*;

/**
 *
 * @author bruno
 */
public class TouchCar extends Form {

    private Car model;
    private Controller controller;

    public TouchCar(Car model, Controller controller) {
        super("TouchCar");
        
        this.model = model;
        this.controller = controller;

        super.addComponent(new LeftBlinkerButton(this.model, this.controller));
        super.addComponent(new RightBlinkerButton(this.model, this.controller));
        super.addComponent(new WarningButton(this.model, this.controller));
        super.addComponent(new Label(""));
        super.addComponent(new MainBeamButton(this.model, this.controller));
        super.addComponent(new LowBeamPositionLightButton(this.model, this.controller));
        super.addComponent(new BackLightButton(this.model, this.controller));
        super.addComponent(new StopLightButton(this.model, this.controller));
        super.setLayout(new GridLayout(4,2));


    }

}
