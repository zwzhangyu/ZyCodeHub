package com.zy.redis.cache;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7760614561073458247L;
    private Long id;
    private String userName;
    private String note;
}