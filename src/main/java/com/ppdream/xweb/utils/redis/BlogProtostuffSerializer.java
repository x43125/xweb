package com.ppdream.xweb.utils.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ppdream.xweb.entity.Blog;
import com.ppdream.xweb.entity.User;
import org.apache.commons.lang3.SerializationException;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
public class BlogProtostuffSerializer {
    private Schema<Blog> schema = RuntimeSchema.createFrom(Blog.class);

    public String serialize(final Blog blog) {
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        try {
            byte[] bytes = serializeInternal(blog, schema, buffer);
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

    public Blog deserialize(final String byteStr) {
        byte[] bytes = new byte[byteStr.length()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) byteStr.charAt(i);
        }

        Blog blog = null;
        try {
            blog = deserializeInternal(bytes, schema.newMessage(), schema);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException();
        }
        return blog;
    }

    private <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
        return ProtostuffIOUtil.toByteArray(source, schema, buffer);
    }

    private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
}
