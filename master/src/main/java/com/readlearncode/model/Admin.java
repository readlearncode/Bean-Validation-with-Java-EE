package com.readlearncode.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Admin {

    @NotNull
    @Valid
    private Transaction transaction;

    @NotNull
    @Valid
    private Client client;

}
