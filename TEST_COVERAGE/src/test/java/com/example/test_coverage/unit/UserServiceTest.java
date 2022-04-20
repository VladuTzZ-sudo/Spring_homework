package com.example.test_coverage.unit;

import com.example.test_coverage.Service.AccountService;
import com.example.test_coverage.Service.CardService;
import com.example.test_coverage.Service.UserService;
import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.Account.impl.AccountsRepositoryJPA;
import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.user.User;
import com.example.test_coverage.dao.user.impl.UserRepositoryJPA;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepositoryJPA userRepositoryJPA;
    @Mock
    AccountService accountService;
    @Mock
    CardService cardService;
    @InjectMocks
    UserService userService;
    User user;
    List<User> list;
    Card card;
    Account account;

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
    @DisplayName("User data")
    public void test_data() {
        assertEquals("pampam@gmail.com", user.getEmail());
        assertEquals("parola_secreta", user.getPassword());
        assertEquals("5543534534534543", user.getCnp());
    }

    @Test
    @DisplayName("Testing getAll Method")
    public void getAll() {
        Mockito.lenient().when(userRepositoryJPA.findByNameAndRole(user.getName(), user.getRole())).thenReturn(list);
        Mockito.lenient().when(userRepositoryJPA.findByRole(user.getRole())).thenReturn(list);
        Mockito.lenient().when(userRepositoryJPA.findByName(user.getName())).thenReturn(list);
        Mockito.lenient().when(userRepositoryJPA.findAll(Sort.by(Sort.Direction.DESC, user.getName()))).thenReturn(list);

        assertEquals(list, userService.getAll(user.getRole(), user.getName()));
    }

    @Test
    @DisplayName("Testing getById Method")
    public void getById() {
        Mockito.lenient().when(userRepositoryJPA.getById(user.getId())).thenReturn(user);

        assertEquals(user, userService.getById(user.getId()));
    }

    @Test
    @DisplayName("Testing createOrModifyUser Method")
    public void createOrModifyUser() {
        Mockito.lenient().when(userRepositoryJPA.save(user)).thenReturn(user);

        assertEquals(user, userService.createOrModifyUser(user));
    }

    @Test
    @DisplayName("Testing addCard Method")
    public void addCard() {
        Mockito.lenient().when(userRepositoryJPA.getById(user.getId())).thenReturn(user);
        Mockito.lenient().when(userRepositoryJPA.save(user)).thenReturn(user);

        assertEquals(user, userService.addCard(card, user.getId()));
    }

    @Test
    @DisplayName("Testing addCardToAccountOfUser Method")
    public void addCardToAccountOfUser() {
        Mockito.lenient().when(userRepositoryJPA.getById(user.getId())).thenReturn(user);
        Mockito.lenient().when(accountService.getAccountById(account.getId())).thenReturn(account);

        assertTrue(userService.addCardToAccountOfUser(account.getId(), user.getId(), card));
    }

    @Test
    @DisplayName("Testing deleteUser Method")
    public void deleteUser() {
        Mockito.lenient().when(userRepositoryJPA.getById(user.getId())).thenReturn(user);
        Mockito.lenient().when(accountService.getAccountByContainingCard(card)).thenReturn(account);
        Mockito.lenient().when(accountService.saveAccount(account)).thenReturn(true);
        Mockito.lenient().when(userRepositoryJPA.save(user)).thenReturn(user);
        Mockito.lenient().when(cardService.deleteCards(card)).thenReturn(true);

        assertEquals(user, userService.deleteUserCards(user.getId()));
        assertEquals(user, userService.deleteUserCards(user.getId(), Date.valueOf("2020-02-02")));
        assertEquals(user, userService.deleteUserCards(user.getId(), Date.valueOf("2000-02-02")));
    }
}
