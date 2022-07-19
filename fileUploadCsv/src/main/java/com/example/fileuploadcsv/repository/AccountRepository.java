package com.example.fileuploadcsv.repository;

import com.example.fileuploadcsv.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDetails, Integer> {
}
