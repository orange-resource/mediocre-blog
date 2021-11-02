package com.ongsat.blog.api.entity.vo.admin.oss;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OssConfigSaveParamVO implements Serializable {

    private String domainName;

    private String endpoint;

    private String keyId;

    private String keySecret;

    private String bucketName;

    private String catalogue;

    private String region;

}
