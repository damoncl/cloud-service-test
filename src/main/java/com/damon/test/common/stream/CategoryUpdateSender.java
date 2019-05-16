package com.damon.test.common.stream;


import com.damon.test.beans.bo.tag.TagTypeUpdateBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(CategoryUpdateSender.CategoryUpdateOut.class)
public class CategoryUpdateSender {

    private final MessageChannel messageChannel;

    @Autowired
    public CategoryUpdateSender(@Qualifier(CategoryUpdateOut.CATEGORY_OUT) MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    public void updateCategory(TagTypeUpdateBo tagTypeUpdateBo) {
        messageChannel.send(MessageBuilder.withPayload(tagTypeUpdateBo).build());
    }

    interface CategoryUpdateOut {
        String CATEGORY_OUT = "categoryOut";

        @Output(CATEGORY_OUT)
        MessageChannel outPut();
    }
}
