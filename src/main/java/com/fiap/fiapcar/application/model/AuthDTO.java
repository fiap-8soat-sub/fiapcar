package com.fiap.fiapcar.application.model;

import java.util.Map;

public class AuthDTO {
    private boolean ok;
    private String challenge;
    private String session;
    private Map<String,String> challengeParams;
    private String idToken;
    private String accessToken;
    private String refreshToken;
    private Integer expiresI;

    public AuthDTO(String refreshToken, boolean ok, String challenge, String session, Map<String, String> challengeParams, String idToken, String accessToken, Integer expiresI) {
        this.refreshToken = refreshToken;
        this.ok = ok;
        this.challenge = challenge;
        this.session = session;
        this.challengeParams = challengeParams;
        this.idToken = idToken;
        this.accessToken = accessToken;
        this.expiresI = expiresI;
    }

    public AuthDTO() {}

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Map<String, String> getChallengeParams() {
        return challengeParams;
    }

    public void setChallengeParams(Map<String, String> challengeParams) {
        this.challengeParams = challengeParams;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresI() {
        return expiresI;
    }

    public void setExpiresI(Integer expiresI) {
        this.expiresI = expiresI;
    }
}
