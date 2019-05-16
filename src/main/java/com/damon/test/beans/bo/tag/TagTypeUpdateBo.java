package com.damon.test.beans.bo.tag;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TagTypeUpdateBo{

    @NotEmpty
    private String type;
    @NotNull
    private Long categoryId;
    @NotEmpty
    private String category;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
