package org.demo.resource.impl;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.demo.entity.Client;
import org.demo.resource.ClientResource;
import org.demo.utils.Utils;

import java.net.URI;
import java.util.List;

@ApplicationScoped
@AllArgsConstructor
public class ClientResourceImpl implements ClientResource {

  @Override
  @Counted(name = "clients_request", description = "Recuento de llamadas a GET /api/clients")
  @Timed(name = "clients_duration", description = "Duración de llamada a GET /api/clients", unit = MetricUnits.MILLISECONDS)
  public List<Client> getAllClients() {
    Log.info("ClientResourceImpl -> Trayendo todos los clientes");
    return Client.listAll();
  }

  @Override
  @Counted(name = "clients_by_id_request", description = "Recuento de llamadas a GET /api/clients/{id}")
  @Timed(name = "clients_by_id_duration", description = "Duración de llamada a GET /api/clients/{id}", unit = MetricUnits.MILLISECONDS)
  public Client getClientById(Long id) {
    Log.info("ClientResourceImpl -> Trayendo client por id");
    return Client.findById(id);
  }

  @Override
  @Counted(name = "clients_count_requests", description = "Recuento de llamadas a GET /count")
  @Timed(name = "clients_count_duration", description = "Duración de llamada a GET /count", unit = MetricUnits.MILLISECONDS)
  public Long countAllClient() {
    Log.info("ClientResourceImpl -> Cantidad total de cliente");
    return Client.count();
  }

  @Override
  @Counted(name = "post_client_request", description = "Recuento de llamadas a  POST /api/clients")
  @Timed(name = "post_client_duration", description = "Duración de llamada a POST /api/clients}", unit = MetricUnits.MILLISECONDS)
  @Transactional
  public Response postClient(Client clientDto) {
    Log.info("ClientResourceImpl -> Creando cliente");
    Utils.validateClient(clientDto);
    Client.persist(clientDto);
    Log.info("ClientResourceImpl -> Cliente creado con exito");
    return Response.created(URI.create("/api/clients/" + clientDto.id)).build();
  }

  @Override
  @Counted(name = "put_client_requests", description = "Recuento de llamadas a PUT /api/clients/{id}")
  @Timed(name = "put_client_duration", description = "Duración de llamada a PUT /api/clients/{id}", unit = MetricUnits.MILLISECONDS)
  @Transactional
  public Client updateClient(Long id,Client client) {
    Log.info("ClientResourceImpl -> Actualizando cliente");
    Client entity = Client.findById(id);
    Utils.validateClient(client);

    entity.firstName = client.firstName;
    entity.lastName = client.lastName;
    entity.documentNumber = client.documentNumber;
    entity.email = client.email;
    Client.persist(entity);
    Log.info("ClientResourceImpl -> Cliente actualizado con exito");
    return entity;
  }

  @Override
  @Counted(name = "delete_client_requests", description = "Recuento de llamadas a DELETE /api/clients/{id}")
  @Timed(name = "delete_client_duration", description = "Duración de llamada a DELETE /api/clients/{id}", unit = MetricUnits.MILLISECONDS)
  @Transactional
  public void deleteClient(Long id) {
    Log.info("ClientResourceImpl -> Eliminado cliente");
    Client entity = Client.findById(id);
    if(entity == null) {
      throw new NotFoundException("Cliente no encontrado");
    }
    Client.deleteById(entity.id);
    Log.info("ClientResourceImpl -> Cliente eliminado con exito");
  }

}
