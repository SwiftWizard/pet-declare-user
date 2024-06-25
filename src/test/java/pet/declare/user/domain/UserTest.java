package pet.declare.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pet.declare.user.utils.TimeUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/*
    * Currently without avoiding the spin-up of spring-context
 */

@SpringBootTest
@ContextConfiguration(classes = TimeUtils.class)
class UserTest{
    private User testSubject;
    @BeforeEach
    void setUp() {
        testSubject = User.builder()
                .lastPasswordChangedTime(LocalDateTime.now().minusMonths(3))
                .lastActiveTime(LocalDateTime.now().minusDays(3))
                .email("test@mail.com")
                .password("password")
                .emailVerified(true)
                .role(User.DEFAULT_USER_ROLE)
                .build();
    }


    @Test
    void testGetAuthorities() {
        assertEquals(testSubject.getAuthorities().size(), 1);
        assertEquals(AbstractUser.ROLE_PREFIX + testSubject.getRole(), testSubject.getAuthorities().iterator().next().getAuthority() );
    }

    @Test
    void testGetUsername() {
        assertEquals(testSubject.getEmail(), testSubject.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        assertTrue(testSubject.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertTrue(testSubject.isAccountNonExpired());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertTrue(testSubject.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
    }
}