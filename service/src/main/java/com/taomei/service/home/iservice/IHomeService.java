package com.taomei.service.home.iservice;

import com.taomei.dao.dtos.home.HomeInfoDto;

/**
 * home模块接口
 */
public interface IHomeService {

    HomeInfoDto getHomeInfo(String userId) throws Exception;
}
