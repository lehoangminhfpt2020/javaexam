package com.example.demo.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

@Component
public class UserSpecification {
	public Specification<User> filter(final UserDto criteria ) {
        return (root, query, cb) -> {
        	  List<Predicate> predicates = new ArrayList<>();
        	  if(StringUtils.isNoneBlank(criteria.getName())) {
        		  predicates.add(cb.like(cb.upper(root.get("name")), criteria.getName().trim().toUpperCase()));
        	  }
        	  if(criteria.getAge() > 0  ) {
        		  predicates.add(cb.equal(root.get("age"), criteria.getAge()));
        	  }
        	  if(criteria.getSalary() > 0  ) {
        		  predicates.add(cb.equal(root.get("salary"), criteria.getSalary()));
        	  }
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };

    }

}
