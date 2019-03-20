package org.examapp.authenticationservice.messages;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotBlank
    @ApiModelProperty(position = 0)
    private String username;

    @NotBlank
    @ApiModelProperty(position = 1)
    @Size(min = 6, max = 256)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
