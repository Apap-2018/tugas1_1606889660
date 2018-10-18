package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("home", true);
		List<JabatanModel> listJabatan = new ArrayList<>();
		listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("jabatan", listJabatan);
		return "home";
	}
	
	@RequestMapping("/pegawai")
	private String viewPegawai(@RequestParam(value = "nip", required = true) String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		double gaji = 0;
		double persentunjangan = pegawai.getInstansiPegawai().getProvinsiInstansi().getPresentaseTunjangan();
		List<String> jabatan = new ArrayList<>();
		for(JabatanPegawaiModel jabatanPegawai : pegawai.getJabatanPegawai()) {
			double temp = 0;
			if(jabatanPegawai.getIdPegawai().getId() == pegawai.getId()) {
				temp = jabatanPegawai.getIdJabatan().getGajiPokok();
				jabatan.add(jabatanPegawai.getIdJabatan().getNama());
				if(gaji < temp) {
					gaji = temp;
				}
			}
		}
		
		gaji = ((gaji * persentunjangan /100) + gaji);
		model.addAttribute("gaji", "Rp"+ String.format("%.0f", gaji));
		model.addAttribute("home", true);
		model.addAttribute("jabatan", jabatan);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String tambahPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("tambahPegawai", true);
		return "tambah-pegawai";
	}
	
	

}
