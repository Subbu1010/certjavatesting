package com.example.certservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@Component
public class CertificateLoader implements CommandLineRunner {
    private static final String CERTS_DIR = "/tmp";
    private static final String KEYSTORE_FILE = "keystore.jks";
    private static final String KEYSTORE_PASSWORD = "changeit";

    @Override
    public void run(String... args) throws Exception {
        File certsDir = new File(CERTS_DIR);
        if (!certsDir.exists() || !certsDir.isDirectory()) {
            System.out.println("Certificates directory not found: " + CERTS_DIR);
            return;
        }

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        File ksFile = new File(KEYSTORE_FILE);
        if (ksFile.exists()) {
            try (FileInputStream fis = new FileInputStream(ksFile)) {
                keyStore.load(fis, KEYSTORE_PASSWORD.toCharArray());
            }
        } else {
            keyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
        }

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        int certCount = 0;
        for (File file : certsDir.listFiles()) {
            if (file.isFile() && (file.getName().endsWith(".crt") || file.getName().endsWith(".pem"))) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
                    String alias = file.getName();
                    keyStore.setCertificateEntry(alias, cert);
                    certCount++;
                } catch (Exception e) {
                    System.err.println("Failed to load certificate: " + file.getName() + ", " + e.getMessage());
                }
            }
        }
        try (FileOutputStream fos = new FileOutputStream(ksFile)) {
            keyStore.store(fos, KEYSTORE_PASSWORD.toCharArray());
        }
        System.out.println(certCount + " certificates loaded into keystore: " + KEYSTORE_FILE);
    }
} 