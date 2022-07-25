package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
	public void insertHistory(String latitude, String longitude) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "/Users/ogeunhyeob/Desktop/wifi.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "insert into history (lat, lnt, inquiry_date) "
					   + "values (?, ?, current_timestamp);";
			pst = con.prepareStatement(sql);
			pst.setString(1, latitude);
			pst.setString(2, longitude);
			int affected = pst.executeUpdate();
			
			if (affected > 0) {
				System.out.println("저장 성공");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
			
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<History> historyList() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<History> list = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "/Users/ogeunhyeob/Desktop/wifi.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "select * from history order by id desc;";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				String id = String.valueOf(rs.getInt(1));
				String lat = rs.getString(2);
				String lnt = rs.getString(3);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				String inquiryDate = simpleDateFormat.format(rs.getTimestamp(4));
				
				History history = new History(id, lat, lnt, inquiryDate);
				
				list.add(history);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
			
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void historyDelete(String historyId) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "/Users/ogeunhyeob/Desktop/wifi.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "delete from history where id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, Integer.parseInt(historyId));
			
			int affected = pst.executeUpdate();
			
			if (affected > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
                if (pst != null && !pst.isClosed()) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
			
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
