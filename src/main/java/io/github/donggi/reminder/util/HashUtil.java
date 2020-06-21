package io.github.donggi.reminder.util;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.postgresql.util.MD5Digest;

public class HashUtil {

    private static final FastDateFormat format = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");

    public static byte[] getSalt(final byte[] source, final int length) {
        if (source.length < 3)
            throw new IllegalArgumentException("source bytes too short");
        byte[] copy = ByteBuffer.wrap(source).array();
        Random r = new Random(new String(source).hashCode());
        ArrayUtils.shuffle(copy, r);
        final byte[] salt = new byte[length];
        long x = copy[0] ^ r.nextLong(), y = copy[1] ^ r.nextLong(), z = copy[2] ^ r.nextLong();
        for (int i = 0; i < length; ++i) {
            long pattern = (x ^ (x >> 23)) ^ (z ^ (z >> 37));
            salt[i] = (byte) pattern;
            x = y;
            y = z;
            z = copy[i % copy.length] ^ r.nextLong(); 
        }
        return salt;
    }

    public static String getUserPwdHash(Long userId, String nickname, String password) {
        return new String(MD5Digest.encode(nickname.getBytes(), password.getBytes(), getSalt(ByteBuffer.allocate(Long.BYTES).putLong(userId).array(), 4)));
    }

    public static String nextToken(Long userId, Date limitDate) {
        StringBuilder builder = new StringBuilder();
        byte[] dateBytes = format.format(limitDate).getBytes();
        for (byte b : getSalt(ByteBuffer.allocate(Long.BYTES + dateBytes.length).putLong(userId).put(dateBytes).array(), 48))
            builder.append(Character.forDigit(Math.abs(b) % Character.MAX_RADIX, Character.MAX_RADIX));
        return builder.toString();
    }

    public static String nextFileName(Long userId, String fileName) {
        StringBuilder builder = new StringBuilder();
        byte[] nameBytes = (fileName + System.nanoTime()).getBytes();
        for (byte b : getSalt(ByteBuffer.allocate(Long.BYTES + nameBytes.length).putLong(userId).put(nameBytes).array(), 24))
            builder.append(Character.forDigit(Math.abs(b) % Character.MAX_RADIX, Character.MAX_RADIX));
        return builder.toString();
    }

}
