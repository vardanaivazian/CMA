/*
package com.sflpro.cma.controller;

import com.sflpro.cma.model.Role;
import com.sflpro.cma.service.dto.UserDto;
import com.sflpro.cma.service.manager.ManagerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { MockServletContext.class })
public class ManagerControllerTest {

    private final String BASE_URL = "/api/v1/manager";

    private MockMvc mockMvc;
    private ManagerService managerService;
    private UserDto userDto;


    @Before
    public void init() {
        managerService = mock(ManagerService.class);
        mockMvc = standaloneSetup(new ManagerController(managerService)).build();
        userDto = UserDto.builder()
                .email( "test@mail.com" )
                .firstName( "Gor" )
                .lastName( "Vardanyan" )
                .password( "hreshnerigayl" )
                .passwordConfirm( "hreshnerigayl" )
                .role( Role.builder().id( 1L ).name( "ADMIN" ).build() )
                .build();
    }


    @Test
    public void createUserSuccess() throws Exception {
//        when(managerService.createUser(userDto)).thenReturn( Collections.singletonList(testUser));

        mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());

        mockMvc.perform(post(BASE_URL.concat("/user"))
                .content("{}")
                .contentType("application/json"))
                .andExpect(status().isOk());
        mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());

        final String expectedResponse = "[{\"id\":1,\"firstName\":\"Simon\",\"lastName\":\"Smith\"," +
                "\"email\":\"ssmith@gmail.com\"}]";

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

    }
}
*/
