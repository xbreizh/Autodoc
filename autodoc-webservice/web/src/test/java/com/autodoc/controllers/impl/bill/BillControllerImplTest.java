package com.autodoc.controllers.impl.bill;

import capital.scalable.restdocs.AutoDocumentation;
import capital.scalable.restdocs.jackson.JacksonResultHandlers;
import com.autodoc.business.contract.bill.BillManager;
import com.autodoc.business.exceptions.InvalidDtoException;
import com.autodoc.controllers.contract.bill.BillController;
import com.autodoc.controllers.helper.GsonConverter;
import com.autodoc.controllers.impl.exceptions.CustomRestExceptionHandler;
import com.autodoc.model.dtos.bill.BillDTO;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
//@Transactional
class BillControllerImplTest {
    private static final Logger LOGGER = Logger.getLogger(BillControllerImplTest.class);
    private static final String ENCODING = "application/json;charset=ISO-8859-1";
    FieldDescriptor id = fieldWithPath("id").description("Id of the bill");
    FieldDescriptor registration = fieldWithPath("registration").description("Id of the bill");
    FieldDescriptor clientId = fieldWithPath("clientId").description("Id of the client");
    FieldDescriptor employeeId = fieldWithPath("employeeId").description("Id of the employee");
    FieldDescriptor paymentType = fieldWithPath("paymentType").description("Payment Type");
    FieldDescriptor discount = fieldWithPath("discount").description("Discount");
    FieldDescriptor pieces = fieldWithPath("pieces").description("pieces of the bill");
    FieldDescriptor tasks = fieldWithPath("tasks").description("Tasks of the bill");
    FieldDescriptor status = fieldWithPath("status").description("Status of the bill");
    FieldDescriptor dateReparation = fieldWithPath("dateReparation").description("Date planned for Reparation");
    FieldDescriptor comments = fieldWithPath("comments").description("Comments");
    FieldDescriptor total = fieldWithPath("total").description("Total amount of the bill");


    private final FieldDescriptor[] descriptor = new FieldDescriptor[]{
            id,registration, clientId, employeeId, paymentType, discount, pieces, tasks, status, dateReparation, comments, total
    };
    private final FieldDescriptor[] descriptorInsert = new FieldDescriptor[]{
            id,registration, clientId, employeeId, paymentType, discount, pieces, tasks, status, dateReparation, comments, total
    };
    private final FieldDescriptor[] descriptorUpdate = new FieldDescriptor[]{
            id,
            registration.optional(),
            clientId.optional(), employeeId.optional(), paymentType.optional(), discount.optional(), pieces.optional(), tasks.optional(), status.optional(),
            dateReparation.optional(), comments.optional(), total.optional()

    };
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String URL_ITEM = "/bills";
    private static final int ID = 29;
    private static final int EMPLOYEE_ID = 2;
    private static final String REGISTRATION = "ABC12345";
    private static final String COMMENTS = "I am a comment";
    private static final String DATE_REPARATION = new Date().toString();
    private static final double DISCOUNT = 10;
    private static final String PAYMENT_TYPE = "CASH";
    private static final String STATUS = "ABC12345";
    private static final List<Integer> PIECES = Arrays.asList(1, 3, 4);
    private static final List<Integer> TASKS = Arrays.asList(14, 33, 42);
    private static final double TOTAL = 127.25;

    private static final GsonConverter CONVERTER = new GsonConverter();
    private static final ManualRestDocumentation REST_DOCUMENTATION = new ManualRestDocumentation();

    private BillController controller;
    private BillManager manager;
    private MockMvc mockMvc;
    private MockMvc mockMvcException;

    List<BillDTO> objList = new ArrayList<>();
    BillDTO dto;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        dto = BillDTO.builder().id(1).registration("05D121487").clientId(1).comments(COMMENTS).dateReparation(DATE_REPARATION)
                .discount(DISCOUNT).employeeId(EMPLOYEE_ID).paymentType(PAYMENT_TYPE).pieces(PIECES).tasks(TASKS).status(STATUS).total(TOTAL).build();
        objList.add(dto);
        manager = mock(BillManager.class);
        controller = new BillControllerImpl(manager);
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
