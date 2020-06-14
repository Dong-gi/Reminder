package io.github.donggi.reminder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.donggi.reminder.dto.TUserSession;
import io.github.donggi.reminder.mapper.TUserSessionMapper;

@Repository
public class TUserSessionDao {

    @Autowired TUserSessionMapper mapper;

    public TUserSession select(Long userId) {
        return mapper.selectByPrimaryKey(userId).get();
    }

    public int upsert(TUserSession tUserSession) {
        if (mapper.selectByPrimaryKey(tUserSession.getUserId()).isPresent())
            return mapper.updateByPrimaryKey(tUserSession);
        return mapper.insert(tUserSession);
    }

}
