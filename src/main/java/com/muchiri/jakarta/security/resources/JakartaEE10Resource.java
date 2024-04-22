package com.muchiri.jakarta.security.resources;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author muchiri
 */
@Path("resource")
@RequestScoped
public class JakartaEE10Resource {

    @Inject
    SecurityContext securityContext;

    @GET
    //@RolesAllowed(value = "caller") not necessary for now
    public Response getCallerAndRole() {
        try {
            String res = securityContext.getCallerPrincipal().getName() + " : " + securityContext.isCallerInRole("user") + "\n";
            return Response.ok(res).build();
        } catch (NullPointerException ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
