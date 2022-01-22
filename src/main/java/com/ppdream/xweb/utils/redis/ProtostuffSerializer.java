package com.ppdream.xweb.utils.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ppdream.xweb.entity.WebUser;
import org.apache.commons.lang3.SerializationException;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
public class ProtostuffSerializer {
    private Schema<WebUser> schema = RuntimeSchema.createFrom(WebUser.class);

    public String serialize(final WebUser user) {
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            byte[] bytes = serializeInternal(user, schema, buffer);
            StringBuilder sb = new StringBuilder(bytes.length);
            for (byte aByte : bytes) {
                sb.append(aByte);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException();
        } finally {
            buffer.clear();
        }
    }

    public WebUser deserialize(final String byteStr) {
        byte[] bytes = new byte[byteStr.length()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) byteStr.charAt(i);
        }

        WebUser user = null;
        try {
            user = deserializeInternal(bytes, schema.newMessage(), schema);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException();
        }
        return user;
    }

    private <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
        return ProtostuffIOUtil.toByteArray(source, schema, buffer);
    }

    private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
}
