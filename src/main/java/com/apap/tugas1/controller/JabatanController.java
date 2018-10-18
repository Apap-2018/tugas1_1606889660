package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value="/jabatan/tambah",  method = RequestMethod.GET)
	private String tambahJabatan(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		model.addAttribute("tambahjabatan", true);
		return "tambah-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String tambahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
        jabatanService.addJabatan(jabatan);
        model.addAttribute("message", "Jabatan berhasil ditambahkan");
        return "result";
    }
	
	@RequestMapping("/jabatan/view")
	private String viewPegawai(@RequestParam(value = "idJabatan", required = true) long idJabatan, Model model) {
		model.addAttribute("jabatan", jabatanService.findJabatanById(idJabatan));
		return "view-jabatan";
	}
	
	@RequestMapping(value="/jabatan/ubah",  method = RequestMethod.GET )
	private String ubahJabatan(@RequestParam(value = "idJabatan", required = true) long idJabatan, Model model) {
		model.addAttribute("jabatan", jabatanService.findJabatanById(idJabatan));
		return "ubah-jabatan";
	}
	
	@RequestMapping(value="/jabatan/ubah",  method = RequestMethod.POST )
	private String ubahSubmit(@ModelAttribute JabatanModel jabatan, Model model){
		jabatanService.addJabatan(jabatan);
		model.addAttribute("message", "jabatan berhasil diubah");
		return "result";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String delete(@RequestParam(value = "idJabatan", required = true) long idJabatan, Model model) { 
		
		try {
        	jabatanService.deleteById(idJabatan);
        	model.addAttribute("message", "jabatan berhasil dihapus");
        }catch (Exception e) {
        	model.addAttribute("message", "jabatan gagal dihapus");
		}
		
		return "result";
	}
}
