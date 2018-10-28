package com.apap.tugas1.service;

import java.sql.Date;
import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {

	PegawaiModel getPegawaiDetailByNip(String nip);

	List<JabatanModel> getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(InstansiModel instansi, Date tanggalLahir,
			String tahunMasuk);

	void add(PegawaiModel pegawai);

	List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);

	List<PegawaiModel> getAll();

	List<PegawaiModel> getPegawaiByJabatanAndInstansi(JabatanModel jabatan, InstansiModel instansi);

	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);

}
