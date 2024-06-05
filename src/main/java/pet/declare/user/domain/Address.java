package pet.declare.user.domain;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Address {
    protected String country;
    protected String state;
    protected String postalCode;
    protected String city;
    private Set<String> addressLines = new HashSet<>();

    public void addAddress(String address){
        addressLines.add(address);
    }
}
