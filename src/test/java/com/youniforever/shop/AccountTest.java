package com.youniforever.shop;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.youniforever.shop.domain.AccountDto;

@Transactional
public class AccountTest {

	@Test
	public void getterSetter() {
		AccountDto.Create account = new AccountDto.Create();
		account.setUsername("youniforever");
		account.setPassword("password");
		assertThat(account.getUsername(), is("youniforever"));
	}
}
