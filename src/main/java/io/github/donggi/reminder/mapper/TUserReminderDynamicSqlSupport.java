package io.github.donggi.reminder.mapper;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import io.github.donggi.reminder.enums.CommonFlag;

public final class TUserReminderDynamicSqlSupport {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final TUserReminder TUserReminder = new TUserReminder();
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> reminderId = TUserReminder.reminderId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Long> userId = TUserReminder.userId;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> title = TUserReminder.title;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> attachFile = TUserReminder.attachFile;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<CommonFlag> completeFlg = TUserReminder.completeFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<CommonFlag> delFlg = TUserReminder.delFlg;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> addDate = TUserReminder.addDate;
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> updDate = TUserReminder.updDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class TUserReminder extends SqlTable {
        public final SqlColumn<Long> reminderId = column("reminder_id", JDBCType.BIGINT);
        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);
        public final SqlColumn<String> title = column("title", JDBCType.VARCHAR);
        public final SqlColumn<String> attachFile = column("attach_file", JDBCType.VARCHAR);
        public final SqlColumn<CommonFlag> completeFlg = column("complete_flg", JDBCType.INTEGER,
                "io.github.donggi.reminder.enums.EnumValueTypeHandler");
        public final SqlColumn<CommonFlag> delFlg = column("del_flg", JDBCType.INTEGER,
                "io.github.donggi.reminder.enums.EnumValueTypeHandler");
        public final SqlColumn<Date> addDate = column("add_date", JDBCType.TIMESTAMP);
        public final SqlColumn<Date> updDate = column("upd_date", JDBCType.TIMESTAMP);

        public TUserReminder() {
            super("t_user_reminder");
        }
    }
}