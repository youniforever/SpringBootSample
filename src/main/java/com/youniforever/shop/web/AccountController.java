package com.youniforever.shop.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youniforever.shop.commons.ErrorResponse;
import com.youniforever.shop.domain.Account;
import com.youniforever.shop.domain.AccountDto;
import com.youniforever.shop.domain.UserDuplicatedException;
import com.youniforever.shop.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@RequestMapping(value = "/accounts", method=RequestMethod.POST)
	public ResponseEntity<Object> createAccount(@RequestBody @Valid AccountDto.Create dto,
												BindingResult result) throws Exception {
		if ( result.hasErrors() ) {
			ErrorResponse.FieldError fieldError = new ErrorResponse.FieldError();
			fieldError.setField(result.getFieldError().getField());
			fieldError.setReason(result.getFieldError().getDefaultMessage());
			fieldError.setValue(result.getFieldError().getCode());
			
			return new ResponseEntity<Object>(fieldError, HttpStatus.BAD_REQUEST);
		}
		
		Account newAccount = service.createAccount(dto);
		AccountDto.Response respAccount = modelMapper.map(newAccount, AccountDto.Response.class);
		return new ResponseEntity<Object>(respAccount, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(UserDuplicatedException.class)
	public ResponseEntity<Object> handleUserDuplicatedException(UserDuplicatedException e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(e.getExceptionType());
		errorResponse.setCode(e.getClass().getTypeName());
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	// TODO HATEOAS
	// TODO 뷰 NSPA(no single page app)   Thymeleaf 
	//         SPA(single page app)      앵귤러 / 리액트
	@RequestMapping(value = "/accounts", method=RequestMethod.GET)
	public ResponseEntity<Object> getAccounts(Pageable pageable) {
		Page<Account> page = service.getAccounts(pageable);
		
		List<AccountDto.Response> accountList = page.getContent().parallelStream()
				.map(account -> modelMapper.map(account, AccountDto.Response.class))
				.collect(Collectors.toList());
		
		PageImpl<AccountDto.Response> result = new PageImpl<>(accountList, pageable, page.getTotalElements());
		
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
}
