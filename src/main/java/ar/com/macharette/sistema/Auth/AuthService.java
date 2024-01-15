package ar.com.macharette.sistema.Auth;

import ar.com.macharette.sistema.JWT.JwtService;
import ar.com.macharette.sistema.User.Role;
import ar.com.macharette.sistema.User.User;
import ar.com.macharette.sistema.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }



    public AuthResponse register(RegisterRerquest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // Cifrar la contraseña
                .lastname(request.getLastname())
                .firstname(request.getFirstname())
                .Country(request.getCountry())
                .role(Role.USER)
                .build();

        //invocar al repositorio para que se cree un nuevo usuario
        userRepository.save(user);

        return  AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();




    }
}
