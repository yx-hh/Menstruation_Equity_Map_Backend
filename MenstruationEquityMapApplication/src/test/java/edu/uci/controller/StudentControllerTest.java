package edu.uci.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.uci.objects.VO.BuildingVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSearchProduct() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/search?radius=0.1&user_latitude=33.64325158034576&user_longitude=-117.84370671600364")
                        .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String stringContent = mvcResult.getResponse().getContentAsString();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BuildingVO>>(){}.getType();
        List<BuildingVO> buildingVOS = gson.fromJson(stringContent, type);

        // check size
        Assert.assertSame(buildingVOS.size(), 8);

        // check ids
        List<Integer> resultIDs = Arrays.asList(new Integer[]{43, 66, 64, 70, 20, 65, 73, 13});
        for (int i = 0; i < buildingVOS.size(); ++i) {
            Assert.assertEquals(buildingVOS.get(i).getId(), resultIDs.get(i));
        }
    }

}