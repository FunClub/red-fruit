package com.taomei.dao.repository;

import com.taomei.dao.entities.album.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlbumRepository extends MongoRepository<Album,String> {
    /**
     * 查询情侣相册按照更新时间降序
     * @param halfId 情侣id
     * @return
     */
    public List<Album> findByHalfIdOrderByUpdateDateDesc(String halfId);
}
