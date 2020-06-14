package io.github.donggi.reminder.util;

import java.nio.ByteBuffer;
import java.util.Date;

import org.postgresql.util.MD5Digest;

public class HashUtil {

    public static byte[] getSalt(byte[] source, int length) {
        if (source.length < 2)
            throw new IllegalArgumentException("source bytes too short");
        final byte[] mix = new byte[] { 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };
        final byte[] salt = new byte[length];
        byte x = source[0], y = source[1], z = (source.length > 2)? source[2] : (byte) ((x ^ (x << 4)) ^ (y ^ (y >>> 4)));
        for (int i = 0; i < length; ++i) {
            int pos = Math.abs(x ^ y ^ z);
            salt[i] = (byte)(source[pos % source.length] ^ mix[pos % mix.length]);
            x = y;
            y = z;
            z = (source.length > 3 + i)? source[3+i] : (byte) ((mix[Math.abs(y) % mix.length] ^ (x << 4)) ^ (mix[Math.abs(x) % mix.length] ^ (y >>> 4))); 
        }
        return salt;
    }

    public static String getUserPwdHash(Long userId, String nickname, String password) {
        return new String(MD5Digest.encode(nickname.getBytes(), password.getBytes(), getSalt(ByteBuffer.allocate(Long.BYTES).putLong(userId).array(), 4)));
    }

    public static String nextToken(Long userId, Date limitDate) {
        StringBuilder builder = new StringBuilder();
        byte[] dateBytes = limitDate.toString().getBytes();
        for (byte b : getSalt(ByteBuffer.allocate(Long.BYTES + dateBytes.length).putLong(userId).put(dateBytes).array(), 48))
            builder.append(Character.forDigit(Math.abs(b) % Character.MAX_RADIX, Character.MAX_RADIX));
        return builder.toString();
    }

}
