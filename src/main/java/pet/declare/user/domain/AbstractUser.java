package pet.declare.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pet.declare.user.utils.TimeUtils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "user_collection")
public abstract class AbstractUser implements UserDetails {

    @Transient
    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    protected String id;
    private String email;
    private String password;
    private LocalDateTime lastPasswordChangedTime;
    private LocalDateTime lastActiveTime;
    private boolean emailVerified;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return lastActiveTime.plus(TimeUtils.ACCOUNT_EXPIRES_DURATION).isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return emailVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return lastPasswordChangedTime.plus(TimeUtils.PASSWORD_EXPIRES_DURATION).isAfter(LocalDateTime.now());
    }
    @Override
    public boolean isEnabled() {
        return emailVerified;
    }
}
