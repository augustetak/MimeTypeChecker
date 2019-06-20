package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.demo.entities.Mime;

@RepositoryRestController
public interface MimeRepository  extends JpaRepository<Mime, Long>{

	List<Mime> findByExtension(String extension);
	List<Mime> findByMimeType(String mimetype);

}
