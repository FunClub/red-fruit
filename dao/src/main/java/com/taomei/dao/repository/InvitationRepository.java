package com.taomei.dao.repository;

import com.taomei.dao.entities.invitation.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public interface InvitationRepository extends MongoRepository<Invitation,BigInteger> {
    /**
     * 查找用户的邀请信息
     * @param invitedId 用户Id
     * @return 邀请信息
     */
    List<Invitation> findByInvitedId(String invitedId);

    /**
     * 删除用户的邀请信息
     * @param id 邀请id
     * @return 行数
     */
    int deleteByInvitationId(@Param("id") String id);
}
