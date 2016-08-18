package com.szty.mapper.my;

import java.util.List;

import com.szty.bean.my.PostCriteria;
import com.szty.bean.my.VoPost;
import com.szty.bean.my.VoPostSimple;

public interface PostMapper {

	public List<VoPostSimple> getPostList(PostCriteria criteria);

	public int getPostCount(PostCriteria criteria);

	public VoPost getPostInfo(String postNo);

	public Integer getAndLockPostReplyNum(String postNo);

}
