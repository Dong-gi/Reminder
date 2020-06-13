package io.github.donggi.reminder.mapper;

import static io.github.donggi.reminder.mapper.TUserSessionDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.donggi.reminder.dto.TUserSession;
import io.github.donggi.reminder.enums.EnumValueTypeHandler;
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
public interface TUserSessionMapper {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(userId, nextToken, tokenLimitDate, alwaysLoginFlg,
            alwaysLogoutFlg, addDate, updDate);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<TUserSession> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TUserSession> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TUserSessionResult")
    Optional<TUserSession> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TUserSessionResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "next_token", property = "nextToken", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token_limit_date", property = "tokenLimitDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "always_login_flg", property = "alwaysLoginFlg", typeHandler = EnumValueTypeHandler.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "always_logout_flg", property = "alwaysLogoutFlg", typeHandler = EnumValueTypeHandler.class, jdbcType = JdbcType.INTEGER),
            @Result(column = "add_date", property = "addDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "upd_date", property = "updDate", jdbcType = JdbcType.TIMESTAMP) })
    List<TUserSession> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long userId_) {
        return delete(c -> c.where(userId, isEqualTo(userId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TUserSession record) {
        return MyBatis3Utils.insert(this::insert, record, TUserSession,
                c -> c.map(userId).toProperty("userId").map(nextToken).toProperty("nextToken").map(tokenLimitDate)
                        .toProperty("tokenLimitDate").map(alwaysLoginFlg).toProperty("alwaysLoginFlg")
                        .map(alwaysLogoutFlg).toProperty("alwaysLogoutFlg").map(addDate).toProperty("addDate")
                        .map(updDate).toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TUserSession> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, TUserSession,
                c -> c.map(userId).toProperty("userId").map(nextToken).toProperty("nextToken").map(tokenLimitDate)
                        .toProperty("tokenLimitDate").map(alwaysLoginFlg).toProperty("alwaysLoginFlg")
                        .map(alwaysLogoutFlg).toProperty("alwaysLogoutFlg").map(addDate).toProperty("addDate")
                        .map(updDate).toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TUserSession record) {
        return MyBatis3Utils.insert(this::insert, record, TUserSession,
                c -> c.map(userId).toPropertyWhenPresent("userId", record::getUserId).map(nextToken)
                        .toPropertyWhenPresent("nextToken", record::getNextToken).map(tokenLimitDate)
                        .toPropertyWhenPresent("tokenLimitDate", record::getTokenLimitDate).map(alwaysLoginFlg)
                        .toPropertyWhenPresent("alwaysLoginFlg", record::getAlwaysLoginFlg).map(alwaysLogoutFlg)
                        .toPropertyWhenPresent("alwaysLogoutFlg", record::getAlwaysLogoutFlg).map(addDate)
                        .toPropertyWhenPresent("addDate", record::getAddDate).map(updDate)
                        .toPropertyWhenPresent("updDate", record::getUpdDate));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUserSession> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUserSession> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUserSession> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUserSession> selectByPrimaryKey(Long userId_) {
        return selectOne(c -> c.where(userId, isEqualTo(userId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, TUserSession, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TUserSession record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId).set(nextToken).equalTo(record::getNextToken)
                .set(tokenLimitDate).equalTo(record::getTokenLimitDate).set(alwaysLoginFlg)
                .equalTo(record::getAlwaysLoginFlg).set(alwaysLogoutFlg).equalTo(record::getAlwaysLogoutFlg)
                .set(addDate).equalTo(record::getAddDate).set(updDate).equalTo(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TUserSession record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId).set(nextToken)
                .equalToWhenPresent(record::getNextToken).set(tokenLimitDate)
                .equalToWhenPresent(record::getTokenLimitDate).set(alwaysLoginFlg)
                .equalToWhenPresent(record::getAlwaysLoginFlg).set(alwaysLogoutFlg)
                .equalToWhenPresent(record::getAlwaysLogoutFlg).set(addDate).equalToWhenPresent(record::getAddDate)
                .set(updDate).equalToWhenPresent(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TUserSession record) {
        return update(c -> c.set(nextToken).equalTo(record::getNextToken).set(tokenLimitDate)
                .equalTo(record::getTokenLimitDate).set(alwaysLoginFlg).equalTo(record::getAlwaysLoginFlg)
                .set(alwaysLogoutFlg).equalTo(record::getAlwaysLogoutFlg).set(addDate).equalTo(record::getAddDate)
                .set(updDate).equalTo(record::getUpdDate).where(userId, isEqualTo(record::getUserId)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TUserSession record) {
        return update(c -> c.set(nextToken).equalToWhenPresent(record::getNextToken).set(tokenLimitDate)
                .equalToWhenPresent(record::getTokenLimitDate).set(alwaysLoginFlg)
                .equalToWhenPresent(record::getAlwaysLoginFlg).set(alwaysLogoutFlg)
                .equalToWhenPresent(record::getAlwaysLogoutFlg).set(addDate).equalToWhenPresent(record::getAddDate)
                .set(updDate).equalToWhenPresent(record::getUpdDate).where(userId, isEqualTo(record::getUserId)));
    }
}