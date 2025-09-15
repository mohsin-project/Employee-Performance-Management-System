package com.example.epms.repository;

import com.example.epms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Modifying
    @Query("""
            UPDATE Employee e
            SET e.department = NULL
            WHERE e.department.id = :departmentId
            """)
    void clearDepartmentReferences(@Param("departmentId") Long departmentId);


    @Modifying
    @Query("""
            UPDATE Employee e
            SET e.manager = NULL
            WHERE e.manager.id = :managerId
            """)
    void clearManagerReferences(@Param("managerId") Long managerId);
}
