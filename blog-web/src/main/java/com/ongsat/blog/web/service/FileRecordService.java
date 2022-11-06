package com.ongsat.blog.web.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ongsat.blog.api.entity.po.FileRecordPO;
import com.ongsat.blog.web.mapper.FileRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class FileRecordService extends ServiceImpl<FileRecordMapper, FileRecordPO> {

    /**
     * 上传文件记录
     */
    public boolean record(FileRecordPO fileRecordPO) {
        int insert = super.baseMapper.insert(fileRecordPO);
        if (insert == 0) {
            return false;
        }
        return true;
    }

}
