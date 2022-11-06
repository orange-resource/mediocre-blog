package com.ongsat.blog.web.service;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.constant.SchemeNameConstant;
import com.ongsat.blog.api.entity.po.AliyunOssPO;
import com.ongsat.blog.api.entity.po.FileRecordPO;
import com.ongsat.blog.api.entity.vo.admin.oss.OssConfigSaveParamVO;
import com.ongsat.blog.api.response.Response;
import com.ongsat.blog.api.response.ResultBuilder;
import com.ongsat.blog.api.response.RspCode;
import com.ongsat.blog.web.common.convert.ConvertObject;
import com.ongsat.blog.web.mapper.AliyunOssMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class OssService extends ServiceImpl<AliyunOssMapper, AliyunOssPO> {

    @Autowired
    private FileRecordService fileRecordService;

    @Autowired
    private ConvertObject convertObject;

    public Response getItemToRsp() {
        AliyunOssPO aliyunOssPO = super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
        Map<String, Object> build = ResultBuilder.create().setDetail(aliyunOssPO).build();
        return Response.build(RspCode.QUERY_SUCCESS, build);
    }

    private AliyunOssPO getOssConfig() {
        AliyunOssPO aliyunOssPO = super.baseMapper.selectBySchemeName(SchemeNameConstant.DEFAULT);
        return aliyunOssPO;
    }

    public synchronized Response save(OssConfigSaveParamVO ossConfigSaveParamVO) {
        AliyunOssPO aliyunOssPO = convertObject.toAliyunOssPO(ossConfigSaveParamVO);
        aliyunOssPO.setSchemeName(SchemeNameConstant.DEFAULT);

        boolean update = super.saveOrUpdate(aliyunOssPO);
        if (update) {
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 上传文件
     */
    public String upload(MultipartFile multipartFile) throws Exception {

        AliyunOssPO aliyunOssConfig = getOssConfig();
        if (null == aliyunOssConfig) {
            throw new Exception("阿里云OSS数据库未配置");
        }

        OSS ossClient = new OSSClientBuilder().build(
                aliyunOssConfig.getEndpoint(),
                aliyunOssConfig.getKeyId(),
                aliyunOssConfig.getKeySecret());

        String fileUrl = "";
        String name = "";
        String originalFilename = "";
        String fileType = "";
        try {
            originalFilename = multipartFile.getOriginalFilename();
            String[] originalFilenameSplit = originalFilename.split("\\.");
            originalFilename = originalFilenameSplit[0];
            fileType = originalFilenameSplit[1];

            name = IdUtil.simpleUUID();
            fileUrl = aliyunOssConfig.getCatalogue() + "/" + name;
            fileUrl += "." + fileType;

            ossClient.putObject(aliyunOssConfig.getBucketName(), fileUrl, multipartFile.getInputStream());
        } catch (Exception e) {
            throw e;
        } finally {
            ossClient.shutdown();
        }

        String url = aliyunOssConfig.getDomainName() + "/" + fileUrl;

        FileRecordPO fileRecord = new FileRecordPO();
        fileRecord.setUrl(url);
        fileRecord.setFileSuffix(fileType);
        fileRecord.setName(name);
        fileRecord.setCloudPath(fileUrl);
        fileRecord.setOriginalName(originalFilename);
        boolean recording = fileRecordService.record(fileRecord);
        if (!recording) {
            throw new Exception("记录文件到mysql失败");
        }
        return url;
    }

}
