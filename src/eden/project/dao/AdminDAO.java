package eden.project.dao;

import eden.project.exception.AppException;
import eden.project.model.ReservationRequest;
import eden.project.model.RestaurantProfile;
import eden.project.model.Table;
import eden.project.util.DBUtils;
import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

	public List<Table> fnGetSeatingArrng() throws AppException {

		List<Table> tables = new ArrayList<Table>();

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM TABLE_INFO");
			rs = ps.executeQuery();

			while (rs.next()) {
				Table table = new Table();
				table.setTableId(rs.getInt(1));
				table.setReservationId(rs.getInt(2));
				table.setCustomerId(rs.getInt(3));
				table.setTableCapacity(rs.getInt(4));
				table.setTableStatus(rs.getString(5));

				tables.add(table);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return tables;
	}

	public JSONObject fnCheckAdminCreds(String username, String password)
			throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		JSONObject obj = new JSONObject();

		try {
			ps = conn
					.prepareStatement("SELECT COUNT(1) FROM ADMIN_INFO WHERE USERNAME = ? AND PASSWORD = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {

				if (rs.getInt(1) > 0) {
					obj.put("valid", "Y");
				} else {
					obj.put("valid", "N");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		return obj;
	}

	public ReservationRequest fnEditAssignedTable(ReservationRequest resReq,
			int resId, int tableId) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn
					.prepareStatement("SELECT COUNT(1) FROM TABLE_INFO WHERE TABLEID = ? AND TABLESTATUS = 'N'");
			ps.setInt(1, tableId);
			rs = ps.executeQuery();

			if (rs.next()) {

				if (rs.getInt(1) > 0) {

					ps = conn
							.prepareStatement("UPDATE RESERVATION_INFO SET TABLEASSIGNED = ?, STATUS = 'C' WHERE RESERVATIONID = ?");
					ps.setInt(1, tableId);
					ps.setInt(2, resId);
					ps.executeUpdate();
					resReq.setTableAssigned(tableId);

					ps = conn
							.prepareStatement("UPDATE TABLE_INFO SET TABLESTATUS = 'Y' WHERE TABLEID = ?");
					ps.setInt(1, tableId);
					ps.executeUpdate();

				} else {
					return null;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return resReq;
	}

	public RestaurantProfile fnEditResProfile(RestaurantProfile resPro) throws AppException {
		
		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		
		try {
			ps = conn
					.prepareStatement("UPDATE RESTAURANT_PROFILE SET NAME = ?, EMAIL = ?, PHONENO = ?, ADDRESS = ?, AUTOASSIGN = ?, CLOSEDAYS = ?, OPENTIME = ?, CLOSETIME = ?");
			ps.setString(1, resPro.getResName());
			ps.setString(2, resPro.getResEmail());
			ps.setString(3, resPro.getResPhoneNo());
			ps.setString(4, resPro.getResAddress());
			ps.setString(5, resPro.getIsAutoAssign());
			ps.setString(6, resPro.getDaysClose());
			ps.setString(7, resPro.getResOpenTime());
			ps.setString(8, resPro.getResCloseTime());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}
		
		return resPro;
	}

	public RestaurantProfile fnFetchProfileDetails() throws AppException{
		
		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		RestaurantProfile resPro = new RestaurantProfile();

		try {
			ps = conn.prepareStatement("SELECT * FROM RESTAURANT_PROFILE");
			rs = ps.executeQuery();

			while (rs.next()) {
				
				resPro.setResName(rs.getString(1));
				resPro.setResEmail(rs.getString(2));
				resPro.setResPhoneNo(rs.getString(3));
				resPro.setResAddress(rs.getString(4));
				resPro.setIsAutoAssign(rs.getString(5));
				resPro.setDaysClose(rs.getString(6));
				resPro.setResOpenTime(rs.getString(7));
				resPro.setResCloseTime(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return resPro;
	}
}
