package com.taomei.service.circle.service;

import com.taomei.dao.entities.circle.Post;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.PostRepository;
import com.taomei.service.circle.iservice.ICircleService;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 帖子服务基本实现
 */
@Service
public class BaseCircleService implements ICircleService {
    private final UserMapper userMapper;
    private final PostRepository postRepository;

    @Autowired
    public BaseCircleService(PostRepository postRepository, UserMapper userMapper) {
        this.postRepository = postRepository;
        this.userMapper = userMapper;
    }

    /**
     * 添加帖子
     * @param post 帖子文档
     * @return 新的帖子
     */
    @Override
    public Post insertPost(Post post) {
        post.setDate(TimeUtil.getSimpleTime());
        Post newPost=postRepository.insert(post);

        return newPost;
    }
}
