package eden.project.app;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ReservationApplication extends ResourceConfig{

	public ReservationApplication() {
		packages("eden.project.rest");
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/RestaurantReservationAPI/api");
        beanConfig.setResourcePackage("eden.project");
        beanConfig.setTitle("Restaurant Reservation APIs");
        beanConfig.setDescription("APIs for getting information and fetching reservations and customers");
        beanConfig.setScan(true);
	}
}
