package io.github.donggi.reminder.mapper;

import static io.github.donggi.reminder.mapper.TUserReminderDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.donggi.reminder.dto.TUserReminder;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface TUserReminderMapper {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(reminderId, userId, title, attachFile, completeFlg, addDate,
            updDate);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<TUserReminder> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TUserReminder> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TUserReminderResult")
    Optional<TUserReminder> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TUserReminderResult", value = {
            @Result(column = "reminder_id", property = "reminderId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "attach_file", property = "attachFile", jdbcType = JdbcType.VARCHAR),
            @Result(column = "complete_flg", property = "completeFlg", jdbcType = JdbcType.INTEGER),
            @Result(column = "add_date", property = "addDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "upd_date", property = "updDate", jdbcType = JdbcType.TIMESTAMP) })
    List<TUserReminder> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long reminderId_) {
        return delete(c -> c.where(reminderId, isEqualTo(reminderId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TUserReminder record) {
        return MyBatis3Utils.insert(this::insert, record, TUserReminder,
                c -> c.map(reminderId).toProperty("reminderId").map(userId).toProperty("userId").map(title)
                        .toProperty("title").map(attachFile).toProperty("attachFile").map(completeFlg)
                        .toProperty("completeFlg").map(addDate).toProperty("addDate").map(updDate)
                        .toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TUserReminder> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, TUserReminder,
                c -> c.map(reminderId).toProperty("reminderId").map(userId).toProperty("userId").map(title)
                        .toProperty("title").map(attachFile).toProperty("attachFile").map(completeFlg)
                        .toProperty("completeFlg").map(addDate).toProperty("addDate").map(updDate)
                        .toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TUserReminder record) {
        return MyBatis3Utils.insert(this::insert, record, TUserReminder,
                c -> c.map(reminderId).toPropertyWhenPresent("reminderId", record::getReminderId).map(userId)
                        .toPropertyWhenPresent("userId", record::getUserId).map(title)
                        .toPropertyWhenPresent("title", record::getTitle).map(attachFile)
                        .toPropertyWhenPresent("attachFile", record::getAttachFile).map(completeFlg)
                        .toPropertyWhenPresent("completeFlg", record::getCompleteFlg).map(addDate)
                        .toPropertyWhenPresent("addDate", record::getAddDate).map(updDate)
                        .toPropertyWhenPresent("updDate", record::getUpdDate));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUserReminder> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUserReminder> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUserReminder> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUserReminder> selectByPrimaryKey(Long reminderId_) {
        return selectOne(c -> c.where(reminderId, isEqualTo(reminderId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, TUserReminder, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TUserReminder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reminderId).equalTo(record::getReminderId).set(userId).equalTo(record::getUserId).set(title)
                .equalTo(record::getTitle).set(attachFile).equalTo(record::getAttachFile).set(completeFlg)
                .equalTo(record::getCompleteFlg).set(addDate).equalTo(record::getAddDate).set(updDate)
                .equalTo(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TUserReminder record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(reminderId).equalToWhenPresent(record::getReminderId).set(userId)
                .equalToWhenPresent(record::getUserId).set(title).equalToWhenPresent(record::getTitle).set(attachFile)
                .equalToWhenPresent(record::getAttachFile).set(completeFlg).equalToWhenPresent(record::getCompleteFlg)
                .set(addDate).equalToWhenPresent(record::getAddDate).set(updDate)
                .equalToWhenPresent(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TUserReminder record) {
        return update(c -> c.set(userId).equalTo(record::getUserId).set(title).equalTo(record::getTitle).set(attachFile)
                .equalTo(record::getAttachFile).set(completeFlg).equalTo(record::getCompleteFlg).set(addDate)
                .equalTo(record::getAddDate).set(updDate).equalTo(record::getUpdDate)
                .where(reminderId, isEqualTo(record::getReminderId)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TUserReminder record) {
        return update(c -> c.set(userId).equalToWhenPresent(record::getUserId).set(title)
                .equalToWhenPresent(record::getTitle).set(attachFile).equalToWhenPresent(record::getAttachFile)
                .set(completeFlg).equalToWhenPresent(record::getCompleteFlg).set(addDate)
                .equalToWhenPresent(record::getAddDate).set(updDate).equalToWhenPresent(record::getUpdDate)
                .where(reminderId, isEqualTo(record::getReminderId)));
    }
}