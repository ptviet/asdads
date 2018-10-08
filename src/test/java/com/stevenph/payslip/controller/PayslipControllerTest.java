package com.stevenph.payslip.controller;

import com.stevenph.payslip.model.Employee;
import com.stevenph.payslip.service.PayslipService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayslipControllerTest {

  @Autowired
  WebApplicationContext wContext;

  @Autowired
  PayslipService payslipService;

  private MockMvc mockMvc;
  private String data = "";
  private String json = "";
  private String emp = "";
  private String response = "";

  @Before
  public void setUp() {

    mockMvc = MockMvcBuilders.webAppContextSetup(wContext)
        .alwaysDo(MockMvcResultHandlers.print())
        .build();

    data = "David,Rudd,60050,9%,01 March – 31 March\n" +
        "Ryan,Chen,120000,10%,01 March – 31 March";

    emp = "{\"firstName\":\"David\",\"lastName\":\"Rudd\",\"annualSalary\":60050,\"superRate\":9,\"paymentPeriod\":\"01 March – 31 March\"}";

    response = "{name:\"David Rudd\",payPeriod:\"01 March – 31 March\",grossIncome:5004,incomeTax:922,netIncome:4082,superAmount:450}";

    json = "[{name:\"David Rudd\",payPeriod:\"01 March – 31 March\",grossIncome:5004,incomeTax:922,netIncome:4082,superAmount:450}," +
        "{name:\"Ryan Chen\",payPeriod:\"01 March – 31 March\",grossIncome:10000,incomeTax:2669,netIncome:7331,superAmount:1000}]";
  }

  @Test
  public void generateOne() throws Exception {

    MvcResult result = mockMvc.perform(
        post("/generateOne")
        .contentType(MediaType.APPLICATION_JSON)
        .content(emp))
    .andExpect(status().isOk())
    .andReturn();

    String content = result.getResponse().getContentAsString();

    JSONAssert.assertEquals(response, content, false);
  }

  @Test
  public void generateFromFile() throws Exception {
    MockMultipartFile file = new MockMultipartFile("file", "testinput.csv", "text/plain", data.getBytes());

    mockMvc.perform(MockMvcRequestBuilders.multipart("/generateFromFile").file(file))
        .andExpect(status().isOk())
        .andExpect(content().json(json));
  }

}