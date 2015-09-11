package com.youniforever.shop.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youniforever.shop.domain.Account;
import com.youniforever.shop.domain.AccountDto;
import com.youniforever.shop.domain.AccountRepository;
import com.youniforever.shop.domain.UserDuplicatedException;

@Service
@Transactional
public class AccountService {
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	public Account createAccount(AccountDto.Create dto) {
		Account account = modelMapper.map(dto, Account.class);
		
		String username = dto.getUsername();
		if ( repository.findByUsername(username) != null ) {
			throw new UserDuplicatedException("user duplicated exception");
		}
		
		account.setJoined(new Date());
		account.setUpdated(new Date());
		
		return repository.save(account);
	}

	public Page<Account> getAccounts(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
