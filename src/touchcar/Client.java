package touchcar;

import java.io.IOException;
import java.io.OutputStream;
import car.can.CanMessage;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class Client implements Runnable {

	public static final int DEFAULT_PORT = 50885;
	private Controller controller;
	private SocketConnection socket;
        private InputStream inputStream;
        private OutputStream outputStream;
	private boolean terminating;

	public Client(Controller controller, String ip, String port) throws IOException {
		this.controller = controller;
                this.socket = (SocketConnection) Connector.open("socket://" + ip + ":" + port);
                this.inputStream  = this.socket.openInputStream();
                this.outputStream = this.socket.openOutputStream();
		this.terminating = false;
	}

	public void run() {
		try {
			byte bytes[] = new byte[6];
			while (this.inputStream.read(bytes,0,6) != -1) {
				this.controller.acceptCanMessage(new CanMessage(bytes));
			}

		} catch (IOException e) {
			if (!this.terminating)
				e.printStackTrace();
		}
	}

	public void close() throws IOException {
		this.socket.close();
                this.inputStream.close();
                this.outputStream.close();
	}

	public void sendCanMessage(CanMessage canMessage) {
		try {
			
			this.outputStream.write(canMessage.getBytes());
			this.outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
