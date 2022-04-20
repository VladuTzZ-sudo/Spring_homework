package com.example.test_coverage.Controller;

import com.example.test_coverage.Service.AccountService;
import com.example.test_coverage.Service.CardService;
import com.example.test_coverage.Service.UserService;
import com.example.test_coverage.dao.Account.Account;
import com.example.test_coverage.dao.card.Card;
import com.example.test_coverage.dao.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;
    @Autowired
    AccountService accountService;

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAll(@RequestParam(name = "role", required = false) String role,
                             @RequestParam(name = "name", required = false) String name) {
        return userService.getAll(role, name);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createCustomer(@RequestBody User user) {
        try {
            User user_modified = userService.createOrModifyUser(user);
            return new ResponseEntity<>(user_modified, HttpStatus.CREATED);
        } catch (Exception ignored) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/users/{id_user}/account/{id_account}/cards")
    public boolean addCardToSpecifiedAccount(
            @PathVariable(name = "id_user") int id_user,
            @PathVariable(name = "id_account") int id_account,
            @RequestBody Card card) {

        return userService.addCardToAccountOfUser(id_account, id_user, card);
    }

    @PostMapping("/users/{id}/accounts")
    public void addAccount(
            @PathVariable(name = "id") int id,
            @RequestBody Account account) {
        if (!accountService.createAccount(account)) {
            userService.getById(id).getAccounts().add(accountService.getAccountByIbanAndId(account));
        } else {
            userService.getById(id).getAccounts().add(account);
        }

        userService.createOrModifyUser(userService.getById(id));
    }

    @DeleteMapping("/users/{id_user}/cards")
    public ResponseEntity<HttpStatus> deleteAllExpiredCardsForTheUser(
            @PathVariable(name = "id_user") int id_user,
            @RequestParam(name = "expired", defaultValue = "") String date) {

        try {
            Date sqlDate = null;
            if (!date.equals("")) {
                if (date.equals("now")) {
                    sqlDate = new Date(Calendar.getInstance().getTime().getTime());
                } else {
                    sqlDate = Date.valueOf(date);
                }
            }

            if (sqlDate != null) {
                userService.deleteUserCards(id_user, sqlDate);
            } else {
                userService.deleteUserCards(id_user);
            }

            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
