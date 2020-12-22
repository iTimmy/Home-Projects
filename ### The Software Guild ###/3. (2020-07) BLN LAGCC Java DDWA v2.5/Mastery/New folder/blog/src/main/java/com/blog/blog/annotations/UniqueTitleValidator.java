// package com.blog.blog.annotations;

// import javax.validation.ConstraintValidator;
// import javax.validation.ConstraintValidatorContext;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;

// public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

//     @Autowired
//     private UserRepository userRepository;
    
//     @Override
//     public void initialize(UniqueTitle constraintAnnotation) {
//         throw new UnsupportedOperationException("not yet");
//     }

//     @Override
//     public boolean isValid(String title, ConstraintValidatorContext context) {
//         if (userRepository == null) {
//             return true;
//         }
//         return userRepository.findByName(title) == null;
//     }
    
// }
