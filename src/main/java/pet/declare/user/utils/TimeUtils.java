package pet.declare.user.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class TimeUtils {

    @Value("${server.timezone}")
    public static String SERVER_TIME_ZONE;
    public static Duration PASSWORD_EXPIRES_DURATION;
    public static Duration ACCOUNT_EXPIRES_DURATION;
    @Value("${credentials.expire.threshold}")
    private void setPasswordExpiresDuration(String duration){
        PASSWORD_EXPIRES_DURATION = Duration.parse(duration);
    }
    @Value("${account.expire.threshold}")
    private void setAccountExpiresDuration(String duration){
        ACCOUNT_EXPIRES_DURATION = Duration.parse(duration);
    }
    public static ZonedDateTime currentServerZonedTime(){
        return ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(SERVER_TIME_ZONE));
    }
}
