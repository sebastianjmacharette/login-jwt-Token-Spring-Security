package ar.com.macharette.sistema.Auth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRerquest {

    String username;
    String password;
    String firstname;
    String lastname;
    String country;
}
