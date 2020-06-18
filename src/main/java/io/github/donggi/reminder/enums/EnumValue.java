package io.github.donggi.reminder.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface EnumValue {
    @JsonValue
    int getValue();
}
