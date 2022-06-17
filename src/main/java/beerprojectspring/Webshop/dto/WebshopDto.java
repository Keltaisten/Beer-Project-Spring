package beerprojectspring.Webshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WebshopDto {
    private String name;
    private String emailAddress;
    private List<String> beerNames = new ArrayList<>();

    public WebshopDto(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebshopDto that = (WebshopDto) o;
        return Objects.equals(name, that.name) && Objects.equals(emailAddress, that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailAddress);
    }
}
