package com.ftTeknoloji.finalcase.controller;

import com.ftTeknoloji.finalcase.auth.JwtTokenUtil;
import com.ftTeknoloji.finalcase.entity.AuthRequest;
import com.ftTeknoloji.finalcase.entity.AuthResponse;
import com.ftTeknoloji.finalcase.entity.Customer;
import com.ftTeknoloji.finalcase.excetions.LoginErrorMessage;
import com.ftTeknoloji.finalcase.excetions.PostErrorMessage;
import com.ftTeknoloji.finalcase.repository.CustomerRepository;
import com.ftTeknoloji.finalcase.service.HesapServisi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = {"http://localhost:8085"})

public class CustomerController {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private HesapServisi hesapServisi;

    @PostMapping("/save")
    public ResponseEntity addCustomer(@RequestBody Customer customer) throws PostErrorMessage {
        String email = customer.getEmail();
        if (customerRepository.existsByEmail(email)) {
            throw new PostErrorMessage("Böyle bir e-mail değeri bulunmakta");
        }

        return ResponseEntity.ok(hesapServisi.addcustomer(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            Customer customer = (Customer) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(customer);
            AuthResponse response = new AuthResponse("Bearer " + accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {

            throw new LoginErrorMessage("Şifreyi  hatalı girdiniz.");
        }
    }
}
