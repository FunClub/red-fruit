package com.taomei.dao.repository;

import com.taomei.dao.entities.half.Half;
import jdk.Exported;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface HalfRepository extends MongoRepository<Half,String> {
    public Half findByUserId1( String userId1);
    public Half findByUserId2(String userId2);

}
