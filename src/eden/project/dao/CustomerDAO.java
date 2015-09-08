package eden.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eden.project.exception.AppException;
import eden.project.model.Customer;
import eden.project.model.Reservation;
import eden.project.util.DBUtils;

public class CustomerDAO {
	
	public List<Customer> fnGetAllCustomer() throws AppException {
	
		List<Customer> customers = new ArrayList<Customer>();
		
		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM CUSTOMER_INFO");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer res = new Customer();
				res.setCustomerId(rs.getInt(1));
				res.setFirstName(rs.getString(2));
				res.setLastName(rs.getString(3));
				res.setPhoneNo(rs.getString(4));
				res.setEmail(rs.getString(5));
				
				customers.add(res);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(),e.getCause());
		}
		
		return customers;
	}
	
	public List<Reservation> fnGetReservationByCust(int custId)
			throws AppException {

		List<Reservation> reservationsById = new ArrayList<Reservation>();

		Connection conn = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn
					.prepareStatement("SELECT * FROM RESERVATION_INFO WHERE CUSTOMERID = ?");
			ps.setInt(1, custId);
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

				reservationsById.add(res);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
		}

		return reservationsById;
	}
}
