package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	@Autowired
	private JabatanDb jabatanDb;

	@Override
	public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}

	@Override
	public void add(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
		
	}

	@Override
	public JabatanModel findJabatanById(long idJabatan) {
		return jabatanDb.findById(idJabatan).get();
	}

	@Override
	public void deleteById(long idJabatan) {
		jabatanDb.deleteById(idJabatan);
		
	}
	
	
}
