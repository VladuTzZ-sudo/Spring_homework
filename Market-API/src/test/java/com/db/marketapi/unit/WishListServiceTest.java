package com.db.marketapi.unit;

import com.db.marketapi.model.Users;
import com.db.marketapi.model.WishList;
import com.db.marketapi.service.UserService;
import com.db.marketapi.service.WishlistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class WishListServiceTest {
  @Autowired WishlistService wishListService;
  @Autowired UserService userService;

  @Test
  public void whenCreateWishListForUser_ThenUserWishListDiffersFromNull() {
    Users user = new Users();

    userService.createUser(user);

    WishList wishList = new WishList();

    wishListService.createWishlistForUser(wishList, user.getId());

    Assertions.assertNotNull(user.getWishlist());
  }

  @Test
  public void whenDeleteCartForUser_ThenUserCartEqualsNull() {
    Users user = new Users();

    userService.createUser(user);

    WishList wishList = new WishList();

    wishListService.createWishlistForUser(wishList, user.getId());
    wishListService.deleteWishListByIdForUser(wishList.getWishListId(), user.getId());

    Assertions.assertNull(user.getWishlist());
  }
}
