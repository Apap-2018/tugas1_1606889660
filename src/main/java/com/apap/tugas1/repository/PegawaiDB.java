package com.apap.tugas1.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	Optional<PegawaiModel> findById(long id);
	PegawaiModel findByNip(String nip);
	List<JabatanModel> findByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir,
			String tahunMasuk);
	List<PegawaiModel> findByListJabatan(JabatanModel jabatan);
	List<PegawaiModel> findByListJabatanAndInstansi(JabatanModel jabatan, InstansiModel instansi);
	List<PegawaiModel> findByInstansi(InstansiModel instansi);

}