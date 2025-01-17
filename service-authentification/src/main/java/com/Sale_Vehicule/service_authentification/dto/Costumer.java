package com.Sale_Vehicule.service_authentification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Costumer {
    private String name;
    private String email;
    private String password;
    private String role;
}
