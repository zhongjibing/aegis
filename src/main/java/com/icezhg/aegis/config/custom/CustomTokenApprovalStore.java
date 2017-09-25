package com.icezhg.aegis.config.custom;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;

public class CustomTokenApprovalStore implements ApprovalStore {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomTokenApprovalStore.class);

    private TokenApprovalStore tokenApprovalStore;

    public CustomTokenApprovalStore(TokenApprovalStore tokenApprovalStore) {
        this.tokenApprovalStore = tokenApprovalStore;
    }

    @Override
    public boolean addApprovals(Collection<Approval> approvals) {
        return tokenApprovalStore.addApprovals(approvals);
    }

    @Override
    public boolean revokeApprovals(Collection<Approval> approvals) {
        return tokenApprovalStore.revokeApprovals(approvals);
    }

    @Override
    public Collection<Approval> getApprovals(String userId, String clientId) {
        return tokenApprovalStore.getApprovals(userId, clientId);
    }

}
