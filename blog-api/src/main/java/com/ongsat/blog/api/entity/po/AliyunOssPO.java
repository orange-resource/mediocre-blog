package com.ongsat.blog.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;

/**
 * 阿里云oss存储对象配置
 * t_aliyun_oss
 */
@TableName("t_aliyun_oss")
@KeySequence("SEQ_TEST")
public class AliyunOssPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "scheme_name", type = IdType.NONE)
    private String schemeName;

    /**
     * 前缀域名
     * domain_name
     */
    private String domainName;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * key_id
     */
    private String keyId;

    /**
     * key_secret
     */
    private String keySecret;

    /**
     * bucket_name
     */
    private String bucketName;

    /**
     * 目录路径
     * catalogue
     */
    private String catalogue;

    /**
     * bucket所在的区域， 默认oss-cn-hangzhou
     * region
     */
    private String region;

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}