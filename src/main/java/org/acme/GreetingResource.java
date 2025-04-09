package org.acme;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Valid;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<GreetingDTO> createGreeting(@Valid GreetingDTO greetingDTO) {
        return Uni.createFrom().item(() -> {
            greetingDTO.setMessage("Hello, " + greetingDTO.getMessage());
            greetingDTO.setResponse("Response, " + greetingDTO.getResponse());
            return greetingDTO;
        });
    }
}


