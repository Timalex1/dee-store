package com.deestore.v1.webapp.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {

//    @Autowired
//    private StrongPasswordEncryptor strongEncryptor;

    @Override
    public String encryptString(String input) {
//        return strongEncryptor.encryptPassword(input);
        return null;
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
//        return strongEncryptor.checkPassword(plainPassword, encryptedPassword);
        return false;
    }
}
