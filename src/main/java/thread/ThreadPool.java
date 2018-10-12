package thread;
import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
	private List<Thread> threadList = new ArrayList<Thread>();
	
	//空闲的线程列表
	private List<Worker> freeThreadList = new ArrayList<Worker>();
	
	public ThreadPool(){
		Thread ThreadPoolItem = new Worker();
	}
	
	/**
	 * 获取一个空闲线程
	 * @return
	 */
	public Worker getFreeThread(){
		
		if(freeThreadList.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  freeThreadList.get(0);
	}
	
}
