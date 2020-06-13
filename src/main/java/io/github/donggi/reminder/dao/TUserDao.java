package io.github.donggi.reminder.dao;

import static io.github.donggi.reminder.mapper.TUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.donggi.reminder.dto.TUser;
import io.github.donggi.reminder.mapper.TUserMapper;
import lombok.NonNull;

@Repository
public class TUserDao {

    @Autowired TUserMapper mapper;

    public int insert(TUser tUser) {
        return mapper.insert(tUser);
    }

    public boolean isUserExists(Long userId) {
        return mapper.selectByPrimaryKey(userId).isPresent();
    }

    public boolean isUserExists(@NonNull String nickname_) {
        return mapper.selectOne(x -> x.where(nickname, isEqualTo(nickname_))).isPresent();
    }

    public TUser selectByUserId(long userId) {
        return mapper.selectByPrimaryKey(userId).orElseThrow(NoSuchElementException::new);
    }

}
