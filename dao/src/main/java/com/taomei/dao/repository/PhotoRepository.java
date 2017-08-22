package com.taomei.dao.repository;

import com.taomei.dao.entities.album.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface PhotoRepository extends MongoRepository<Photo,String> {
    /**
     * 通过相册id查询相片
     * @param albumId 相册id
     * @return
     */
    public List<Photo> findByAlbumId(String albumId);
}
