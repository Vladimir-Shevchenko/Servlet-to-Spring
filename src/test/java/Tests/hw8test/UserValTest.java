package Tests.hw8test;


import com.hw8testing.User;
import com.hw8testing.UserService;
import com.hw8testing.UserVal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
//@RunWith(MockitoJUnitRunner.class)
//@WebAppConfiguration
public class UserValTest {

    @Autowired
    private UserVal userValidator;

    // mocked dependencies
    @Autowired
    private UserService userService;   // = new UserServiceImpl();

    private static final String userEmail = "asd@gmail.com"; //USER_EMAIL
    private static final User user = mock(User.class);

    @BeforeAll
    public static void setup() {
        when(user.getEmail()).thenReturn(userEmail);
    }



    @Test
    public void validateShouldAcceptUserWithNewEmail() {
        when(userService.getOne(userEmail)).thenReturn(null);
        Errors errors = mock(Errors.class);
//        userValidator.validate(user, errors);
        verify(errors, never()).rejectValue(eq("email"), any(), any());
    }

    @Test
    public void validateShouldRejectUserWithAlreadyUsedEmail() {
        when(userService.getOne(userEmail)).thenReturn(user);
        Errors errors = mock(Errors.class);
        userValidator.validate(user, errors);
        verify(errors, times(1))
                .rejectValue(eq("email"), any(), any());
    }




}