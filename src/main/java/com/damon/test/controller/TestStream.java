package com.damon.test.controller;

import com.damon.test.beans.bo.tag.TagTypeUpdateBo;
import com.damon.test.common.stream.CategoryUpdateSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class TestStream {

    @Autowired
    private CategoryUpdateSender categoryUpdateSender;

    @PostMapping("/updateType")
    public String updateCategory(@RequestBody TagTypeUpdateBo tagTypeUpdateBo) {
        categoryUpdateSender.updateCategory(tagTypeUpdateBo);
        return "success";
    }
}
