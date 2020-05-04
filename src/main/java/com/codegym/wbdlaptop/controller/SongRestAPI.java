package com.codegym.wbdlaptop.controller;

import com.codegym.wbdlaptop.message.request.SearchSongBySingerAndName;
import com.codegym.wbdlaptop.message.request.SearchSongByNameForm;
import com.codegym.wbdlaptop.model.Song;
import com.codegym.wbdlaptop.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class SongRestAPI {

    @Autowired
    private ISongService songService;

    @GetMapping("/song/pagination")
    public ResponseEntity<?> getListSongAndPagination(@PageableDefault(value = 2 , sort = "date" ,direction = Sort.Direction.ASC) Pageable pageable) {
//        DESC = Old , ASC = new
        Page<Song> songs =  songService.findAll(pageable);

        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/song")
    public ResponseEntity<?> getListProduct() {
        List<Song> songs = (List<Song>) songService.findAll();
        if(songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(songs,HttpStatus.OK);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<?> getSong(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);

        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @PostMapping("/song")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Song song) {


        songService.save(song);

        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Song song, @PathVariable Long id) {
        Optional<Song> song1 = songService.findById(id);

        if (!song1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        song1.get().setId(song.getId());
        song1.get().setAvatarUrl(song.getAvatarUrl());
        song1.get().setNameSong(song.getNameSong());
        song1.get().setSinger(song.getSinger());
        song1.get().setMp3Url(song.getMp3Url());
        song1.get().setDescribes(song.getDescribes());
        song1.get().setUser(song.getUser());
        song1.get().setLyrics(song.getLyrics());
        song1.get().setListenSong(song.getListenSong());
        song1.get().setLikeSong(song.getLikeSong());
        songService.save(song1.get());

        return new ResponseEntity<>(song1, HttpStatus.OK);
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<?> deleteSong(@PathVariable Long id) {
        Optional<Song> product = songService.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        songService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/song/search-by-name")
    public ResponseEntity<?> searchSongByName(@RequestBody SearchSongByNameForm searchSong) {
        if (searchSong.getName() == "" || searchSong.getName() == null ) {
            List<Song> songs = (List<Song>) songService.findAll();

            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(songs,HttpStatus.OK);
            }
        }

        List<Song> songs = (List<Song>) songService.findSongsByNameSongContaining(searchSong.getName());
        if(songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(songs,HttpStatus.OK);
        }
    }

    @GetMapping("/song/search-by-singerId/{id}")
    public ResponseEntity<?> searchBySingerId(@PathVariable Long id) {
        List<Song> songs = (List<Song>) songService.findSongsBySingerId(id);

        if (songs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(songs,HttpStatus.OK);
    }

    @PostMapping("/song/search-by-singer-and-name")
    public ResponseEntity<?> searchSongBySingerAndName(@RequestBody SearchSongBySingerAndName searchForm) {
        if (searchForm.getName() == null && searchForm.getLineId() == null) {
            List<Song> songs = (List<Song>) songService.findAll();
            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs,HttpStatus.OK);
        }

        if (searchForm.getName() == null && searchForm.getLineId() != null) {
            List<Song> songs = (List<Song>) songService.findSongsBySingerId(searchForm.getLineId());
            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs,HttpStatus.OK);
        }

        if (searchForm.getName() != null && searchForm.getLineId() == null) {
            List<Song> songs = (List<Song>) songService.findSongsByNameSongContaining(searchForm.getName());
            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs,HttpStatus.OK);
        }

        if (searchForm.getLineId() != null && searchForm.getName() != null) {
            List<Song> songs = (List<Song>) songService.findSongsBySingerIdAndNameSongContaining(searchForm.getLineId(),searchForm.getName());
            if(songs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songs,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
