package com.autodoc.controllers.impl.car;

import com.autodoc.business.contract.car.ManufacturerManager;
import com.autodoc.controllers.contract.car.ManufacturerController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.dao.filler.Filler;
import com.autodoc.model.dtos.car.ManufacturerDTO;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
class ManufacturerControllerImplTest {
    private final static String encoding = "application/json;charset=ISO-8859-1";
    private static final Logger LOGGER = Logger.getLogger(ManufacturerControllerImplTest.class);
    private ManufacturerController manufacturerController;
    private ManufacturerManager manufacturerManager;
    private GsonConverter converter;
    private MockMvc mockMvc;
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            fieldWithPath("id").description("Id of the manufacturer"),
            fieldWithPath("name").description("Name of the manufacturer")
    };
    private List<ManufacturerDTO> manufacturers = new ArrayList<>();
    String urlItem = "/manufacturers";
    private ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
    @Inject
    private Filler filler;


    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext,
               RestDocumentationContextProvider restDocumentation) throws Exception {
        manufacturerManager = mock(ManufacturerManager.class);
        manufacturerController = new ManufacturerControllerImpl(manufacturerManager);
        /*this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation).uris().withPort(8087))
                .build();*/
        //manufacturerDTO.setPhoneNumber2("12345");
        manufacturers.add(manufacturerDTO);
        converter = new GsonConverter();
        // using standalone
        this.mockMvc = MockMvcBuilders.standaloneSetup(manufacturerController).apply(documentationConfiguration(restDocumentation).uris().withPort(8087)).build();
        manufacturerDTO.setId(3);
        manufacturerDTO.setName("TOYOTA");

    }


    @Test
    void getAll() throws Exception {
        when(manufacturerManager.getAll()).thenReturn(manufacturers);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(urlItem)
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=ISO-8859-1"))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));

        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(manufacturers));
        assertEquals(response, manufacturerController.getAll());
    }

    @Test
    public void getByName() throws Exception {
        String name = manufacturerDTO.getName();
        int id = 3;
        when(manufacturerManager.getByName(anyString())).thenReturn(manufacturerDTO);

        this.mockMvc.perform(
                get(urlItem + "/name?name=" + name)
                        .header("Authorization", "Bearer token")
                        .content(converter.convertObjectIntoGsonObject(name))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(encoding))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").value(id))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(converter.convertObjectIntoGsonObject(manufacturerDTO));
        assertEquals(response, manufacturerController.getByName("sdsdsdsd"));
    }
}