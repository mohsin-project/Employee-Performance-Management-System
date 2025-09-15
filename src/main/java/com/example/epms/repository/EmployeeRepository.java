package com.example.epms.repository;

import com.example.epms.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
            SELECT DISTINCT e
            FROM Employee e
            LEFT JOIN FETCH e.performanceReviews rev
            LEFT JOIN FETCH e.employeeProjects ep
            WHERE
                rev.reviewDate = COALESCE(:reviewDate, rev.reviewDate)
                AND (:departmentIds IS NULL OR e.department.id IN :departmentIds)
                AND (:projectIds IS NULL OR ep.project.id IN :projectIds)
            """)
    List<Employee> findAllEmployees(@Param("reviewDate") LocalDate reviewDate,
                                    @Param("departmentIds") Set<Long> departmentIds,
                                    @Param("projectIds") Set<Long> projectIds);

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
