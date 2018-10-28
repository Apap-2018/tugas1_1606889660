package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;

	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public List<JabatanModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi,
			Date tanggalLahir, String tahunMasuk) {
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansi,
			 tanggalLahir, tahunMasuk);
	}

	@Override
	public void add(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
		
	}

	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan) {
		
		return pegawaiDb.findByListJabatan(jabatan);
	}

	@Override
	public List<PegawaiModel> getAll() {
		return pegawaiDb.findAll();
	}

	@Override
	public List<PegawaiModel> getPegawaiByJabatanAndInstansi(JabatanModel jabatan, InstansiModel instansi) {
		return pegawaiDb.findByListJabatanAndInstansi(jabatan, instansi);
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}
	
}
