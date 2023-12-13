
import Seller.SellerConsumer;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class Resource {

    @Inject
    SellerConsumer sellerConsumer;


    @POST
    public Response send(String model) {
        sellerConsumer.removeCar(model);
        // Return an 202 - Accepted response.
        return Response.accepted().build();
    }
}
