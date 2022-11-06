package com.ongsat.blog.web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.constant.SchemeNameConstant;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.SystemConfigPO;
import com.ongsat.blog.api.entity.vo.admin.systemconfig.SystemConfigSaveParamVO;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.SystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemConfigService extends ServiceImpl<SystemConfigMapper, SystemConfigPO> {

    @Autowired
    private ConvertObject convertObject;

    public SystemConfigPO getItem() {
        return super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
    }

    public Response getItemToRsp() {
        SystemConfigPO systemConfigPO = super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
        Map<String, Object> build = new ResultBuilder()
                .setDetail(systemConfigPO)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public synchronized Response save(SystemConfigSaveParamVO systemConfigSaveParamVO) {
        SystemConfigPO systemConfigPO = convertObject.toSystemConfigPO(systemConfigSaveParamVO);
        systemConfigPO.setSchemeName(SchemeNameConstant.DEFAULT);

        boolean update = super.saveOrUpdate(systemConfigPO);
        if (update) {
            return Response.success();
        }
        return Response.error();
    }

}
