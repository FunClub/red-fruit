package com.taomei.dao.repository;

import com.taomei.dao.entities.half.Half;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface HalfRepository extends MongoRepository<Half,BigInteger> {

}
