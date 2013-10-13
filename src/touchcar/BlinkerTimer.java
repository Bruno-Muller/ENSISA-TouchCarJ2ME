package touchcar;

import java.util.Timer;
import car.Car;

public class BlinkerTimer {
	private Car model;
	private Controller controller;
	private Timer timer;


	BlinkerTimer(Car model, Controller controller) {
		this.model = model;
		this.controller = controller;
	}

	public void start() {
		if (this.timer != null)
			this.timer.cancel();
		this.timer = new Timer();
		this.timer.schedule(new BlinkerTask(this.model, this.controller), 500, 500);

	}

	public void stop() {
		if (this.timer != null)
			this.timer.cancel();
		this.timer = null;
	}

}
