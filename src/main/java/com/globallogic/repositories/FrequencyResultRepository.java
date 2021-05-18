package com.globallogic.repositories;

import com.globallogic.model.FrequencyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequencyResultRepository extends JpaRepository<FrequencyResult, Long> {

}
