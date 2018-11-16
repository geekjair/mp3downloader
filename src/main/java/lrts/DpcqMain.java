package lrts;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import common.Downloader;
import common.FileDownloader;

/**
 * 斗破苍穹下载
 * 支持的功能：
 * 1.批量下载
 * 2.完成即关闭
 * @author jairwx
 *
 */
public class DpcqMain {
	public final static String path = "dpcq2";
	public static Logger logger = LoggerFactory.getLogger(DpcqMain.class);
	public static
	void main(String[] args) throws URISyntaxException {
		logger.info("log");
		//线程池
		ExecutorService pool = Executors.newFixedThreadPool(16);
		//获取列表
		String url = new LrtsURLBuilder().getURI()
		.addParameter("bookId", "5622")//斗破苍穹的书ID
		.addParameter("pageNum", "31")//页数
		.addParameter("pageSize", "50")//每页的个数
		.addParameter("sortType", "0")
		.setHost("dapis.mting.info")
		.setPath("/yyting/bookclient/ClientGetBookResource.action").toString();
		url = ParamsVerify.convertUrlWIthAuth(url);
		String body = new Downloader().getBodyStr(url);
		Map<String, String> resultMap = new BodyParse().getBookResource(body);
		for (String section : resultMap.keySet()) {
			final String section2 = section;
			String sectionName2 = resultMap.get(section2);
			final String sectionName = sectionName2.replace("？", "_");
			logger.info("递交任务:"+sectionName);
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
					logger.info(Thread.currentThread().getName()+"正在下载:"+sectionName);
					logger.info("Map3Url"+mp3Url);
					//对MP3名称
					FileDownloader.saveFile(path,sectionName,mp3Url);
					logger.info(Thread.currentThread().getName()+"下载完成:"+sectionName);
				}
			});
		}
		//任务执行后
		pool.shutdown();
		
	}
	

}
