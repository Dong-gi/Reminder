package io.github.donggi.reminder.mapper;

import static io.github.donggi.reminder.mapper.TUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import io.github.donggi.reminder.dto.TUser;
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
public interface TUserMapper {

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(userId, nickname, pwdHash, addDate, updDate);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<TUser> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TUser> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TUserResult")
    Optional<TUser> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TUserResult", value = {
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "pwd_hash", property = "pwdHash", jdbcType = JdbcType.VARCHAR),
            @Result(column = "add_date", property = "addDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "upd_date", property = "updDate", jdbcType = JdbcType.TIMESTAMP) })
    List<TUser> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long userId_) {
        return delete(c -> c.where(userId, isEqualTo(userId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(TUser record) {
        return MyBatis3Utils.insert(this::insert, record, TUser,
                c -> c.map(userId).toProperty("userId").map(nickname).toProperty("nickname").map(pwdHash)
                        .toProperty("pwdHash").map(addDate).toProperty("addDate").map(updDate).toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<TUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, TUser,
                c -> c.map(userId).toProperty("userId").map(nickname).toProperty("nickname").map(pwdHash)
                        .toProperty("pwdHash").map(addDate).toProperty("addDate").map(updDate).toProperty("updDate"));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(TUser record) {
        return MyBatis3Utils.insert(this::insert, record, TUser,
                c -> c.map(userId).toPropertyWhenPresent("userId", record::getUserId).map(nickname)
                        .toPropertyWhenPresent("nickname", record::getNickname).map(pwdHash)
                        .toPropertyWhenPresent("pwdHash", record::getPwdHash).map(addDate)
                        .toPropertyWhenPresent("addDate", record::getAddDate).map(updDate)
                        .toPropertyWhenPresent("updDate", record::getUpdDate));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<TUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<TUser> selectByPrimaryKey(Long userId_) {
        return selectOne(c -> c.where(userId, isEqualTo(userId_)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, TUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(TUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalTo(record::getUserId).set(nickname).equalTo(record::getNickname).set(pwdHash)
                .equalTo(record::getPwdHash).set(addDate).equalTo(record::getAddDate).set(updDate)
                .equalTo(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(TUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(userId).equalToWhenPresent(record::getUserId).set(nickname)
                .equalToWhenPresent(record::getNickname).set(pwdHash).equalToWhenPresent(record::getPwdHash)
                .set(addDate).equalToWhenPresent(record::getAddDate).set(updDate)
                .equalToWhenPresent(record::getUpdDate);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(TUser record) {
        return update(c -> c.set(nickname).equalTo(record::getNickname).set(pwdHash).equalTo(record::getPwdHash)
                .set(addDate).equalTo(record::getAddDate).set(updDate).equalTo(record::getUpdDate)
                .where(userId, isEqualTo(record::getUserId)));
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(TUser record) {
        return update(c -> c.set(nickname).equalToWhenPresent(record::getNickname).set(pwdHash)
                .equalToWhenPresent(record::getPwdHash).set(addDate).equalToWhenPresent(record::getAddDate).set(updDate)
                .equalToWhenPresent(record::getUpdDate).where(userId, isEqualTo(record::getUserId)));
    }
}