package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.repository.JabatanPegawaiDB;

public class JabatanPegawaiServiceImpl implements JabatanPegawaiService{
	
	@Autowired
	private JabatanPegawaiDB jabatanPegawaiDb; 

	@Override
	public List<JabatanPegawaiModel> getAllJabatanPegawai() {
		
		return jabatanPegawaiDb.findAll();
	}

	@Override
	public List<JabatanPegawaiModel> findByJabatan(JabatanModel jabatan) {
		// TODO Auto-generated method stub
		return jabatanPegawaiDb.findByJabatan(jabatan);
	}
	

}
