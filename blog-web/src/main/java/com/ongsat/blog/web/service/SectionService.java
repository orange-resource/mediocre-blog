package com.ongsat.blog.web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.api.entity.po.SectionPO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionCreateParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionDeleteParamVO;
import com.ongsat.blog.api.entity.vo.admin.section.SectionUpdateParamVO;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.SectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SectionService extends ServiceImpl<SectionMapper, SectionPO> {

    @Autowired
    private ConvertObject convertObject;

    public Response queryAll() {
        List<SectionPO> sectionPOList = super.baseMapper.selectListByCreateAt();
        Map<String, Object> build = new ResultBuilder()
                .setList(sectionPOList)
                .build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    public Response create(SectionCreateParamVO sectionCreateParamVO) {
        SectionPO sectionPO = convertObject.toSectionPO(sectionCreateParamVO);
        int insert = super.baseMapper.insert(sectionPO);
        if (insert == 0) {
            return Response.error();
        }
        return Response.success();
    }

    public Response update(SectionUpdateParamVO sectionUpdateParamVO) {
        SectionPO sectionPO = convertObject.toSectionPO(sectionUpdateParamVO);
        int update = super.baseMapper.updateById(sectionPO);
        if (update == 0) {
            return Response.error();
        }
        return Response.success();
    }

    public Response delete(SectionDeleteParamVO sectionDeleteParamVO) {
        String id = sectionDeleteParamVO.getId();
        int delete = super.baseMapper.deleteById(id);
        if (delete == 0) {
            return Response.error();
        }
        return Response.success();
    }

}
