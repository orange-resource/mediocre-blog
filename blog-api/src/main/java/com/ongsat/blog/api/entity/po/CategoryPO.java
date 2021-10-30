package com.ongsat.blog.api.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章分类
 * t_category
 */
@TableName("t_category")
@KeySequence("SEQ_TEST")
public class CategoryPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "create_at", fill = FieldFill.INSERT)
    private Date createAt;

    @TableField(value = "update_at", fill = FieldFill.UPDATE)
    private Date updateAt;

    /**
     * 分类名称
     * name
     */
    private String name;

    /**
     * 父级id
     * pid
     */
    private String pid;

    /**
     * 排序 大的数排最前面
     * sort
     */
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}