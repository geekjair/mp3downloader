package thread;

/**
 * 线程池的一个线程
 * @author admin
 *
 */
public class Worker extends Thread {
	ThreadPool threadPool = new ThreadPool();
	
	public void execute(Runnable runnable){
		
	}
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
	
}
