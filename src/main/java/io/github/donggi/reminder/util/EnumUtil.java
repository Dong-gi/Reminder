package io.github.donggi.reminder.util;

import java.util.HashMap;
import java.util.Map;

import io.github.donggi.reminder.enums.EnumValue;
import lombok.NonNull;

public class EnumUtil {
    public static <T extends Enum<T> & EnumValue> Map<Integer, T> asMap(@NonNull T sample) {
        Map<Integer, T> map = new HashMap<>();
        for (T element : (T[]) sample.getClass().getEnumConstants())
            map.put(element.getValue(), element);
        return map;
    }
}
