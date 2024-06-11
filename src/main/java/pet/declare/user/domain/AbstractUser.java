package pet.declare.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pet.declare.user.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@Document(collection = "user_collection")
public abstract class AbstractUser implements UserDetails {

    @Transient
    public static final String ROLE_PREFIX = "ROLE_";

    @Id
    protected String id;

    @NotBlank(message = "Email required")
    private String email;

    @JsonIgnore
    @Size(min = 7, message = "Password too short")
    @NotBlank(message = "Password required")
    private String password;

    private LocalDateTime lastPasswordChangedTime;
    private LocalDateTime lastActiveTime;
    private boolean emailVerified;
    private String role;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return roles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return lastActiveTime.plus(TimeUtils.ACCOUNT_EXPIRES_DURATION).isAfter(LocalDateTime.now());
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return emailVerified;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return lastPasswordChangedTime.plus(TimeUtils.PASSWORD_EXPIRES_DURATION).isAfter(LocalDateTime.now());
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return emailVerified;
    }
}
