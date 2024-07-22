package backend;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
public class MusicplayerRunnable implements Runnable{
	private Player player;
	private Boolean isStop = false;
	//lock sychnonize with song playing thread and continue or stop it with wait
	private final Object lock = new Object();
	public MusicplayerRunnable(Player player) {
		this.player = player;
	}
	@Override
	public void run() {
			try {
				while(player.play(1)) {
					if(isStop) {
				        synchronized (lock) {
			                lock.wait(); // Wait method
			                isStop = false;
				        }
					}
				}
			} 
			catch (JavaLayerException | InterruptedException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	public void stopPlayer() {
		isStop = true;
	}
	public void continuePlayer() {
        synchronized (lock) {
            lock.notify(); // Notify method
        }
	}
}
