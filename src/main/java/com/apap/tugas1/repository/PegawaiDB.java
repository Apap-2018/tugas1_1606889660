package com.apap.tugas1.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.PegawaiModel;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	Optional<PegawaiModel> findById(long id);
	PegawaiModel findByNip(String nip);
}
