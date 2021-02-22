package ar.edu.itba.paw.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


public class LoginRequest implements Serializable {
    private String user;
    private String password;
}
