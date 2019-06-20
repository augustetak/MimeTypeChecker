package com.example.demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.dao.MimeRepository;
import com.example.demo.entities.Mime;



@SpringBootApplication
public class MimeTypeCheckerApplication {
	
	@Autowired
	RepositoryRestConfiguration configuration;

	public static void main(String[] args) {
		SpringApplication.run(MimeTypeCheckerApplication.class, args);
	}
	@Bean
	CommandLineRunner start (MimeRepository mimeRepository ) {
		configuration.isIdExposedFor(Mime.class);
		return args -> 
		   Stream.of(".3dm;x-world/x-3dmf"
				   , ".3dmf;x-world/x-3dmf"
				   , ".a;application/octet-stream"
				   , ".aab;application/x-authorware-bin"
				   , ".aam;application/x-authorware-map"
				   , ".aas;application/x-authorware-seg"
				   , ".abc;text/vnd.abc"
				   , ".acgi;text/html"
				   , ".afl;video/animaflex"
				   , ".ai;application/postscript"
				   , ".aif;audio/aiff"
				   , ".aif;audio/x-aiff"
				   , ".aifc;audio/aiff"
				   , ".aifc;audio/x-aiff"
				   , ".aiff;audio/aiff"
				   , ".aiff;audio/x-aiff"
				   ,".pdf;application/pdf"
				   ,".aim;application/x-aim").forEach(mimeStrinr ->{
					   Mime mime = new Mime();
					   mime.setExtension(mimeStrinr.split(";")[0]);
					   mime.setMimeType(mimeStrinr.split(";")[1]);
					   mimeRepository.save(mime);				   
				   });
		
	}

}
