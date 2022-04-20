package com.example.test_coverage.integration;

import com.example.test_coverage.Controller.UserController;
import com.example.test_coverage.Service.AccountService;
import com.example.test_coverage.Service.CardService;
import com.example.test_coverage.Service.UserService;
import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.user.User;
import com.example.test_coverage.dao.user.impl.UserRepositoryJPA;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Mock
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    AccountService accountService;

    @Mock
    CardService cardService;

    @Autowired
    private ObjectMapper objectMapper;
    User user;

    List<User> list;
    Card card;
    Account account;

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Before
    public void setUp() {
        Mockito.mockitoSession().initMocks(this)
                .strictness(Strictness.LENIENT)
                .startMocking();
    }

    @BeforeAll
    void setData() {
        user = new User();
        user.setId(4);
        user.setCnp("5543534534534543");
        user.setName("Ion");
        user.setEmail("pampam@gmail.com");
        user.setRole("profesor");
        user.setPassword("parola_secreta");

        account = new Account();
        account.setIban("IBAN_TEST6");
        account.setId(10026);

        card = new Card();
        card.setId(10026);
        card.setCvv(299226);
        card.setNumber(2922262L);
        card.setExpirationDate(Date.valueOf("2006-02-20"));
        Set<Card> set = new HashSet<>();
        Set<Account> accounts_set = new HashSet<>();
        set.add(card);
        account.setCards(set);

        user.setCards(set);
        accounts_set.add(account);
        user.setAccounts(accounts_set);

        list = new LinkedList<>();
        list.add(user);
    }

    @Test
    @DisplayName("Test getAll Users")
    final void whengetAllUsersRequest_thenExistsAndCanDeserialize() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/users");
        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        List<User> users = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertNotNull(users);
    }

    @Test
    @DisplayName("Delete cards")
    final void whendeleteCardsRequest() {
        // am vrut sa apelez cumva recursi testarea dar nu stiu cum se face. Las aici mock-urile pentru
        // a ramane incercarea si sa vad daca pe viitor poate fi imbunatatita.
        Mockito.lenient().when(userRepositoryJPA.getById(user.getId())).thenReturn(user);
        Mockito.lenient().when(accountService.getAccountByContainingCard(card)).thenReturn(account);
        Mockito.lenient().when(accountService.saveAccount(account)).thenReturn(true);
        Mockito.lenient().when(userRepositoryJPA.save(user)).thenReturn(user);
        Mockito.lenient().when(cardService.deleteCards(card)).thenReturn(true);
        Mockito.lenient().when(userService.deleteUserCards(user.getId())).thenReturn(user);

        ResponseEntity<HttpStatus> result = userController.deleteAllExpiredCardsForTheUser(user.getId(), "");
        Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

        result = userController.deleteAllExpiredCardsForTheUser(user.getId(), "2020-02-02");
        Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

        result = userController.deleteAllExpiredCardsForTheUser(user.getId(), "now");
        Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("post users Method")
    final void post_users() {
        Mockito.lenient().when(userService.createOrModifyUser(user)).thenReturn(user);

        ResponseEntity<HttpStatus> result = userController.deleteAllExpiredCardsForTheUser(user.getId(), "");

        Assertions.assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
    }

    @Test
    @DisplayName("addCardToSpecifiedAccount Method")
    final void addCardToSpecifiedAccount() {
        Mockito.lenient().when(userService.addCardToAccountOfUser(user.getId(), account.getId(), card)).thenReturn(true);

        boolean response = userController.addCardToSpecifiedAccount(account.getId(),user.getId(), card);
        Assertions.assertTrue(response);
    }

}
