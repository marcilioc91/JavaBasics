package com.marciliocabral;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;

@Path("secure")
@RequestScoped
public class SecureResource {

    @Inject
    @Claim(standard = Claims.preferred_username)
    String username;

    @GET
    @Path("claim")
    @RolesAllowed("Subscriber")
    public String getClaim() {
        return username;
    }
}
