package com.mingxing.utils;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JacksonUtil {

    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        //忽略不存在的字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 创建一个模块并注册自定义的日期解析器
        module.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws
                    IOException {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = jsonParser.getText();
                try {
                    return formatter.parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mapper.registerModule(module);
    }

    public static <T> T convertObj(Object data, Class<T> clazz) {
        return mapper.convertValue(data, clazz);
    }

    public static <T> List<T> convertArray(Object data, Class<T> clazz) {
        try {
            String json = mapper.writeValueAsString(data);
            // 将 JSON 字符串转换为 JsonNode
            JsonNode jsonNode = mapper.readTree(json);
            // 然后将 JsonNode 转换为目标类型
            return mapper.convertValue(
                    jsonNode,
                    new TypeReference<List<T>>() {
                        @Override
                        public JavaType getType() {
                            return mapper.getTypeFactory().constructParametricType(List.class, clazz);
                        }
                    }
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
