package io.github.donggi.reminder.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.donggi.reminder.enums.CommonFlag;

@SpringBootTest
public class EnumUtilTests {
    @Test
    void asMapWork() {
        Map<Integer, CommonFlag> map = EnumUtil.asMap(CommonFlag.ON);
        assertTrue(map.size() == 2);
        assertTrue(map.get(0) == CommonFlag.OFF);
        assertTrue(map.get(1) == CommonFlag.valueOf(1));
    }    
}
