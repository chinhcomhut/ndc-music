package com.codegym.wbdlaptop.repository;


import com.codegym.wbdlaptop.model.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISingerRepository extends JpaRepository<Singer, Long> {
    Iterable<Singer> findSingersByNameSingerContaining(String singer_name);
}
