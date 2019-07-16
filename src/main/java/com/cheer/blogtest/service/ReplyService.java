package com.cheer.blogtest.service;

import com.cheer.blogtest.model.Reply;
import com.cheer.blogtest.vo.ReplyVo;

import java.util.List;

public interface ReplyService {

    List<ReplyVo> getReplyVoList(int bid);

    int insertReply(Reply reply);

    int selectFloor(int bid);

    List<ReplyVo> getReplyList(String username);
}
