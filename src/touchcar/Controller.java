package touchcar;

import java.io.IOException;
import car.Car;
import car.can.CanMessage;
import car.light.Light;
import car.opticalblock.OpticalBlock;
import com.sun.lwuit.Form;

public class Controller {

	public abstract class BlinkerState {
		public static final int OFF = 0;
		public static final int LEFT = 1;
		public static final int RIGHT = 2;
		public static final int BOTH = 3;
	}

	private Client client;
	private Car model;
	private Form view;
	private BlinkerTimer timer;

	public Controller(Car model, Form view, String ip, String port) throws IOException {
		this.model = model;
		this.view = view;
		this.client = new Client(this, ip, port);
		this.timer = new BlinkerTimer(this.model, this);
		new Thread(client).start();
	}

        public void setView(Form view) {
            this.view = view;
        }

        public Form getView() {
            return this.view;
        }

	private int getBlinkerState() {
		if (((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.BLINKER) == CanMessage.LIGHT_BLINKING) ||
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.BLINKER) == CanMessage.LIGHT_BLINKING)) &&
				((this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.BLINKER) == CanMessage.LIGHT_BLINKING) ||
						(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.BLINKER) == CanMessage.LIGHT_BLINKING)))
			return BlinkerState.BOTH;
		if (((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.BLINKER) == CanMessage.LIGHT_BLINKING) ||
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.BLINKER) == CanMessage.LIGHT_BLINKING)) &&
				(this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.BLINKER) != CanMessage.LIGHT_BLINKING) &&
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.BLINKER) != CanMessage.LIGHT_BLINKING))
			return BlinkerState.LEFT;
		if (((this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.BLINKER) == CanMessage.LIGHT_BLINKING) ||
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.BLINKER) == CanMessage.LIGHT_BLINKING)) &&
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.BLINKER) != CanMessage.LIGHT_BLINKING) &&
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.BLINKER) != CanMessage.LIGHT_BLINKING))
			return BlinkerState.RIGHT;
		return BlinkerState.OFF;
	}

	public void acceptCanMessage(CanMessage canMessage) {
		this.model.acceptCanMessage(canMessage);

		if (canMessage.getLightState(Light.BLINKER) == CanMessage.LIGHT_BLINKING)
			this.timer.start();
		else if (canMessage.getLightState(Light.BLINKER) == CanMessage.LIGHT_OFF)
			this.timer.stop();

		this.view.repaint();
	}

	public void toggleLeftBlinker() {
		int blinkerState = this.getBlinkerState();
		this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0));
		if (blinkerState != BlinkerState.LEFT)
			this.client.sendCanMessage(new CanMessage(CanMessage.LEFT, CanMessage.CLIGNO_ON, 0));
	}

	public void toggleRightBlinker() {
		int blinkerState = this.getBlinkerState();
		this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0));
		if (blinkerState != BlinkerState.RIGHT)
			this.client.sendCanMessage(new CanMessage(CanMessage.RIGHT, CanMessage.CLIGNO_ON, 0));
	}

	public void toggleWarning() {
		int blinkerState = this.getBlinkerState();
		this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_OFF, 0));
		if (blinkerState != BlinkerState.BOTH)
			this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.CLIGNO_ON, 0));
	}

	public void toggleStopLight() {
		if ((this.model.getLightState(OpticalBlock.REAR_LEFT, Light.STOP_LIGHT) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.STOP_LIGHT) == CanMessage.LIGHT_ON))
			this.client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.STOP_OFF, 0));
		else
			this.client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.STOP_ON, 0));
	}

	public void toggleBackLight() {
		if ((this.model.getLightState(OpticalBlock.REAR_LEFT, Light.BACK_LIGHT) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.BACK_LIGHT) == CanMessage.LIGHT_ON))
			this.client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.RECULE_OFF, 0));
		else
			this.client.sendCanMessage(new CanMessage(CanMessage.REAR, CanMessage.RECULE_ON, 0));

	}

	public void toggleMainBeam() {
		if ((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.MAINBEAM) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.MAINBEAM) == CanMessage.LIGHT_ON))
			this.client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.PHARE_OFF, 0));
		else
			this.client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.PHARE_ON, 0));
	}

	public void toggleLowBeamPositionLight() {
		// OFF -> Position
		if ((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.POSITION_LIGHT) == CanMessage.LIGHT_OFF) ||
				(this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.POSITION_LIGHT) == CanMessage.LIGHT_OFF) ||
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.POSITION_LIGHT) == CanMessage.LIGHT_OFF) ||
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.POSITION_LIGHT) == CanMessage.LIGHT_OFF))
			this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_ON, 0));


		// Position LowBeam -> OFF
		else if ((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.LOWBEAM) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.LOWBEAM) == CanMessage.LIGHT_ON)) {
			this.client.sendCanMessage(new CanMessage(CanMessage.ALL, CanMessage.POS_OFF, 0));
			this.client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.CROIS_OFF, 0));
		}
		// Position -> Position LowBeam
		else if ((this.model.getLightState(OpticalBlock.FRONT_LEFT, Light.POSITION_LIGHT) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.FRONT_RIGHT, Light.POSITION_LIGHT) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.REAR_LEFT, Light.POSITION_LIGHT) == CanMessage.LIGHT_ON) ||
				(this.model.getLightState(OpticalBlock.REAR_RIGHT, Light.POSITION_LIGHT) == CanMessage.LIGHT_ON))
			this.client.sendCanMessage(new CanMessage(CanMessage.FRONT, CanMessage.CROIS_ON, 0));
	}
}
