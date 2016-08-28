package eden.project.rest;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.json.simple.JSONObject;

import eden.project.dao.AdminDAO;
import eden.project.exception.AppException;
import eden.project.model.ReservationRequest;
import eden.project.model.RestaurantProfile;
import eden.project.model.Table;

@Path("/admin")
@Api(tags = { "/admin" })
public class AdminController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns all the data of tables", notes = "This API fetchs all the data of tables from the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public List<Table> getSeatingArrg() {
		List<Table> tables = null;

		try {
			AdminDAO admDao = new AdminDAO();
			tables = admDao.fnGetSeatingArrng();

			if (tables.isEmpty()) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return tables;
	}

	@GET
	@Path("/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Authentication API", notes = "This API would check for username and password for admin login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public JSONObject getReservationsByCust(@PathParam("username") String username,
			@PathParam("password") String password) {
		JSONObject obj = null;
		try {
			
			AdminDAO admDao = new AdminDAO();
			obj = admDao.fnCheckAdminCreds(username, password);

		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return obj;
	}
	
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Profile fetching API", notes = "This API would all the details related to restaurant profile")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public RestaurantProfile fetchRestaurantProfile() {
		RestaurantProfile resProObj = null;
		try {
			
			AdminDAO admDao = new AdminDAO();
			resProObj = admDao.fnFetchProfileDetails();

		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return resProObj;
	}
	
	@PUT
	@Path("/edittable/{resId}/{tableid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edit the table assigned to a reservation", notes = "This API takes the information and updates the table assigned to it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public ReservationRequest updateAssignedTable(@PathParam("resId") int resId, @PathParam("tableid") int tableId, ReservationRequest resReq) {
		try {
			AdminDAO resDao = new AdminDAO();
			resReq = resDao.fnEditAssignedTable(resReq, resId, tableId);
			if (resReq == null) {
				System.out.println("Table already assigned");
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return resReq;
	}
	
	@PUT
	@Path("/resprofile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edits Restaurant's profile", notes = "This API take the profile information from admin and updates it")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Not Found"), })
	// public Reservation makeReservation(Reservation res, Customer cust) {
	public RestaurantProfile makeReservation(RestaurantProfile resPro) {
		try {
			AdminDAO resDao = new AdminDAO();
			resPro = resDao.fnEditResProfile(resPro);
			if (resPro == null) {
				throw new WebApplicationException(Status.NOT_FOUND);
			}
		} catch (AppException e) {
			e.printStackTrace();
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		return resPro;
	}
}
