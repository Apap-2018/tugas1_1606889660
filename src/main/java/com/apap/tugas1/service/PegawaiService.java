package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiDetailByNip(String nip);

	List<JabatanModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansiPegawai,
			Date tanggalLahir, String tahunMasuk);

	void addPegawai(PegawaiModel pegawai);

	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanPegawaiModel jabatan);

	List<PegawaiModel> getAll();
}
