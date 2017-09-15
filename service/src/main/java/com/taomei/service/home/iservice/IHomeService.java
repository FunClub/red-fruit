package com.taomei.service.home.iservice;

import com.taomei.dao.dtos.home.HomeInfoDto;
import com.taomei.dao.dtos.login.LoginDto;

/**
 * home模块接口
 */
public interface IHomeService {

    HomeInfoDto getHomeInfo(LoginDto dto) throws Exception;
}
