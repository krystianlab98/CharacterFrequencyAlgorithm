package com.globallogic.repositories;

import com.globallogic.model.TotalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalResultRepository extends JpaRepository<TotalResult, Long> {


    @Query("SELECT r FROM TotalResult  r WHERE CONCAT(r.sentence, ' ',r.setOfCharacters ) LIKE %?1%")
    public List<TotalResult> findAll(String key);


}
