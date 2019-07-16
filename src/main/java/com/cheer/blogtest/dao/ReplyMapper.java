package com.cheer.blogtest.dao;

import com.cheer.blogtest.model.Reply;
import com.cheer.blogtest.vo.ReplyVo;

import java.util.List;

public interface ReplyMapper {

    List<ReplyVo> getReplyVoList(int bid);

    int insertReply(Reply reply);

    int selectFloor(int bid);

    List<ReplyVo> getReplyList(String username);


}
