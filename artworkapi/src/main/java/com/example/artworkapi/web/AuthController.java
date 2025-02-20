package com.example.artworkapi.web;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.artworkapi.TokenGenerator;
import com.example.artworkapi.domain.AppUser;
import com.example.artworkapi.domain.ForgotPasswordRequest;
import com.example.artworkapi.domain.LoginRequest;
import com.example.artworkapi.domain.LoginResponse;
import com.example.artworkapi.domain.ResetPasswordRequest;
import com.example.artworkapi.domain.SignupRequest;
import com.example.artworkapi.model.AppUserRepository;
import com.example.artworkapi.service.EmailService;
import com.example.artworkapi.service.GoogleTokenVerifierService;
import com.example.artworkapi.service.JwtService;


//This class handles authentication-related HTTP requests
@Controller
//Base URL path for all endpoints in this controller
@RequestMapping("/api/auth")
public class AuthController {

	 // Repository for user data operations
	 @Autowired
	 private AppUserRepository userRepository;
	
	 // Service for verifying Google tokens
	 @Autowired
	 private GoogleTokenVerifierService googleTokenVerifierService;
	
	 // Service for sending emails
	 @Autowired
	 private EmailService emailService;
	
	 // Service for JWT operations
	 private final JwtService jwtService = new JwtService();
	
	 // Logger for this class
	 private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	 // Service for password encoding
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	 /**
	  * Handles forgot password requests
	  * @param request Contains the user's email
	  * @return ResponseEntity with the result of the operation
	  */
	 @PostMapping("/forgot-password")
	 public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
	     // Find user by email
	     Optional<AppUser> userOptional = userRepository.findByEmail(request.getEmail());
	     if (userOptional.isEmpty()) {
	         return ResponseEntity.badRequest().body("No user found with this email.");
	     }
	
	     AppUser user = userOptional.get();
	     // Generate a unique token for password reset
	     String token = TokenGenerator.generateToken();
	     user.setPasswordResetToken(token);
	     // Set token expiration to 15 minutes from now
	     user.setPasswordResetTokenExpiration(LocalDateTime.now().plus(Duration.ofMinutes(15)));
	     userRepository.save(user);
	
	     // Create the reset password link
	     String resetLink = "https://artworkdockerimage-v1.onrender.com/api/auth/reset-password?token=" + token;
	    // String resetLink = "http://192.168.68.118:8686/api/auth/reset-password?token=" + token;
	     
	     // Prepare email content
	     String content = "To reset your password, please click the following link: <a href=\"" + resetLink + "\">Reset Password</a>";
	
	     // Set sender email ( the user's email)
	     String senderEmail = "mariahacs10@yahoo.com";
	
	     // Send the password reset email
	     emailService.sendEmail(user.getEmail(), "Password Reset Request", content, senderEmail);
	
	     return ResponseEntity.ok("Password reset email sent.");
	 }
	
	 /**
	  * Displays the reset password page
	  * @param token The reset token
	  * @param model The Spring MVC Model
	  * @return The name of the view to render
	  */
	 @GetMapping("/reset-password")
	 public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
	     model.addAttribute("token", token);
	     return "reset-password";
	 }
	
	 /**
	  * Handles password reset requests
	  * @param request Contains the reset token and new password
	  * @return ResponseEntity with the result of the operation
	  */
	 @PostMapping("/reset-password")
	   public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
	       // Find user by reset token
	       Optional<AppUser> userOptional = userRepository.findByPasswordResetToken(request.getToken());

	       if (userOptional.isEmpty()) {
	           return ResponseEntity.badRequest().body("Invalid reset token.");
	       }

	       AppUser user = userOptional.get();

	       // Check if the token has expired
	       LocalDateTime now = LocalDateTime.now();
	       if (user.getPasswordResetTokenExpiration() != null && now.isAfter(user.getPasswordResetTokenExpiration())) {
	           return ResponseEntity.badRequest().body("Reset token has expired.");
	       }

	       // Check if passwords match
	       if (!request.getNewPassword().equals(request.getConfirmPassword())) {
	           return ResponseEntity.badRequest().body("Passwords do not match.");
	       }

	       // Validate password strength
	       if (!isPasswordStrong(request.getNewPassword())) {
	           return ResponseEntity.badRequest().body("Password does not meet strength requirements.");
	       }

	       // Update user's password and clear reset token data
	       user.setPassword(passwordEncoder.encode(request.getNewPassword()));
	       user.setPasswordResetToken(null);
	       user.setPasswordResetTokenExpiration(null);
	       userRepository.save(user);

	       return ResponseEntity.ok("Password has been reset successfully.");
	   }

	   private boolean isPasswordStrong(String password) {
	       // Implement password strength checks here
	       // For example: minimum length, contains uppercase, lowercase, number, special character
	       return password.length() >= 8 &&
	              password.matches(".*[A-Z].*") &&
	              password.matches(".*[a-z].*") &&
	              password.matches(".*\\d.*") &&
	              password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
	   }
	   
	   @PostMapping("/signup")
	   public ResponseEntity<Map<String, String>> registerUser(@RequestBody SignupRequest signupRequest) {
	       Map<String, String> response = new HashMap<>();

	       // Check if a user with the provided username already exists
	       if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
	           response.put("error", "Error: Username is already taken!");
	           return ResponseEntity.badRequest().body(response);
	       }

	       // Check if a user with the provided email already exists
	       if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
	           response.put("error", "Error: Email is already taken!");
	           return ResponseEntity.badRequest().body(response);
	       }

	       // Create a new AppUser object with the provided username, encoded password, email, and default role
	       AppUser user = new AppUser(
	           signupRequest.getUsername(),
	           passwordEncoder.encode(signupRequest.getPassword()),
	           signupRequest.getEmail(),
	           "USER" // Default role
	       );

	       // Save the new user to the database
	       userRepository.save(user);

	       logger.info("Received signup request for username: {}", signupRequest.getUsername());

	       // Return a success response
	       response.put("message", "User registered successfully!");
	       response.put("userId", String.valueOf(user.getId())); // Include the user ID if needed
	       return ResponseEntity.ok(response);
	   }

	   
    // This method handles POST requests to /api/auth/signup
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
//        // Checks if a user with the provided username already exists
//        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
//            return ResponseEntity.badRequest().body("Error: Username is already taken!");
//        }
//
//        // Checks if a user with the provided email already exists
//        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().body("Error: Email is already taken!");
//        }
//
//        // It creates a new AppUser object with the provided username, encoded password, email, and default role
//        AppUser user = new AppUser(
//            signupRequest.getUsername(),
//            passwordEncoder.encode(signupRequest.getPassword()),
//            //adding the email to the database
//            signupRequest.getEmail(),
//            "USER" // Default role
//        );
//
//        // Saves the new user to the database
//        userRepository.save(user);
//        
//        logger.info("Received signup request for username: {}", signupRequest.getUsername());
//
//        return ResponseEntity.ok("User registered successfully!");
//    }
//    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Find user by username
        AppUser user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);

        // Check if user exists and password matches
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // Generate JWT token using JwtService
            String jwtToken = jwtService.generateToken(user); // Assuming JwtService has a generateToken method

            // Return the token and userId in the response
            return ResponseEntity.ok(new LoginResponse(jwtToken, user.getId())); // Return token and userId
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
    
//    @PostMapping("/google-signin")
//    public ResponseEntity<?> googleSignIn(@RequestBody GoogleSignInRequest googleSignInRequest) {
//        try {
//            logger.info("Received Google Sign-In request with token: {}", googleSignInRequest.getIdToken());
//
//            GoogleIdToken idToken = googleTokenVerifierService.verifyToken(googleSignInRequest.getIdToken());
//            if (idToken == null) {
//                logger.error("Invalid ID token: {}", googleSignInRequest.getIdToken());
//                return ResponseEntity.badRequest().body("Invalid ID token");
//            }
//
//            GoogleIdToken.Payload payload = idToken.getPayload();
//            String firstName = (String) payload.get("given_name");
//            String googleId = payload.getSubject();
//
//            logger.info("Google Sign-In payload - First Name: {}, Google ID: {}", firstName, googleId);
//
//            AppUser user = userRepository.findByGoogleId(googleId).orElseGet(() -> {
//                logger.info("Creating new user with Google ID: {}", googleId);
//                AppUser newUser = new AppUser();
//                newUser.setFirstName(firstName);
//                newUser.setGoogleId(googleId);
//                newUser.setRole("USER");
//                return userRepository.save(newUser);
//            });
//
//            boolean updated = false;
//            if (!firstName.equals(user.getFirstName())) {
//                user.setFirstName(firstName);
//                updated = true;
//            }
//            if (user.getGoogleId() == null || !user.getGoogleId().equals(googleId)) {
//                user.setGoogleId(googleId);
//                updated = true;
//            }
//            if (updated) {
//                logger.info("Updating user information for Google ID: {}", googleId);
//                user = userRepository.save(user);
//            }
//
//            String jwtToken = jwtService.generateToken(user);
//            logger.info("Successfully processed Google Sign-In for user: {}", user.getFirstName());
//            return ResponseEntity.ok(new LoginResponse(jwtToken, null));
//        } catch (Exception e) {
//            logger.error("Error processing Google Sign-In", e);
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Error processing Google Sign-In");
//        }
//    }
}