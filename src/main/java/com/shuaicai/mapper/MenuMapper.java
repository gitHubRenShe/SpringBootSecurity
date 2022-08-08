package com.shuaicai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shuaicai.domain.Menu;

import java.util.List;

/**
 * @ClassName MenuMapper
 * @Description TODO
 * @Author shuai cai
 * @Date 2022/8/7 16:12
 * @PackagePath com.shuaicai.mapper
 * @Version 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);
}
