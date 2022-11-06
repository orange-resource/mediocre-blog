package com.ongsat.blog.web.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.constant.RedisConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.AdminUserPO;
import com.ongsat.blog.api.entity.vo.admin.adminuser.AdminUserLoginParamVO;
import com.ongsat.blog.web.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AdminUserService extends ServiceImpl<AdminUserMapper, AdminUserPO> {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Response login(AdminUserLoginParamVO adminUserLoginParamVO) {

        String username = adminUserLoginParamVO.getUsername();
        String password = adminUserLoginParamVO.getPassword();

        int count = super.baseMapper.selectCount(username, password);
        if (count == 0) {
            return Response.build(RspCode.ACCOUNT_FILL_WARN);
        }

        String token = "MB" + IdUtil.simpleUUID() + IdUtil.objectId() + IdUtil.objectId();
        redisTemplate.opsForValue().set(RedisConstant.TOKEN + token, username, 1, TimeUnit.DAYS);

        Map<String, Object> build = new ResultBuilder()
                .setToken(token)
                .build();
        return Response.build(RspCode.LOGIN_SUCCESS, build);
    }

    public Response logout(String token) {
        redisTemplate.delete(RedisConstant.TOKEN + token);
        return Response.build(RspCode.LOGOUT_SUCCESS);
    }

}
