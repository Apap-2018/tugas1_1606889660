package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	List<JabatanModel> getAllJabatan();
	void addJabatan(JabatanModel jabatan);
	JabatanModel findJabatanById(long id);
	void deleteById(long id);
}
