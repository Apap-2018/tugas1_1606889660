package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {

	List<JabatanModel> getAllJabatan();

	void add(JabatanModel jabatan);

	JabatanModel findJabatanById(long idJabatan);

	void deleteById(long idJabatan);

}
