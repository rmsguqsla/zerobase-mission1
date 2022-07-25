package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.OpenApi;

public class WifiService {
	public boolean insertApi() {
		OpenApi openApi = new OpenApi();
		int listTotalCount = openApi.getListTotalCount();
		int curCount = 0;
		int start = 1;
		int end = 1000;
		Connection con = null;
		PreparedStatement pst = null;
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "/Users/ogeunhyeob/Desktop/wifi.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			String sql = "insert into wifi (mgr_no, wrdofc, main_nm, "
							+ "adres1, adres2, instl_floor, "
							+ "instl_ty, instl_mby, svc_se, "
							+ "cmcwr, cnctc_year, inout_door, "
							+ "remars3, lat, lnt, work_dttm) "
							+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			pst = con.prepareStatement(sql);
			while(end <= listTotalCount) {
				List<Wifi> list = openApi.getList(String.valueOf(start), String.valueOf(end));
				for (int i = 0; i < list.size(); i++) {
					Wifi wifi = list.get(i);
					pst.setString(1, wifi.getX_SWIFI_MGR_NO());
					pst.setString(2, wifi.getX_SWIFI_WRDOFC());
					pst.setString(3, wifi.getX_SWIFI_MAIN_NM());
					pst.setString(4, wifi.getX_SWIFI_ADRES1());
					pst.setString(5, wifi.getX_SWIFI_ADRES2());
					pst.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
					pst.setString(7, wifi.getX_SWIFI_INSTL_TY());
					pst.setString(8, wifi.getX_SWIFI_INSTL_MBY());
					pst.setString(9, wifi.getX_SWIFI_SVC_SE());
					pst.setString(10, wifi.getX_SWIFI_CMCWR());
					pst.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
					pst.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
					pst.setString(13, wifi.getX_SWIFI_REMARS3());
					pst.setString(14, wifi.getLNT());
					pst.setString(15, wifi.getLAT());
					pst.setString(16, wifi.getWORK_DTTM());
					int affected = pst.executeUpdate();
					if (affected > 0) {
		                System.out.println("저장 성공");
		            }
				}
				curCount += 1000;
				start += 1000;
				if (listTotalCount - curCount > listTotalCount % 1000) {
					end += 1000;
				} else {
					end += listTotalCount % 1000;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (con != null && !con.isClosed()) {
					con.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public List<Wifi> selectWifi(String latitude, String longitude) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Wifi> list = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");
			String dbFile = "/Users/ogeunhyeob/Desktop/wifi.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			String sql = "select (select round(100 * sqrt(power(CAST(lat as real) - ?, 2) + power(CAST(lnt as real) - ?, 2)), 4)) as km, * "
					   + "from wifi "
					   + "order by km asc "
					   + "limit 20;";
			pst = con.prepareStatement(sql);
			pst.setDouble(1, Double.parseDouble(latitude));
			pst.setDouble(2, Double.parseDouble(longitude));
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				String KM = String.valueOf(rs.getDouble(1));
				String X_SWIFI_MGR_NO = rs.getString(2);
				String X_SWIFI_WRDOFC = rs.getString(3);
				String X_SWIFI_MAIN_NM = rs.getString(4);
				String X_SWIFI_ADRES1 = rs.getString(5);
				String X_SWIFI_ADRES2 = rs.getString(6);
				String X_SWIFI_INSTL_FLOOR = rs.getString(7);
				String X_SWIFI_INSTL_TY = rs.getString(8);
				String X_SWIFI_INSTL_MBY = rs.getString(9);
				String X_SWIFI_SVC_SE = rs.getString(10);
				String X_SWIFI_CMCWR = rs.getString(11);
				String X_SWIFI_CNSTC_YEAR = rs.getString(12);
				String X_SWIFI_INOUT_DOOR = rs.getString(13);
				String X_SWIFI_REMARS3 = rs.getString(14);
				String LAT = rs.getString(15);
				String LNT = rs.getString(16);
				String WORK_DTTM = rs.getString(17);
				
				Wifi wifi = new Wifi();
				wifi.setKM(KM);
				wifi.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
				wifi.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);
				wifi.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);
				wifi.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);
				wifi.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);
				wifi.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);
				wifi.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);
				wifi.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);
				wifi.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);
				wifi.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);
				wifi.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);
				wifi.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);
				wifi.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);
				wifi.setLAT(LAT);
				wifi.setLNT(LNT);
				wifi.setWORK_DTTM(WORK_DTTM);
				
				list.add(wifi);
			}
		} catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		return list;
	}
	
}
