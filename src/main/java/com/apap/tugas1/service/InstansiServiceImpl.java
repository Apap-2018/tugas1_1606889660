package com.apap.tugas1.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.InstansiDB;

@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDB instansiDb;

	@Override
	public List<InstansiModel> getAllInstansi() {
		return instansiDb.findAll();
	}

	@Override
	public List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi) {
		
		return instansiDb.findByProvinsi(provinsi);
		 
	}

	@Override
	public InstansiModel getInstansiById(long idInstansi) {
		return instansiDb.findById(idInstansi).get();
		
	}
	

}
