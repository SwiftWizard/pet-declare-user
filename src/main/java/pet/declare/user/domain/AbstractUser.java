package pet.declare.user.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
public class AbstractUser implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    private  UUID id;
    private String email;
    private String password;
    private OffsetDateTime lastPasswordChangedTime;
    private OffsetDateTime lastActiveTime;
    private boolean emailVerified;

    @Value("password.validity.duration")
    protected String durationString;
    protected Duration passwordValidityDuration = Duration.parse(durationString);

    @Value("account.expire.threshold")
    protected String accountExpireString;
    protected Duration accountExpiresThreshold;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));

        return role;
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
        return lastActiveTime.plus(accountExpiresThreshold).isAfter(OffsetDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return emailVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return lastPasswordChangedTime.plus(passwordValidityDuration).isAfter(OffsetDateTime.now());
    }

    @Override
    public boolean isEnabled() {
        return emailVerified;
    }
}
