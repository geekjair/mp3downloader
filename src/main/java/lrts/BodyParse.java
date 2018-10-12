package lrts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class BodyParse {
	public Map<String, String> getBookResource(String body) {
		Map<String, String> result = new TreeMap<String, String>();//treemap排序
		JSONObject jsonObject = JSONObject.parseObject(body);
		JSONArray booklist = jsonObject.getJSONArray("list");
		for (int i = 0; i < booklist.size(); i++) {
			JSONObject book = booklist.getJSONObject(i);
			result.put(book.getString("section"), book.getString("name"));
		}
		return result;
	}

	private List<String> getUrl(String body) {
		List<String> result = new ArrayList<String>();
		JSONObject jsonObject = JSONObject.parseObject(body);
		JSONArray booklist = jsonObject.getJSONArray("list");
		for (int i = 0; i < booklist.size(); i++) {
			JSONObject book = booklist.getJSONObject(i);
			result.add(book.getString("path"));
		}
		return result;
	}

	public String getMp3Url(String body) {
		return getUrl(body).get(0);
	}
}
