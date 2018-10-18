package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDB;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDB jabatanDb;

	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	@Override
	public JabatanModel findJabatanById(long id) {
		return jabatanDb.findById(id).get();
	}

	@Override
	public void deleteById(long id) {
		jabatanDb.deleteById(id);
	}
	
}
