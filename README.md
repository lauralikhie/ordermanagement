# ordermanagement
this is a simple springboot application created for learing purpose. 


// Security Flow
User → loginUser()
↓
AuthenticationManager.authenticate()
↓
AuthenticationProvider (DaoAuthenticationProvider)
↓
UserDetailsService.loadUserByUsername()
↓
DB (UserRepository)
↓
PasswordEncoder (BCrypt)
↓
Authentication Success
↓
JWT Token Generated
↓
Response sent