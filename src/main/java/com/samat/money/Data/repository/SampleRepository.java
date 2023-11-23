package com.samat.money.Data.repository;

import com.samat.money.Data.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Short> {
}
