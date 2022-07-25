package db;

public class History {
	private String id;
	private String lat;
	private String lnt;
	private String inquiryDate;
	
	public History(String id, String lat, String lnt, String inquiryDate) {
		super();
		this.id = id;
		this.lat = lat;
		this.lnt = lnt;
		this.inquiryDate = inquiryDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLnt() {
		return lnt;
	}
	public void setLnt(String lnt) {
		this.lnt = lnt;
	}
	public String getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(String inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	@Override
	public String toString() {
		return "History [id=" + id + ", lat=" + lat + ", lnt=" + lnt + ", inquiryDate=" + inquiryDate + "]";
	}
}
