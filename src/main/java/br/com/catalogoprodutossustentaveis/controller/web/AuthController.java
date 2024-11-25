package br.com.catalogoprodutossustentaveis.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.catalogoprodutossustentaveis.util.JwtUtil;

@Controller
@RequestMapping("/web")
public class AuthController {

    private final PasswordEncoder passwordEncoder;

    public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/login")
    public String loginPage() {
    	JwtUtil.passwordGenerate();
        return "login"; // Nome da página HTML no diretório templates
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = "admin"; // Mocked user
        String password = "$2a$10$Or.88gyxxUuhD5ur.tGTUug5vRcQXpsyvA2RU3Pi.Q/pZFaNpd2cC"; // Mocked password

        if (username.equals(loginRequest.getUsername()) && passwordEncoder.matches(loginRequest.getPassword(), passwordEncoder.encode(password))) {
            String token = JwtUtil.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}

class LoginRequest {
    private String username;
    private String password;
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}