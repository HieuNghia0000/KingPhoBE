package org.application.kingphobe.service;

import org.application.kingphobe.dto.LoginDTO;
import org.application.kingphobe.model.User;

public interface AuthenticationService {
    User authenticate(LoginDTO input);
}
