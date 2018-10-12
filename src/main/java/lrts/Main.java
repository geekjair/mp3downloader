package lrts;

import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.impl.conn.tsccm.WaitingThread;

import com.alibaba.fastjson.JSONArray;
import common.Downloader;
import common.FileDownloader;

/**
 * 懒人听书 
 * @author admin
 *
 */
public class Main {
	public final static String path = "dpcq";
	public static
	void main(String[] args) throws URISyntaxException {
		//线程池
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		//循环
		for (int i = 25; i <= 34; i++) {
			//获取列表
			String url = new LrtsURLBuilder().getURI()
			.addParameter("bookId", "5622")
			.addParameter("pageNum", String.valueOf(i))
			.addParameter("pageSize", "50")
			.addParameter("sortType", "0")
			.setHost("dapis.mting.info")
			.setPath("/yyting/bookclient/ClientGetBookResource.action").toString();
			url = ParamsVerify.convertUrlWIthAuth(url);
			String body = new Downloader().getBodyStr(url);
			Map<String, String> resultMap = new BodyParse().getBookResource(body);
			System.out.println(resultMap);
			for (String section : resultMap.keySet()) {
				final String section2 = section;
				final String sectionName = resultMap.get(section2);
				System.out.println("递交任务:"+sectionName);
				pool.execute(new Runnable() {
					
					public void run() {
						//暂停3秒
						try {
							Thread.sleep(300 * Thread.currentThread().getId());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JSONArray sections = new JSONArray();
						sections.add(section2);
						String getUrl = new LrtsURLBuilder().getURI()
						.setHost("dapis.mting.info")
						.setPath("/yyting/gateway/entityPath.action")
						.addParameter("entityId", "5622")
						.addParameter("entityType", "3")
						.addParameter("opType", "1")
						.addParameter("type", "0")
						.addParameter("sections", sections.toJSONString())
						.toString();
						getUrl = ParamsVerify.convertUrlWIthAuth(getUrl);
						String body2 = new Downloader().getBodyStr(getUrl);
						String mp3Url = new BodyParse().getMp3Url(body2);
						//下载mp3
						System.out.println(Thread.currentThread().getName()+"正在下载:"+sectionName);
						FileDownloader.saveFile(path,sectionName,mp3Url);
						System.out.println(Thread.currentThread().getName()+"下载完成:"+sectionName);
					}
				});
			}
			try {
				Thread.sleep(1200000);
				System.out.println("循环下一批次");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
