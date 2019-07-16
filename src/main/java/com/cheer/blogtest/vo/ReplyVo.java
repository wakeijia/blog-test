package com.cheer.blogtest.vo;

import com.cheer.blogtest.model.Reply;
import lombok.Data;

@Data
public class ReplyVo extends Reply {
    private String avatar;
    private String bUsername;
    private String message;

}
