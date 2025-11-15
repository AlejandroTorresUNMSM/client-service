package org.demo.resource.impl;

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
  public List<Client> getAllClients() {
    Log.info("ClientResourceImpl -> Trayendo todos los clientes");
    return Client.listAll();
  }

  @Override
  public Client getClientById(Long id) {
    Log.info("ClientResourceImpl -> Trayendo client por id");
    return Client.findById(id);
  }

  @Override
  public Long countAllClient() {
    Log.info("ClientResourceImpl -> Cantidad total de cliente");
    return Client.count();
  }

  @Override
  @Transactional
  public Response postClient(Client clientDto) {
    Log.info("ClientResourceImpl -> Creando cliente");
    Utils.validateClient(clientDto);
    Client.persist(clientDto);
    Log.info("ClientResourceImpl -> Cliente creado con exito");
    return Response.created(URI.create("/api/clients/" + clientDto.id)).build();
  }

  @Override
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
