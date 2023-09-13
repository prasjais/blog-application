package com.blogapplication.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty(message = "Username consists maximum of 10 characters")
    @Size(max = 10)
    private String name;

    @Email(message = "Email is not valid !!")
    private String email;

    @NotEmpty(message = "Password must be min of 3 and max of 10 characters")
    @Size(min = 3, max = 10)
    private String password;

    @NotEmpty
    private String about;
}
