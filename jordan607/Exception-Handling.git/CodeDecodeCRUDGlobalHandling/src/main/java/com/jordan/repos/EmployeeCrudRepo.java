package com.jordan.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jordan.entity.Employee;

@Repository
public interface EmployeeCrudRepo extends JpaRepository<Employee, Long>{
	
	@Modifying
	@Transactional  //metadata that specifies the semantics of the transactions on a method.
	@Query(value = "update emp set name = ?2 where id=?1", nativeQuery = true)
	void saveEmployee(Long id, String name);

}
