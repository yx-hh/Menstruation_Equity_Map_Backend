package edu.uci.controller;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import edu.uci.objects.VO.BuildingVO;
import edu.uci.objects.VO.FloorVO;
import edu.uci.objects.VO.RestroomVO;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllBuildings() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/all-buildings").accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        String stringContent = result.getResponse().getContentAsString();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<BuildingVO>>(){}.getType();
        List<BuildingVO> buildingVOS = gson.fromJson(stringContent, type);

        // check size: hard code size here
        Assert.assertEquals(buildingVOS.size(), 94);

        // check a specific building: aldrich hall
        BuildingVO aldrichHall = buildingVOS.get(1);
        Assert.assertEquals(aldrichHall.getFloors().size(), 2);
        FloorVO firstFloor = aldrichHall.getFloors().get(0);
        Assert.assertEquals(firstFloor.getName(), "1");
        RestroomVO restroom = firstFloor.getRestrooms().get(0);
        Assert.assertEquals(restroom.getRestroomId(), 2);
    }

    @Test
    void getQrCode() {
    }

}