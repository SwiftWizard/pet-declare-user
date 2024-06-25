package pet.declare.user.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import pet.declare.user.controllers.restdocs.constraints.ConstrainedFields;
import pet.declare.user.domain.User;
import pet.declare.user.domain.UserMock;
import pet.declare.user.repository.UserRepository;

import java.util.Optional;

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
        mockMvc.perform(get("/users/public/greeting"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById() throws Exception{
        var mockUser = UserMock.mockUser();
        given(userRepository.findById(any(String.class))).willReturn(Optional.of(mockUser));

        ConstrainedFields fields = new ConstrainedFields(User.class);

        mockMvc.perform(get("/users/public/{id}", mockUser.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("users/get-by-id",
                        pathParameters(
                            parameterWithName("id").description("Id of user to be found.")
                        ),
                        responseFields(
                                fields.withPath("id").description("The ID of the user"),
                                fields.withPath("email").description("Users email, severs as the username in the same time"),
                                fields.withPath("lastPasswordChangedTime").description("Users last password change time"),
                                fields.withPath("lastActiveTime").description("Users last activity time"),
                                fields.withPath("emailVerified").description("Has the users email been confirmed"),
                                fields.withPath("role").description("Role of the user"),
                                fields.withPath("name").description("User given name"),
                                fields.withPath("surname").description("User given surname"),
                                fields.withPath("profileImageUrl").description("Users profile image URL, singular"),
                                fields.withPath("contactNumber").description("Users phone number"),
                                fields.subsectionWithPath("address").description("Users address")
                        )
                ));
    }
}