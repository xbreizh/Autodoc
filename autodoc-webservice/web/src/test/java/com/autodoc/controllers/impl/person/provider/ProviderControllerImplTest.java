package com.autodoc.controllers.impl.person.provider;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.person.provider.ProviderManager;
import com.autodoc.business.impl.person.provider.ProviderManagerImpl;
import com.autodoc.controllers.contract.person.provider.ProviderController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.exceptions.CustomRestExceptionHandler;
import com.autodoc.controllers.impl.person.provider.ProviderControllerImpl;
import com.autodoc.controllers.impl.person.provider.ProviderControllerImplTest;
import com.autodoc.model.dtos.person.provider.ProviderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.ManualRestDocumentation;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(locations = "classpath:mvc-dispatcher-servlet.xml")
@WebAppConfiguration
//@Sql(scripts = "classpath:resetDb.sql")
@Transactional
class ProviderControllerImplTest {
    private static final String URL_ITEM = "/providers";
    private static final Logger LOGGER = Logger.getLogger(ProviderControllerImplTest.class);
    private static final String ENCODING = "application/json;charset=ISO-8859-1";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final int ID = 72;
    private static final String NAME = "toyota";
    private static final GsonConverter CONVERTER = new GsonConverter();
    private static final ManualRestDocumentation REST_DOCUMENTATION = new ManualRestDocumentation();
    FieldDescriptor id = fieldWithPath("id").description("Id of the provider");
    FieldDescriptor firstName = fieldWithPath("firstName").description("FirstName of the provider");
    FieldDescriptor lastName = fieldWithPath("lastName").description("LastName of the provider");
    FieldDescriptor phoneNumber = fieldWithPath("phoneNumber").description("PhoneNumber of the provider");
    FieldDescriptor address = fieldWithPath("address").description("Address of the provider");
    FieldDescriptor company = fieldWithPath("company").description("Company of the provider");
    FieldDescriptor website = fieldWithPath("website").description("Website of the provider");
    FieldDescriptor email1 = fieldWithPath("email1").description("Email1 of the provider");
    FieldDescriptor email2 = fieldWithPath("email2").description("Email2 of the provider");
    private FieldDescriptor[] descriptor = new FieldDescriptor[]{
            id, firstName, lastName, phoneNumber, address, website, company, email1, email2
    };
    private final FieldDescriptor[] descriptorInsert = new FieldDescriptor[]{
            id, firstName, lastName, phoneNumber, address.optional(), website.optional(), company.optional(), email1.optional(), email2.optional()
    };
    private final FieldDescriptor[] descriptorUpdate = new FieldDescriptor[]{
            id, id, firstName.optional(), lastName.optional(), phoneNumber.optional(), address.optional(), website.optional(), company.optional(), email1.optional(), email2.optional()
    };
    List<ProviderDTO> objList = new ArrayList<>();
    private String FIRSTNAME = "Raymond";
    private String LASTNAME = "FORCAT";
    private String ADDRESS = "49, bagger street";
    private String COMPANY = "FONSICE";
    private String WEBSITE = "FONSICE.COM";
    private String EMAIL1 = "FORCAT@FONSICE.COM";
    private String EMAIL2 = "FORCAT@FONSICE-OLD.COM";

    ProviderDTO dto = ProviderDTO.builder().firstName(FIRSTNAME).lastName(LASTNAME).phoneNumber("03938937837").address(ADDRESS).company(COMPANY)
            .website(WEBSITE).email1(EMAIL1).email2(EMAIL2).build();
    private ProviderController controller;
    private ProviderManager manager;
    private MockMvc mockMvc;
    private MockMvc mockMvcException;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        manager = mock(ProviderManagerImpl.class);
        controller = new ProviderControllerImpl(manager);
        objList.add(dto);
        this.mockMvc = this.mockMvcException = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new CustomRestExceptionHandler())
                .alwaysDo(JacksonResultHandlers.prepareJackson(MAPPER))
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8087)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .alwaysDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                //.webAppContextSetup(webApplicationContext)  // to be used for integration testing
                .alwaysDo(JacksonResultHandlers.prepareJackson(MAPPER))
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8087)
                        .and().snippets()
                        .withDefaults(CliDocumentation.curlRequest(),
                                HttpDocumentation.httpRequest(),
                                HttpDocumentation.httpResponse(),
                                AutoDocumentation.requestFields(),
                                AutoDocumentation.responseFields(),
                                AutoDocumentation.pathParameters(),
                                AutoDocumentation.requestParameters(),
                                AutoDocumentation.description(),
                                AutoDocumentation.methodAndPath(),
                                AutoDocumentation.section()))
                .alwaysDo(document("{class-name}/{method-name}",
                        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }


    @Test
    public void getAll() throws Exception {
        when(manager.getAll()).thenReturn(objList);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM)
                        .header("Authorization", "Bearer test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(ENCODING))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(
                                fieldWithPath("[]").description("An array of manufacturers"))
                                .andWithPrefix(".[]", descriptor)
                ));
        LOGGER.info("carModels: " + objList);
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(objList));
        assertEquals(response, controller.getAll());
    }


    @Test
    void getById() throws Exception {
        when(manager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/{carModelId}", ID)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(ID))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(ENCODING))
                .andDo(document("{ClassName}/{methodName}",
                        responseFields(descriptor)
                ));
        ResponseEntity response = ResponseEntity.ok(CONVERTER.convertObjectIntoGsonObject(dto));
        assertEquals(response, controller.getById(ID));

    }

    @Test
    @DisplayName("should return 404 if id is invalid")
    void getByIdError() throws Exception {
        when(manager.getById(anyInt())).thenReturn(null);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .get(URL_ITEM + "/" + ID)
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    public void getByName() throws Exception {

        when(manager.getByName(anyString())).thenReturn(dto);

        this.mockMvcException.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM + "/name")
                        .header("Authorization", "Bearer test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isMethodNotAllowed());


    }


    @Test
    @DisplayName("should add object if valid")
    void create() throws Exception {
        dto.setId(0);
        when(manager.getById(anyInt())).thenReturn(dto);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .post(URL_ITEM)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated())
                .andDo(document("{ClassName}/{methodName}",
                        requestFields(descriptorInsert)
                ));

    }

    @Test
    @DisplayName("should update object if valid")
    void update() throws Exception {
        when(manager.update(any())).thenReturn(true);
        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .put(URL_ITEM)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk()).andDo(document("{ClassName}/{methodName}",
                requestFields(descriptorUpdate)
        ));
    }

    @Test
    @DisplayName("should delete object if valid")
    void delete() throws Exception {
        when(manager.deleteById(anyInt())).thenReturn(true);

        this.mockMvc.perform(
                RestDocumentationRequestBuilders
                        .delete(URL_ITEM + "/" + ID)
                        .header("Authorization", "Bearer test")
                        .content(CONVERTER.convertObjectIntoGsonObject(dto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isNoContent())
        ;
    }

}