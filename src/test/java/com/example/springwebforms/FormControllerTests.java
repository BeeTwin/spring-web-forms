package com.example.springwebforms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("test_user")
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-forms-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(value = {"/create-forms-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void user403() throws Exception {
        this.mockMvc.perform(get("/form"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithUserDetails("test_admin")
    @Sql(value = {"/create-admin-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-forms-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(value = {"/create-forms-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void adminOk() throws Exception {
        this.mockMvc.perform(get("/form"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test_admin")
    @Sql(value = {"/create-admin-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-forms-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(value = {"/create-forms-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void formListShowCorrectResult() throws Exception {
        this.mockMvc.perform(get("/form"))
                .andDo(print())
                .andExpect(xpath("//*[@id=\"row\"]").nodeCount(1))
                .andExpect(content().string(containsString("???????????????? ??????????")));
    }
}
