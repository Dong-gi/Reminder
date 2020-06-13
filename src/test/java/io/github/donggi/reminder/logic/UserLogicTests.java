package io.github.donggi.reminder.logic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.donggi.reminder.dto.TUser;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserLogicTests {

    @Autowired
    private UserLogic userLogic;

    @Test
    void registerNewUser() throws JsonGenerationException, JsonMappingException, IOException {
        String nickname = new Date().toString();
        String pwdHash = new Date().toString();
        TUser tUser = userLogic.registerNewUser(nickname, pwdHash);
        StringWriter w = new StringWriter();
        new ObjectMapper().writeValue(w, tUser);
        log.info("New user registered : {}", w.toString());
        assertTrue(tUser.getAddDate() != null);
    }
}
