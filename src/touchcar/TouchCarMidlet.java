package touchcar;

import car.Car;
import com.sun.lwuit.Command;
import com.sun.lwuit.Container;
import com.sun.lwuit.Display;
import com.sun.lwuit.Form;
import com.sun.lwuit.Label;
import com.sun.lwuit.TextField;
import com.sun.lwuit.events.ActionEvent;
import com.sun.lwuit.events.ActionListener;
import com.sun.lwuit.layouts.BoxLayout;
import com.sun.lwuit.layouts.GridLayout;
import com.sun.lwuit.plaf.UIManager;
import com.sun.lwuit.util.Resources;
import java.io.IOException;
import javax.microedition.midlet.*;
import ui.CarView;
import ui.TouchCar;


/**
 * @author bruno
 */
public class TouchCarMidlet extends MIDlet implements ActionListener {

    private Form controlView, carView;
    private Car model;
    private Controller controller;
    private TextField ip, port;

    public void startApp() {
        Display.init(this);

        try {
            UIManager.getInstance().addThemeProps(Resources.open("/LWUITtheme.res").getTheme("LWUITDefault"));

            Form f = new Form("TouchCar");

            Container l1 = new Container();
            l1.addComponent(new Label("IP :"));
            l1.addComponent(this.ip = new TextField("192.168.125.10"));
            l1.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            f.addComponent(l1);
            
            Container l2 = new Container();
            l2.addComponent(new Label("Port :"));
            l2.addComponent(this.port = new TextField(String.valueOf(Client.DEFAULT_PORT)));
            l2.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            f.addComponent(l2);

            f.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            f.addCommand(new Command("Exit"));
            f.addCommand(new Command("Ok"));
            f.addCommandListener(this);
            f.show();

        } catch (IOException ex) {
            Form f = new Form("TouchCar");
            f.addComponent(new Label(ex.getMessage()));
            f.addCommand(new Command("Exit"));
            f.addCommandListener(this);
            f.show();
        }
    }

    public void startTouchCar() {
        try {
            this.model = new Car();
            

            this.carView = new Form("TouchCar");
            this.carView.addComponent(new CarView(this.model));
            this.carView.addCommand(new Command("Exit"));
            this.carView.addCommand(new Command("ControlView"));
            this.carView.setLayout(new GridLayout(1,1));
            this.carView.addCommandListener(this);

            this.controlView = new TouchCar(this.model, this.controller);

            this.controlView.addCommand(new Command("Exit"));
            this.controlView.addCommand(new Command("CarView"));
            this.controlView.addCommandListener(this);

            this.controller = new Controller(this.model, this.controlView, this.ip.getText(), this.port.getText());
            this.controlView.show();

        } catch (IOException ex) {
            Form f = new Form("TouchCar");
            f.addComponent(new Label(ex.getMessage()));
            f.addCommand(new Command("Exit"));
            f.addCommandListener(this);
            f.show();
        }

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getCommand().getCommandName().equals("Exit"))
            notifyDestroyed();
        if (ae.getCommand().getCommandName().equals("CarView")) {
            this.carView.show();
            this.controller.setView(this.carView);
            }
        if (ae.getCommand().getCommandName().equals("ControlView")) {
            this.controlView.show();
            this.controller.setView(this.controlView);
        }
        if (ae.getCommand().getCommandName().equals("Ok")) {
            this.startTouchCar();
        }
    }
}
