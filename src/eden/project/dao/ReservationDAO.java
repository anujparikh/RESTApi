package eden.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eden.project.exception.AppException;
import eden.project.model.Reservation;
import eden.project.model.ReservationRequest;
import eden.project.util.DBUtils;

public class ReservationDAO {

	public List<Reservation> fnGetAllReservation() throws AppException {

		List<Reservation> reservations = new ArrayList<Reservation>();

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("SELECT * FROM RESERVATION_INFO");
			rs = ps.executeQuery();

			while (rs.next()) {
				Reservation res = new Reservation();
				res.setReservationId(rs.getInt(1));
				res.setCustomerId(rs.getInt(2));
				res.setReservationDate(rs.getString(3));
				res.setFromTime(rs.getString(4));
				res.setToTime(rs.getString(5));
				res.setPartySize(rs.getInt(6));
				res.setNotes(rs.getString(7));

				reservations.add(res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return reservations;
	}

	public Reservation fnGetReservationByResId(int resId) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reservation res = new Reservation();

		try {
			ps = conn
					.prepareStatement("SELECT * FROM RESERVATION_INFO WHERE RESERVATIONID = ?");
			ps.setInt(1, resId);
			rs = ps.executeQuery();

			if (rs.next()) {

				res.setReservationId(rs.getInt(1));
				res.setCustomerId(rs.getInt(2));
				res.setReservationDate(rs.getString(3));
				res.setFromTime(rs.getString(4));
				res.setToTime(rs.getString(5));
				res.setPartySize(rs.getInt(6));
				res.setNotes(rs.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return res;
	}

	// public Reservation fnCreateReservation(Reservation res, Customer cust)
	// throws AppException {
	public ReservationRequest fnCreateReservation(ReservationRequest resReq)
			throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conn
					.prepareStatement("SELECT CUSTOMERID FROM CUSTOMER_INFO WHERE FIRSTNAME = ? AND LASTNAME = ?");
			ps.setString(1, resReq.getFirstName());
			ps.setString(2, resReq.getLastName());

			rs = ps.executeQuery();

			if (rs.next()) {
				resReq.setCustomerId(rs.getInt(1));
			} else {

				ps = conn
						.prepareStatement(
								"INSERT INTO CUSTOMER_INFO (FirstName, LastName, PhoneNo, Email) VALUES (?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, resReq.getFirstName());
				ps.setString(2, resReq.getLastName());
				ps.setString(3, resReq.getPhoneNo());
				ps.setString(4, resReq.getEmail());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				if (rs.next()) {
					resReq.setCustomerId(rs.getInt(1));
				}
			}

			String confStatus = "C";
			String waitStatus = "W";
			ps = conn
					.prepareStatement("SELECT TABLEID FROM TABLE_INFO WHERE TABLECAP > ? AND TABLESTATUS = 'N' ORDER BY TABLECAP");
			ps.setInt(1, resReq.getPartySize());
			rs = ps.executeQuery();
			if (rs.next()) {

				resReq.setTableAssigned(rs.getInt(1));
				resReq.setResStatus(confStatus);
				ps = conn
						.prepareStatement("UPDATE TABLE_INFO SET TABLESTATUS = 'Y' WHERE TABLEID = ?");
				ps.setInt(1, resReq.getTableAssigned());
				ps.executeUpdate();

			} else {
				resReq.setResStatus(waitStatus);
			}

			ps = conn
					.prepareStatement(
							"INSERT INTO RESERVATION_INFO (CustomerId, Date, FromTime, ToTime, PartySize, Notes, Status, TableAssigned) VALUES (?, str_to_date(?,'%m-%d-%Y'), ?, ?, ?, ?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, resReq.getCustomerId());
			ps.setString(2, resReq.getReservationDate());
			ps.setString(3, resReq.getFromTime());
			ps.setString(4, resReq.getToTime());
			ps.setInt(5, resReq.getPartySize());
			ps.setString(6, resReq.getNotes());
			ps.setString(7, resReq.getResStatus());
			ps.setInt(8, resReq.getTableAssigned());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				resReq.setReservationId(rs.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return resReq;
	}

	public ReservationRequest fnEditReservation(ReservationRequest resReq) throws AppException {

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			
			ps = conn
					.prepareStatement("SELECT CUSTOMERID FROM CUSTOMER_INFO WHERE FIRSTNAME = ? AND LASTNAME = ?");
			ps.setString(1, resReq.getFirstName());
			ps.setString(2, resReq.getLastName());

			rs = ps.executeQuery();

			if (rs.next()) {
				resReq.setCustomerId(rs.getInt(1));
			} else {

				ps = conn
						.prepareStatement(
								"INSERT INTO CUSTOMER_INFO (FirstName, LastName, PhoneNo, Email) VALUES (?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, resReq.getFirstName());
				ps.setString(2, resReq.getLastName());
				ps.setString(3, resReq.getPhoneNo());
				ps.setString(4, resReq.getEmail());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();

				if (rs.next()) {
					resReq.setCustomerId(rs.getInt(1));
				}
			}
			
			ps = conn
					.prepareStatement("UPDATE RESERVATION_INFO SET CUSTOMERID = ?, DATE = str_to_date(?,'%m-%d-%Y'), FROMTIME = ?, TOTIME = ?, PARTYSIZE = ?, NOTES = ?, TABLEASSIGNED = ? WHERE RESERVATIONID = ?");
			ps.setInt(1, resReq.getCustomerId());
			ps.setString(2, resReq.getReservationDate());
			ps.setString(3, resReq.getFromTime());
			ps.setString(4, resReq.getToTime());
			ps.setInt(5, resReq.getPartySize());
			ps.setString(6, resReq.getNotes());
			ps.setInt(7, resReq.getReservationId());
			ps.setInt(8, resReq.getTableAssigned());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return resReq;
	}
}
