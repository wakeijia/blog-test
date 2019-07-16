package com.cheer.blogtest.service.impl;

import com.cheer.blogtest.dao.ReplyMapper;
import com.cheer.blogtest.model.Reply;
import com.cheer.blogtest.service.ReplyService;
import com.cheer.blogtest.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;


    @Override
    public List<ReplyVo> getReplyVoList(int bid) {
        List<ReplyVo> replyVoList = this.replyMapper.getReplyVoList(bid);
        return replyVoList;
    }

    @Override
    public int insertReply(Reply reply) {
        int i = this.replyMapper.insertReply(reply);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }
    }

    @Override
    public int selectFloor(int bid) {
        int floor = this.replyMapper.selectFloor(bid);
        return floor;
    }

    @Override
    public List<ReplyVo> getReplyList(String username) {
        return this.replyMapper.getReplyList(username);
    }
}
