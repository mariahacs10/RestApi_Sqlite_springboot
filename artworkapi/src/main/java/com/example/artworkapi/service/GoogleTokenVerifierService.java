package com.example.artworkapi.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class GoogleTokenVerifierService {

    private final String CLIENT_ID = "927431940343-ep0cs8nkfleb3pgp87tmrp5qs8mon4bv.apps.googleusercontent.com"; 
   
    public GoogleIdToken verifyToken(String idTokenString) {
        @SuppressWarnings("deprecation")
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        try {
            return verifier.verify(idTokenString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
