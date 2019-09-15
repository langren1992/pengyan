package com.anserx.pengyan.oauth.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

/**
 * 登录用户名或密码提示信息修改
 * @author zengrui
 */
public class SelfOauthExceptionSerializer extends StdSerializer<SelfOauthException> {

    public SelfOauthExceptionSerializer() {
        super(SelfOauthException.class);
    }

    @Override
    public void serialize(SelfOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("code", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("msg", "用户名或密码错误");
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
