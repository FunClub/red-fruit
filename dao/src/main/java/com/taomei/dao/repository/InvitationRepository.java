package com.taomei.dao.repository;

import com.taomei.dao.entities.invitation.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface InvitationRepository extends MongoRepository<Invitation,BigInteger> {
    List<Invitation> findByInvitedId(String invitedId);
}
