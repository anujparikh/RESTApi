package eden.project.rest;

import eden.project.dao.ReservationDAO;
import eden.project.exception.AppException;
import eden.project.model.Reservation;
import eden.project.model.ReservationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/reservations")
@Api(tags = { "/reservations" })
public class ReservationController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all the reservations", notes = "This API fetchs all the reservations from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = null;
		try {
			ReservationDAO resDao = new ReservationDAO();
			reservations = resDao.fnGetAllReservation();
			if (reservations.isEmpty()) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservations;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all the reservations based on reservation id", notes = "This API fetch the reservations from the database based on reservation id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public ReservationRequest getReservationsByCust(@PathParam("id") int resId) {
		ReservationRequest reservationsById = null;
		try {
			ReservationDAO resDao = new ReservationDAO();
			reservationsById = resDao.fnGetReservationByResId(resId);
			if (reservationsById == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return reservationsById;
	}

	@POST
	@Path("/makereservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Creates a reservation and new customer", notes = "This API takes all the information from customer and creates a new customer and a new reservation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	// public Reservation makeReservation(Reservation res, Customer cust) {
	public ReservationRequest makeReservation(ReservationRequest resReq) {
		try {
			ReservationDAO resDao = new ReservationDAO();
			resReq = resDao.fnCreateReservation(resReq);
			if (resReq == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return resReq;
	}

	@PUT
	@Path("/editreservation/{resId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edit Reservation", notes = "This API takes all the information from about reservation and edits it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public ReservationRequest updateReservation(@PathParam("resId") int resId, ReservationRequest resReq) {
		try {
			ReservationDAO resDao = new ReservationDAO();
			resReq = resDao.fnEditReservation(resReq);
			if (resReq == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return resReq;
	}
	
	@POST
	@Path("/{resId}")
	@ApiOperation(value = "Cancel Reservation", notes = "This API takes the reservation id and cancels the reservation")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Delete Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public void cancelReservation(@PathParam("resId") int resId) {
		try {
			ReservationDAO resDao = new ReservationDAO();
			resDao.fnCancelReservation(resId);
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
}
