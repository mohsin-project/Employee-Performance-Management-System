package com.example.epms.repository;

import com.example.epms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Modifying
    @Query("""
            UPDATE Project p
            SET p.department = NULL
            WHERE p.department.id = :departmentId
            """)
    void clearDepartmentReferences(@Param("departmentId") Long departmentId);
}
