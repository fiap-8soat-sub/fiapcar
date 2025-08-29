package com.fiap.fiapcar.application.services;

import com.fiap.fiapcar.application.model.CustomerDTO;
import com.fiap.fiapcar.application.ports.in.CustomerPort;
import com.fiap.fiapcar.application.ports.out.CustomerCognitoPort;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;

@RequiredArgsConstructor
public class CustomerPortImpl implements CustomerPort {

    private final CustomerCognitoPort customerCognitoPort;
    private static final String UPPER   = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER   = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS  = "0123456789";
    private static final String SPECIAL = "^$*.[]{}()?-" + "\"!@#%&/\\,><':;|_~`+=";

    private static final SecureRandom RNG = new SecureRandom();

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        String pass = generateRamdomPassword(12);
        String id = customerCognitoPort.register(
                customerDTO.getEmail(),
                pass,
                customerDTO.getUsername());
        return new CustomerDTO(
                customerDTO.getName(),
                customerDTO.getEmail(),
                id,
                pass,
                customerDTO.getUsername()
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
