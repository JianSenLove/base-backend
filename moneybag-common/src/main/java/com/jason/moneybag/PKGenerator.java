package com.jason.moneybag;

import java.security.SecureRandom;
import java.util.UUID;

public class PKGenerator {
    // Base62 字符集
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // SecureRandom 提供更强的随机性
    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成一个 Base62 编码的随机主键
     * @return Base62 编码的主键字符串
     */
    public synchronized static String generateKey() {
        return Base62.encode(asByteArray(UUID.randomUUID()));
    }

    /**
     * 将 UUID 转换为字节数组
     * @param uuid UUID 对象
     * @return UUID 转换后的 16 字节数组
     */
    private static byte[] asByteArray(UUID uuid) {
        long msb = uuid.getMostSignificantBits();  // 获取前 64 位
        long lsb = uuid.getLeastSignificantBits(); // 获取后 64 位
        byte[] buffer = new byte[16];

        // 高 64 位放入前 8 字节
        for (int i = 0; i < 8; i++) {
            buffer[i] = (byte) (msb >>> 8 * (7 - i));
        }

        // 低 64 位放入后 8 字节
        for (int i = 8; i < 16; i++) {
            buffer[i] = (byte) (lsb >>> 8 * (15 - i));
        }

        return buffer;
    }

    /**
     * Base62 编码类，将字节数组转换为 Base62 编码的字符串
     */
    private static class Base62 {

        public static String encode(byte[] input) {
            StringBuilder result = new StringBuilder();
            for (byte b : input) {
                result.append(BASE62.charAt(b & 0x3F));  // 6 位一组进行编码
            }
            return result.toString();
        }
    }

    // 如果需要生成带前缀或自定义长度的主键，也可以扩展下面的代码
    /**
     * 生成带前缀的主键
     * @param prefix 主键前缀
     * @return 带前缀的主键
     */
    public synchronized static String generateKeyWithPrefix(String prefix) {
        return prefix + generateKey();
    }
}
