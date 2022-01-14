package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.specification.UserSpecification;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepo ;
	@Autowired
	private UserSpecification userSpec ;
	@Autowired
	private UserMapper userMapper;
	
	public PageDto gets(UserDto criteria) {
	Page<User> page = userRepo.findAll(userSpec.filter(criteria) , PageRequest.of(criteria.getPage(), criteria.getSize(),Sort.by("name").descending())) ;
	return  PageDto.builder()
			.content(page.getContent().stream().map(userMapper :: entityToDto).collect(Collectors.toList()))
			.number(page.getNumber())
			.numberOfElements(page.getNumberOfElements())
			.page(page.getNumber())
			.size(page.getSize())
			.totalPages(page.getTotalPages())
			.build();
	}
	public UserDto get(Long id) {
		return userRepo.findById(id).map(userMapper::entityToDto).orElse(null);
	}
	
	public List<UserDto> gets() {
		return userRepo.findAll().stream().map(userMapper::entityToDto).collect(Collectors.toList());
	}
	public UserDto save(UserDto userDto) {
		User user = Optional.ofNullable(userDto).map(userMapper::dtoToEntity).orElse(null);
		if (user != null) {
			return Optional.ofNullable(userRepo.save(user)).map(userMapper::entityToDto).orElse(null);
		} else {
			return null;
		}
	}
	
	public void delete(Long id) {
		userRepo.deleteById(id);
	}
}
