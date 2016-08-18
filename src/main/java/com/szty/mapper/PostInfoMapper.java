package com.szty.mapper;

import com.szty.bean.PostInfo;

public interface PostInfoMapper {
    int deleteByPrimaryKey(String postNo);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(String postNo);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKeyWithBLOBs(PostInfo record);

    int updateByPrimaryKey(PostInfo record);
}