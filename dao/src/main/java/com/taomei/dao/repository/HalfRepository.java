package com.taomei.dao.repository;

import com.taomei.dao.entities.Half;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface HalfRepository extends MongoRepository<Half,BigInteger> {
    public Half findByUserId1(String userId1);
    public Half findByUserId2(String userId2);

}
