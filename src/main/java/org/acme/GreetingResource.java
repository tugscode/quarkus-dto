package org.acme;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    @Inject
    ReactiveMailer reactiveMailer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }


    @POST
    @Path("/reactive")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> sendASimpleEmailAsync(@Valid MailDTO mailDTO) {
        return reactiveMailer.send(
                        Mail.withText(mailDTO.getTo(), mailDTO.getSubject(), mailDTO.getText()).setFrom("tugs-erdene.o@unitel.mn"))
                .onItem().transform(ignored -> Response.ok(mailDTO).build())
                .onFailure().recoverWithItem(throwable -> Response.status(Response.Status.BAD_REQUEST)
                        .entity("Failed to send email: " + throwable.getMessage()).build());
    }
}


