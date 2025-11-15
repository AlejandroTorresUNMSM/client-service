package org.demo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Client extends PanacheEntity {
  public String firstName;
  public String lastName;
  @Column(unique = true)
  public String documentNumber;
  public String email;

  public static Client findByDocumentNumber(String documentNumber){
    return find("documentNumber", documentNumber).firstResult();
  }

  public static Client findById(Long id){
    return find("id",id).firstResult();
  }
}
