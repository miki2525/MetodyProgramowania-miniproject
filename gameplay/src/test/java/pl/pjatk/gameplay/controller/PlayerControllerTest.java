package pl.pjatk.gameplay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {



    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @BeforeEach
    void cleanUp(){
        playerService.deleteAll();
    }

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/player")).//andDo(print()).
        andExpect(status().isOk());
    }

    @Test
    void shouldMatchHandlerforHelloWorld() throws Exception {
        mockMvc.perform(get("/player/hello")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(equalTo("Hello world")));

    }

    void shouldMatchHandlerforID() throws Exception {
        Player player = playerService.save(new Player("nickname", 200, 100));
        mockMvc.perform(get("/1")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(equalTo(player.toString())));//jsona wstawic
    }


    void shouldMatchHandlerfornotID() throws Exception {
        mockMvc.perform(get("/1")).
                andDo(print()).
                andExpect(status().isNotFound());
    }

}