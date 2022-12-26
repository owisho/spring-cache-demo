package com.example.springcachedemo.config;

import com.example.springcachedemo.util.XssUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

@Configuration
public class MessageConvertersConfig extends WebMvcConfigurationSupport {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        /**
         * 替换默认的MappingJackson2HttpMessageConverter，过滤(json请求参数)xss
         */
        ListIterator<HttpMessageConverter<?>> listIterator = converters.listIterator();
        while (listIterator.hasNext()) {
            HttpMessageConverter<?> next = listIterator.next();
            if (next instanceof MappingJackson2HttpMessageConverter) {
                listIterator.remove();
                break;
            }
        }
        converters.add(getMappingJackson2HttpMessageConverter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        // 创建自定义ObjectMapper
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new JsonHtmlXssDeserializer(String.class));
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(this.getApplicationContext()).build();
        objectMapper.registerModule(module);
        // 创建自定义消息转换器
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }

}

class JsonHtmlXssDeserializer extends JsonDeserializer<String> {

    public JsonHtmlXssDeserializer(Class<String> string) {
        super();
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getValueAsString();
        String name = jsonParser.getCurrentName();
        //content 字段每个接口使用时自行判断
        if (StringUtils.isNotEmpty(name) && Arrays.asList("content", "newTitle", "title").contains(name)) {
            return value;
        }
        if (value != null) {
            return XssUtils.escape(value);
        }
        return value;
    }

}