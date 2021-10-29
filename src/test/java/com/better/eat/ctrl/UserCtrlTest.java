package com.better.eat.ctrl;

import com.better.eat.domain.document.UserDocument;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserCtrlTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @BeforeEach
    public void setupMockMvc(){
        //初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        UserDocument user = new UserDocument("andy");
        //session注入用户信息
        session.setAttribute("user",user);
    }



    @Test
    void addUser() throws Exception {
        System.out.println("Hello Test");
        String json = "{\n" +
                "    \"name\":\"test\",\n" +
                "    \"age\":12,\n" +
                "    \"height\":180.8,\n" +
                "    \"weight\":150.5,\n" +
                "    \"phoneNumber\":120,\n" +
                "    \"gender\":true\n" +
                "}";
        mvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json).session(session))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andDo(MockMvcResultHandlers.print()
                        );
    }

    @Test
    void updateUser() {
        List list = EasyMock.createMock(List.class);
        //录制过程
        //期望方法list.set(0,1)执行2次,返回null,不抛出异常
        expect1:EasyMock.expect(list.set(0,1)).andReturn(null).times(2);
        //期望方法list.set(0,1)执行1次,返回null,不抛出异常
        expect1:EasyMock.expect(list.set(0,1)).andReturn(null);
        //执行测试代码
        EasyMock.replay(list);
        //执行list.set(0,1),匹配expect1期望,会返回null
        Assert.assertNull(list.set(0,1));
        //执行list.set(0,1),匹配expect1(因为expect1期望执行此方法2次),会返回null
        Assert.assertNull(list.set(0,1));
        //执行list.set(0,1),匹配expect2,会返回1
        Assert.assertEquals(1,list.set(0,1));
        //验证期望
        EasyMock.verify(list);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserByConditions() {
    }
}