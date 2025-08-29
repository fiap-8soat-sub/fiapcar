package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.CustomerDTO;
import com.fiap.fiapcar.application.ports.in.CustomerPort;
import com.fiap.fiapcar.application.ports.out.CustomerCognitoPort;
import com.fiap.fiapcar.application.ports.out.CustomerDatabasePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class CustomerPortImpl implements CustomerPort {

    private final CustomerCognitoPort customerCognitoPort;
    private final CustomerDatabasePort customerDatabasePort;
    private static final String UPPER   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER   = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS  = "0123456789";
    private static final String SPECIAL = "^$*.[]{}()?-" + "\"!@#%&/\\,><':;|_~`+=";

    private static final SecureRandom RNG = new SecureRandom();

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        log.info("[CustomerPortImpl.create]");
        String pass = generateRamdomPassword(12);
        String id = customerCognitoPort.register(
                customerDTO.getEmail(),
                pass,
                customerDTO.getUsername());

        log.info("[CustomerPortImpl.create] User={}, Id={}", customerDTO.getUsername(), id);
        return customerDatabasePort.createUser(buildNewCustomerDTO(customerDTO, id, pass));
    }

    @Override
    public Page<CustomerDTO> getAllCustomers(Pageable pageable) {
        log.info("[CustomerPortImpl.getAllCustomers]");
        return customerDatabasePort.getAllCustomers(pageable);
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        log.info("[CustomerPortImpl.getCustomerById] Request");
        return Optional.ofNullable(customerDatabasePort.getCustomerById(id))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private CustomerDTO buildNewCustomerDTO(CustomerDTO customerDTO, String id, String pass) {
        return new CustomerDTO(
                customerDTO.getName(),
                customerDTO.getEmail(),
                UUID.fromString(id),
                pass,
                customerDTO.getUsername(),
                LocalDateTime.now()
        );
    }

    public static String generateRamdomPassword(int length) {
        int n = Math.max(8, length);

        StringBuilder sb = new StringBuilder(n);
        sb.append(pick(UPPER));
        sb.append(pick(LOWER));
        sb.append(pick(DIGITS));
        sb.append(pick(SPECIAL));

        String ALL = UPPER + LOWER + DIGITS + SPECIAL;
        while (sb.length() < n) {
            sb.append(pick(ALL));
        }

        char[] a = sb.toString().toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            char tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }

        return new String(a);
    }

    private static char pick(String s) {
        return s.charAt(RNG.nextInt(s.length()));
    }
}
