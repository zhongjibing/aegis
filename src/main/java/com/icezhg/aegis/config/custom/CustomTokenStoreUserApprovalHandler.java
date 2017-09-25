package com.icezhg.aegis.config.custom;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;

public class CustomTokenStoreUserApprovalHandler implements UserApprovalHandler {

    private TokenStoreUserApprovalHandler tokenStoreUserApprovalHandler;

    public CustomTokenStoreUserApprovalHandler(TokenStoreUserApprovalHandler tokenStoreUserApprovalHandler) {
        this.tokenStoreUserApprovalHandler = tokenStoreUserApprovalHandler;
    }

    @Override
    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        return tokenStoreUserApprovalHandler.isApproved(authorizationRequest, userAuthentication);
    }

    @Override
    public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        return tokenStoreUserApprovalHandler.checkForPreApproval(authorizationRequest, userAuthentication);
    }

    @Override
    public AuthorizationRequest updateAfterApproval(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        return tokenStoreUserApprovalHandler.updateAfterApproval(authorizationRequest, userAuthentication);
    }

    @Override
    public Map<String, Object> getUserApprovalRequest(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
        return tokenStoreUserApprovalHandler.getUserApprovalRequest(authorizationRequest, userAuthentication);
    }
}
