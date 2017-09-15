package com.taomei.service.circle.service;

import com.taomei.dao.dtos.base.ArtInfoDto;
import com.taomei.dao.dtos.base.ShowPagedArtDto;
import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.circle.SelectOnePostDto;
import com.taomei.dao.dtos.circle.SelectPostConditionDto;
import com.taomei.dao.dtos.circle.ShowPostDto;
import com.taomei.dao.entities.circle.Post;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.dao.repository.PostRepository;
import com.taomei.service.circle.iservice.ICircleService;
import com.taomei.service.share.enums.SortBy;
import com.taomei.service.share.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 帖子服务基本实现
 */
@Service
public class BaseCircleService implements ICircleService {
    private final UserMapper userMapper;
    private final PostRepository postRepository;
    private final DiscussionRepository discussionRepository;
    @Autowired
    public BaseCircleService(PostRepository postRepository, UserMapper userMapper, DiscussionRepository discussionRepository) {
        this.postRepository = postRepository;
        this.userMapper = userMapper;
        this.discussionRepository = discussionRepository;
    }

    /**
     * 查询一个帖子
     *
     * @param dto 查询一个帖子的dto
     * @return 显示一个帖子dto
     */
    @Override
    public ShowPostDto selectPost(SelectOnePostDto dto) {
        String postId = dto.getPostId();
        String userId = dto .getUserId();
        Post post = postRepository.findOne(postId);
        return generateShowPostDto(post,userId);
    }

    /**
     * 添加帖子
     * @param post 帖子文档
     * @return 新的帖子
     */
    @Override
    public ShowPostDto insertPost(Post post) {
        ShowPostDto showPostDto = new ShowPostDto();
        post.setDate(TimeUtil.getSimpleTime());
        Post newPost=postRepository.insert(post);
        showPostDto.setPost(post);
        ArtInfoDto artInfoDto = new ArtInfoDto();
        UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(post.getUserId());
        artInfoDto.setDiscussionCount(0L);
        artInfoDto.setThumbsUpCount(0L);
        artInfoDto.setThumbsUpAble(true);
        artInfoDto.setNickname(npInfoDto.getNickname());
        artInfoDto.setProfile(npInfoDto.getProfileImg());
        showPostDto.setArtInfo(artInfoDto);
        return showPostDto;
    }

    /**
     * 查询帖子
     * @param dto dto
     * @return 分页的帖子列表
     */
    @Override
    public ShowPagedArtDto<ShowPostDto> selectPosts(SelectPostConditionDto dto) {
        //确定排序类型
        String sortBy = dto.getSortBy();
        Sort sort;
        if(sortBy.equals(SortBy.HOT.getSort())){
            sort = new Sort(Sort.Direction.DESC,"discussionCount");
        }else{
            sort = new Sort(Sort.Direction.DESC,"date");
        }
        //分页
        PageRequest pageRequest = new PageRequest(dto.getPageIndex(),dto.getPageSize(),sort);

        //查询帖子
        Page<Post> postPage=null;
        String userId = dto.getUserId();
        String circleName = dto.getCircleName();
        if(circleName!=null){
            Post exPost=  new Post();
            exPost.setCircleName(circleName);
            postPage = postRepository.findAll(Example.of(exPost),pageRequest);
        }else {
            postPage = postRepository.findAll(pageRequest);
        }

        List<ShowPostDto> showPostDtos = new ArrayList<>();
        List<Post> posts = postPage.getContent();
        for (Post post:posts){
            showPostDtos.add(generateShowPostDto(post,userId));
        }

        //填充ShowPagedArtDto
        ShowPagedArtDto showPagedArtDto = new ShowPagedArtDto();
        showPagedArtDto.setTotalElements(postPage.getTotalElements());
        showPagedArtDto.setContent(showPostDtos);
        return showPagedArtDto;
    }

    /**
     * 生成PostDto
     * @param post 帖子文档
     * @param userId 用户id
     * @return  显示一个帖子的dto
     */
   private ShowPostDto generateShowPostDto(Post post,String userId){
       ShowPostDto showPostDto = new ShowPostDto();
       showPostDto.setPost(post);

       //填充动态信息
       ArtInfoDto artInfoDto = new ArtInfoDto();
       UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(post.getUserId());
       artInfoDto.setHowLongAgo(TimeUtil.calculateHowLongAgo(post.getDate()));
       artInfoDto.setProfile(npInfoDto.getProfileImg());
       artInfoDto.setNickname(npInfoDto.getNickname());
       List<String> thumbsUserIds = post.getThumbsUpUserIds();
       if(thumbsUserIds!=null){
           artInfoDto.setThumbsUpCount((long) thumbsUserIds.size());
           artInfoDto.setThumbsUpAble(!thumbsUserIds.contains(userId));
       }else{
           artInfoDto.setThumbsUpCount(0L);
           artInfoDto.setThumbsUpAble(true);
       }
       showPostDto.setArtInfo(artInfoDto);
       //填充评论数量
       ParentDiscussion parentDiscussion = new ParentDiscussion();
       parentDiscussion.setArtId(post.getPostId());
       artInfoDto.setDiscussionCount( discussionRepository.count(Example.of(parentDiscussion)));

       return  showPostDto;
   }
}
