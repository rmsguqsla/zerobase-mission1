package api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import db.Wifi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OpenApi {
	public int getListTotalCount() {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String result;
		int list_total_count = 0;
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
			urlBuilder.append("/" + URLEncoder.encode("566f4b4970726d7338397a46615541","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			
			String url = urlBuilder.toString();
			
			// OkHttp 클라이언트 객체 생성
			OkHttpClient client = new OkHttpClient();
			// GET 요청 객체 생성
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			// OkHttp 클라이언트로 GET 요청 객체 전송
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				// 응답 받아서 처리
				ResponseBody body = response.body();
				if(body != null) {
					result = body.string();
					JsonElement element = parser.parse(result);
					JsonObject rootob = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
					list_total_count = rootob.getAsJsonObject().get("list_total_count").getAsInt();
				}
			} else {
				System.err.println("Error Occurred");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list_total_count;
	}
	
	public List<Wifi> getList(String start, String end) {
		List<Wifi> list = new ArrayList<>();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		String result;
		try {
			StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
			urlBuilder.append("/" + URLEncoder.encode("566f4b4970726d7338397a46615541","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
			urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
			urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
			urlBuilder.append("/" + URLEncoder.encode(start,"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
			urlBuilder.append("/" + URLEncoder.encode(end,"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
			
			String url = urlBuilder.toString();
			
			// OkHttp 클라이언트 객체 생성
			OkHttpClient client = new OkHttpClient();
			// GET 요청 객체 생성
			Request.Builder builder = new Request.Builder().url(url).get();
			Request request = builder.build();
			// OkHttp 클라이언트로 GET 요청 객체 전송
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				// 응답 받아서 처리
				ResponseBody body = response.body();
				if(body != null) {
					result = body.string();
					JsonElement element = parser.parse(result);
					JsonObject rootob = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
					JsonArray jsonArray = rootob.getAsJsonObject().get("row").getAsJsonArray();
					if(jsonArray != null) {
						int len = jsonArray.size();
						for(int i = 0; i < len; i++) {
							Wifi data = new Gson().fromJson(jsonArray.get(i), Wifi.class);
							list.add(data);
						}
					}
				}
			} else {
				System.err.println("Error Occurred");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}	
}
