package io.github.donggi.reminder.enums;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.donggi.reminder.util.EnumUtil;

public enum ApiResultCode implements EnumValue {

    /** 정상 */
    OK(200),
    /** 입력 불량 */
    BAD_REQUEST(400),
    /** 알 수 없는 서버 에러 */
    INTERNAL_ERROR(500),

    /** 1??? : 인증 관련 오류 */
    /** 1000 : 유저 특정 불가 */
    NO_SUCH_USER(1000),
    /** 1001 : userId 없음 */
    NO_USER_ID(1001),
    /** 1002 : requestToken 없음 */
    NO_REQUEST_TOKEN(1002),
    /** 1003 : requestToken 만료 */
    REQUEST_TOKEN_EXPIRED(1003),
    /** 1004 : nickname 중복 */
    NICKNAME_ALREADY_EXISTS(1004),
    ;


    private final int value;
    private static final Map<Integer, ApiResultCode> MAP = EnumUtil.asMap(OK);


    @JsonCreator
    public static ApiResultCode valueOf(int value) {
        return MAP.get(value);
    }

    private ApiResultCode(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
