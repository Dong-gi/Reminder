package io.github.donggi.reminder.mapper;

import io.github.donggi.reminder.enums.CommonFlag;
import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TUserSessionDynamicSqlSupport {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TUserSession TUserSession = new TUserSession();
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = TUserSession.userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> nextToken = TUserSession.nextToken;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> tokenLimitDate = TUserSession.tokenLimitDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<CommonFlag> alwaysLoginFlg = TUserSession.alwaysLoginFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<CommonFlag> alwaysLogoutFlg = TUserSession.alwaysLogoutFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> addDate = TUserSession.addDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updDate = TUserSession.updDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TUserSession extends SqlTable {
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);
        public final SqlColumn<String> nextToken = column("next_token", JDBCType.VARCHAR);
        public final SqlColumn<Date> tokenLimitDate = column("token_limit_date", JDBCType.TIMESTAMP);
        public final SqlColumn<CommonFlag> alwaysLoginFlg = column("always_login_flg", JDBCType.INTEGER,
                "io.github.donggi.reminder.enums.EnumValueTypeHandler");
        public final SqlColumn<CommonFlag> alwaysLogoutFlg = column("always_logout_flg", JDBCType.INTEGER,
                "io.github.donggi.reminder.enums.EnumValueTypeHandler");
        public final SqlColumn<Date> addDate = column("add_date", JDBCType.TIMESTAMP);
        public final SqlColumn<Date> updDate = column("upd_date", JDBCType.TIMESTAMP);

        public TUserSession() {
            super("t_user_session");
        }
    }
}