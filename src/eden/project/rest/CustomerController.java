package eden.project.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import eden.project.dao.CustomerDAO;
import eden.project.exception.AppException;
import eden.project.model.Customer;
import eden.project.model.Reservation;

@Path("/customers")
@Api(tags = { "/customers" })
public class CustomerController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all the customers", notes = "This API fetchs all the customers from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;
		try {
			CustomerDAO custDao = new CustomerDAO();
			customers = custDao.fnGetAllCustomer();
			if (customers.isEmpty()) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return customers;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all the reservations based on customer id", notes = "This API fetchs all the reservations from the database based on customer id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public List<Reservation> getReservationsByCust(@PathParam("id") int custId) {
		List<Reservation> reservationsById = null;
		try {
			CustomerDAO resDao = new CustomerDAO();
			reservationsById = resDao.fnGetReservationByCust(custId);
			if (reservationsById.isEmpty()) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservationsById;
	}

	public void updateReservation() {

	}
}
