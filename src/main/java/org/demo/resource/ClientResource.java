package org.demo.resource;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.demo.entity.Client;

import java.util.List;

@Path("/api/clients")
public interface ClientResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<Client> getAllClients();

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Client getClientById(@PathParam("id")Long id);

  @GET
  @Path("/count")
  @Produces(MediaType.APPLICATION_JSON)
  Long countAllClient();

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  Response postClient(Client clientDto);

  @PUT
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  Client updateClient(@PathParam("id") Long id,Client client);

  @DELETE
  @Path("/{id}")
  void deleteClient(@PathParam("id")Long id);

}
