package db;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;

public class OpenApi {
	public int getListTotalCount() throws IOException {
		/*URL*/
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
		urlBuilder.append("/" + URLEncoder.encode("566f4b4970726d7338397a46615541","UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo","UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode("1","UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode("5","UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
		// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
		//urlBuilder.append("/" + URLEncoder.encode("20220301","UTF-8")); /* 서비스별 추가 요청인자들*/
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(); conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json"); System.out.println("Response code: " + conn.getResponseCode()); /* 연결
		자체에 대한 확인이 필요하므로 추가합니다.*/ BufferedReader rd;
		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String result = sb.toString();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(result);
		JsonObject rootob = element.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject();
		int list_total_count = rootob.getAsJsonObject().get("list_total_count").getAsInt();
		
		return list_total_count;
	}
	
	public boolean getListTotalCount2() {
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
					System.out.println("Response : " + body.string());
				}
			} else {
				System.err.println("Error Occurred");
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		OpenApi openapi = new OpenApi();
		//System.out.println(openapi.getListTotalCount());
		System.out.println(openapi.getListTotalCount2());
	}
		
		
		/*
		while(true) {
			
		}
		*/
		//JsonObject row = rootob.getAsJsonObject().get("row").getAsJsonObject();
		
		//JsonArray item 
	
		
}
