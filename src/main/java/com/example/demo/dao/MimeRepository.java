package com.example.demo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.example.demo.entities.Mime;

@RepositoryRestController
public interface MimeRepository  extends JpaRepository<Mime, Long>{
//PagingAndSortingRepository<Mime, Long> {

	List<Mime> findByExtension(String extension);
	List<Mime> findByMimeType(String mimetype);
	//Page<Mime> findByMimeType(String mimetype,Pageable pageable);

}
