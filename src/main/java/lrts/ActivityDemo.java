package lrts;

import java.util.Random;

import common.Downloader;

/**
 * 任务中心
 * @author admin
 *
 */
public class ActivityDemo {
	public static void main(String[] args){
		//test2();
		test1();
	}
	public static void test1(){
		String url = new LrtsURLBuilder().getURI()
				//.addParameter("bookId", "5622")
				//.addParameter("pageNum", "30")
				//.addParameter("pageSize", "42")
				.addParameter("taskId", "313")
				.addParameter("reqId", String.valueOf(new Random().nextInt(100000)))
				.addParameter("imei", "OTkwMDA5MzkxMzQzMzc=")
				.addParameter("q", "1080")
				.setHost("dapi.mting.info")
				.setPath("/yyting/integral/taskEvent.action").toString();
				url = ParamsVerify.convertUrlWIthAuth(url);
				String body = new Downloader().getBodyStr(url);
				System.out.println(body);
	}
	
	public static void test2(){
		String url = new LrtsURLBuilder().getURI()
				//.addParameter("bookId", "5622")
				//.addParameter("pageNum", "30")
				//.addParameter("pageSize", "42")
				//.addParameter("taskId", "310")
				//.addParameter("reqId", "1542376232172")
				.addParameter("id", "0")
				.addParameter("op", "1")
				.addParameter("playTime", "0")
				.addParameter("time", "1542377139")
				.addParameter("type", "11")
				.addParameter("imei", "OTkwMDA5MzkxMzQzMzc=")
				.addParameter("q", "1080")
				.setHost("dmonitor.mting.info")
				.setPath("/upload/clickAdvert").toString();
				url = ParamsVerify.convertUrlWIthAuth(url);
				String body = new Downloader().getBodyStr(url);
				System.out.println(body);
	}
}
