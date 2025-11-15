package org.demo.utils;

import jakarta.ws.rs.NotFoundException;
import org.demo.entity.Client;

import java.util.regex.Pattern;

public class Utils {

  private static final Pattern EMAIL_PATTERN = Pattern.compile(
      "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
  );

  private static final Pattern NAME_PATTERN = Pattern.compile(
      "^[A-Z][a-z]*$"
  );


  public static void validateClient(Client client){
    if(client == null) {
      throw new NotFoundException();
    }
    validateEmail(client.email);
    validateName(client.firstName);
    validateName(client.lastName);
  }

  public static void validateEmail(String email) {
    if (!isValidEmail(email)) {
      throw new IllegalArgumentException("El correo electrónico no es válido: " + email);
    }
  }

  public static void validateName(String name) {
    if (!isValidName(name)) {
      throw new IllegalArgumentException(
          "El nombre debe comenzar con mayúscula y solo contener letras: " + name
      );
    }
  }

  public static boolean isValidEmail(String email) {
    if (email == null || email.isBlank()) {
      return false;
    }
    return EMAIL_PATTERN.matcher(email).matches();
  }

  public static boolean isValidName(String name) {
    if (name == null || name.isBlank()) {
      return false;
    }
    return NAME_PATTERN.matcher(name).matches();
  }
}
