package pet.declare.user.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import pet.declare.user.domain.AbstractUser;
import pet.declare.user.domain.User;
import pet.declare.user.domain.UserMock;
import pet.declare.user.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@AutoConfigureDataMongo
@WebMvcTest(UserController.class)
@ComponentScan(basePackages = "pet.declare.user")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void greeting() throws Exception{
        mockMvc.perform(get("/users/greeting"))
                .andExpect(status().isOk());
    }

    @Test
    void save() {
    }

    @Test
    void getUserById() throws Exception{
        var mockUser = UserMock.mockUser();
        given(userRepository.findById(any(String.class))).willReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/{id}", mockUser.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("users/",
                        pathParameters(
                            parameterWithName("id").description("Id of user to be found.")
                        ),
                        responseFields(
                                fieldWithPath("id").description("The ID of the user"),
                                fieldWithPath("email").description("Users email, severs as the username in the same time"),
                                fieldWithPath("lastPasswordChangedTime").description("Users last password change time"),
                                fieldWithPath("lastActiveTime").description("Users last activity time"),
                                fieldWithPath("emailVerified").description("Has the users email been confirmed"),
                                fieldWithPath("role").description("Role of the user"),
                                fieldWithPath("name").description("User given name"),
                                fieldWithPath("surname").description("User given surname"),
                                fieldWithPath("profileImageUrl").description("Users profile image URL, singular"),
                                fieldWithPath("contactNumber").description("Users phone number"),
                                subsectionWithPath("address").description("Users address")
                        )
                ));
    }
}