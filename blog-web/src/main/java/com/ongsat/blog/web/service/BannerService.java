package com.ongsat.blog.web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.entity.po.BannerPO;
import com.ongsat.blog.api.entity.vo.admin.banner.BannerDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.banner.BannerSaveParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerService extends ServiceImpl<BannerMapper, BannerPO> {

    @Autowired
    private ConvertObject convertObject;

    public Response queryAll() {
        List<BannerPO> carouselPOList = super.baseMapper.selectListByCreateAt();

        Map<String, Object> build = new ResultBuilder()
                .setList(carouselPOList)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response save(BannerSaveParamVO bannerSaveParamVO) {
        BannerPO carouselPO = convertObject.toBannerPO(bannerSaveParamVO);
        boolean saveOrUpdate = super.saveOrUpdate(carouselPO);
        if (saveOrUpdate) {
            return Response.success();
        }
        return Response.error();
    }

    public Response delete(BannerDeleteParamVO bannerDeleteParamVO) {
        String id = bannerDeleteParamVO.getId();
        int delete = super.baseMapper.deleteById(id);
        if (delete == 0) {
            return Response.error();
        }
        return Response.success();
    }

}
