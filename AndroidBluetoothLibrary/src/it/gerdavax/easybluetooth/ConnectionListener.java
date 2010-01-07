package it.gerdavax.easybluetooth;

import android.os.Handler;
import android.os.Message;

public abstract class ConnectionListener {
	private static final int CONNECTION_WAITING = 1;
	private static final int CONNECTION_ERROR = 2;
	
	private Handler delegate = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch(msg.what) {
			case CONNECTION_WAITING:
				ConnectionListener.this.connectionWaiting((BtSocket) msg.obj);
				break;
			case CONNECTION_ERROR:
				ConnectionListener.this.connectionError();
				break;
			}
		}
	};
	
	final void notifyConnectionError() {
		delegate.sendMessage(delegate.obtainMessage(CONNECTION_ERROR));
	}
	
	final void notifyConnectionWaiting(BtSocket tobounce) {
		delegate.sendMessage(delegate.obtainMessage(CONNECTION_WAITING, tobounce));
	}
	public abstract void connectionWaiting(BtSocket socket);
	public abstract void connectionError();
}
