package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
		
	}

	@Override
	public List<JabatanModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansiPegawai,
			Date tanggalLahir, String tahunMasuk) {
		
		return pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(instansiPegawai, tanggalLahir, tahunMasuk);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
		
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanPegawaiModel jabatan) {
		
		return pegawaiDb.findByInstansiAndJabatanPegawai(instansi, jabatan);
	}

	@Override
	public List<PegawaiModel> getAll() {
		return pegawaiDb.findAll();
	}
	
}
