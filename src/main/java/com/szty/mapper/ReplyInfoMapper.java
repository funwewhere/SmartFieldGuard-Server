package com.szty.mapper;

import com.szty.bean.ReplyInfo;
import org.apache.ibatis.annotations.Param;

public interface ReplyInfoMapper {
    int deleteByPrimaryKey(@Param("postNo") String postNo, @Param("replySn") Integer replySn);

    int insert(ReplyInfo record);

    int insertSelective(ReplyInfo record);

    ReplyInfo selectByPrimaryKey(@Param("postNo") String postNo, @Param("replySn") Integer replySn);

    int updateByPrimaryKeySelective(ReplyInfo record);

    int updateByPrimaryKeyWithBLOBs(ReplyInfo record);

    int updateByPrimaryKey(ReplyInfo record);
}