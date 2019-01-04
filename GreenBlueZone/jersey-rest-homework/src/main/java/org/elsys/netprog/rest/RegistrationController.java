package org.elsys.netprog.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/")
public class RegistrationController {
    private static List<Registration> registrations = new ArrayList<>();

    @PUT
    @Path("{a:green|blue}/{carReg}")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response putRegistrations(@PathParam("carReg") String carReg) {
        String pattern = "\\b[ETYOPAHKXCBM]{2}[0-9]{4}[ETYOPAHKXCBM]{2}\\b";
        if (carReg.matches(pattern)) {
            if (findRegistration(carReg) == null) {
                Registration reg = new Registration(carReg);
                registrations.add(reg);
                return Response.status(200).entity(reg).build();
            }
            else {
                findRegistration(carReg).updateRegistration();
                return Response.status(200).entity(findRegistration(carReg)).build();
            }
        }
        return Response.status(400).build();
    }

    @GET
    @Path("/{carReg}")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response getRegistrations(@PathParam("carReg") String carReg) {
        return Response.status(200).entity(findRegistration(carReg)).build();
    }

    private Registration findRegistration(String carReg) {
        return registrations.stream()
                .filter(customer -> carReg.equals(customer.getCarReg()))
                .findAny()
                .orElse(null);
    }
}
