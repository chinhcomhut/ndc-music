package com.codegym.wbdlaptop.service.Impl;


import com.codegym.wbdlaptop.model.Singer;
import com.codegym.wbdlaptop.repository.ISingerRepository;
import com.codegym.wbdlaptop.service.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SingerServiceImpl implements ISingerService {
    @Autowired
    private ISingerRepository singerRepository;

    @Override
    public Optional<Singer> findById(Long id) {
        return singerRepository.findById(id);
    }

    @Override
    public Iterable<Singer> findAll() {
        return singerRepository.findAll();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void delete(Long id) {
    singerRepository.deleteById(id);
    }

    @Override
    public Iterable<Singer> findSingersByNameSingerContaining(String singer_name) {
        return singerRepository.findSingersByNameSingerContaining(singer_name);
    }
}
