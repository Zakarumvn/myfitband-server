package com.myfitband.server.controller;


import com.myfitband.server.config.JwtTokenUtil;
import com.myfitband.server.config.SessionService;
import com.myfitband.server.dto.JwtRequest;
import com.myfitband.server.dto.JwtResponse;
import com.myfitband.server.dto.SessionUser;
import com.myfitband.server.entity.User;
import com.myfitband.server.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    SessionService session;
    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, authenticationRequest.getUsername()));
    }
    private void authenticate(String username, String password) throws Exception {
        Authentication authentication = null;
        try {
            String encoded = new BCryptPasswordEncoder().encode(password);
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody JwtRequest user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(@RequestBody JwtRequest request) throws Exception {
        SessionUser user = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getUsername().equals(request.getUsername())){
            List<SessionInformation> sessionInformations = sessionRegistry.getAllSessions(user, false);
            sessionInformations.forEach(SessionInformation::expireNow);
        }
    }
}