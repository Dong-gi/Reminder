package io.github.donggi.reminder.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class TUserDynamicSqlSupport {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TUser TUser = new TUser();
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = TUser.userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> nickname = TUser.nickname;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> pwdHash = TUser.pwdHash;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> addDate = TUser.addDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updDate = TUser.updDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TUser extends SqlTable {
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);
        public final SqlColumn<String> nickname = column("nickname", JDBCType.VARCHAR);
        public final SqlColumn<String> pwdHash = column("pwd_hash", JDBCType.VARCHAR);
        public final SqlColumn<Date> addDate = column("add_date", JDBCType.TIMESTAMP);
        public final SqlColumn<Date> updDate = column("upd_date", JDBCType.TIMESTAMP);

        public TUser() {
            super("t_user");
        }
    }
}