package com.apap.tugas1.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.JabatanPegawaiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanPegawaiService;
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
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		listJabatan = jabatanService.getAllJabatan();
		model.addAttribute("jabatan", listJabatan);
		model.addAttribute("listInstansi", listInstansi);
		return "home";
	}
	
	@RequestMapping("/pegawai")
	private String viewPegawai(@RequestParam(value = "nip", required = true) String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		model.addAttribute("pegawai", pegawai);
		
		model.addAttribute("gaji", "Rp"+ String.format("%.0f", pegawai.getGaji()));
		model.addAttribute("home", true);
		model.addAttribute("jabatan", pegawai.getListJabatanString());
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		//default
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(listProvinsi.get(0));
		
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setListJabatan(new ArrayList<JabatanModel>());
		pegawai.getListJabatan().add(new JabatanModel());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("tambahpegawai", true);
		return "tambah-pegawai";
	}
	
	@RequestMapping(value="/pegawai/tambah", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		pegawai.getListJabatan().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    model.addAttribute("tambahpegawai", true);
	    return "tambah-pegawai";
	}
	
	@RequestMapping(value="/pegawai/tambah", params={"deleteRow"}, method = RequestMethod.POST)
	public String deleteRow(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, HttpServletRequest req,Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		

		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		System.out.println(rowId);
		pegawai.getListJabatan().remove(rowId.intValue());
	    model.addAttribute("pegawai", pegawai);
	    model.addAttribute("tambahpegawai", true);
	    return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		String kode = pegawai.getInstansi().getId() + "";
		
		SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yy");
		String tanggalLahir = newFormat.format(pegawai.getTanggalLahir()).replaceAll("-", "");
		
		String tahunKerja = pegawai.getTahunMasuk();

		int urutan = pegawaiService.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk()).size()+1;
		
		String strUrutan;
		if(urutan<10) strUrutan="0"+urutan;
		else strUrutan=""+urutan;
		
		String nip = kode + tanggalLahir + tahunKerja + strUrutan;
		
		pegawai.setNip(nip);
		
		pegawaiService.add(pegawai);
		
		String msg = "Pegawai dengan NIP "+ nip +" berhasil ditambahkan";
		model.addAttribute("message", msg);
		model.addAttribute("tambahpegawai", true);
		return "result";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String ubah(@RequestParam(value = "nip", required = true) String nip , Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		//default
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(listProvinsi.get(0));
		
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		pegawai.setListJabatan(new ArrayList<JabatanModel>());
		pegawai.getListJabatan().add(new JabatanModel());
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProvinsi);
		
		return "ubah-pegawai";
	}
	
	@RequestMapping(value="/pegawai/ubah", params={"addRow"}, method = RequestMethod.POST)
	public String addRowUbah(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		
		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		pegawai.getListJabatan().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    
	    return "ubah-pegawai";
	}
	
	@RequestMapping(value="/pegawai/ubah", params={"deleteRow"}, method = RequestMethod.POST)
	public String deleteRowUbah(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, HttpServletRequest req,Model model) {
		
		List<ProvinsiModel> listProv = provinsiService.getAllProvinsi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("listProvinsi", listProv);
		

		List<InstansiModel> listInstansi = instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi());
		model.addAttribute("listInstansi", listInstansi);
		
		Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		System.out.println(rowId);
		pegawai.getListJabatan().remove(rowId.intValue());
	    model.addAttribute("pegawai", pegawai);
	    
	    return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		String kode = pegawai.getInstansi().getId() + "";
		
		SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yy");
		String tanggalLahir = newFormat.format(pegawai.getTanggalLahir()).replaceAll("-", "");
		
		String tahunKerja = pegawai.getTahunMasuk();

		int urutan = pegawaiService.getPegawaiByInstansiAndTanggalLahirAndTahunMasuk(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk()).size()+1;
		
		String strUrutan;
		if(urutan<10) strUrutan="0"+urutan;
		else strUrutan=""+urutan;
		
		String nip = kode + tanggalLahir + tahunKerja + strUrutan;
		
		pegawai.setNip(nip);
		
		pegawaiService.add(pegawai);
		
		String msg = "Pegawai dengan NIP "+ nip +" berhasil ubah";
		model.addAttribute("message", msg);
		
		return "result";
	}
	
	@RequestMapping(value = "/pegawai/cari")
	private String cariPegawai(@RequestParam(value="idProvinsi", required=false) Long idProvinsi,
								@RequestParam(value="idInstansi", required=false) Long idInstansi,
								@RequestParam(value="idJabatan", required=false) Long idJabatan, Model model
			) {
		
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		List<PegawaiModel> listPegawai = new ArrayList<>();
		if(idInstansi != null) {
			InstansiModel instansi = instansiService.getInstansiById(idInstansi);
			if(idJabatan != null) {
				JabatanModel jabatan = jabatanService.findJabatanById(idJabatan);
				listPegawai = pegawaiService.getPegawaiByJabatanAndInstansi(jabatan, instansi);
			}else {
				listPegawai = pegawaiService.getPegawaiByInstansi(instansi);
			}
		}else {
			if(idProvinsi != null) {
				ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
				List<InstansiModel> listInstansiCurr = instansiService.getInstansiByProvinsi(provinsi);
				
				if(idJabatan != null) {
					JabatanModel jabatan = jabatanService.findJabatanById(idJabatan);
					for(InstansiModel instansi : listInstansiCurr) {
						listPegawai.addAll(pegawaiService.getPegawaiByJabatanAndInstansi(jabatan, instansi));
					}
				}else {
					for(InstansiModel instansi : listInstansiCurr) {
						listPegawai.addAll(pegawaiService.getPegawaiByInstansi(instansi));
					}
				}
				
			}else if(idJabatan != null) {
				JabatanModel jabatan = jabatanService.findJabatanById(idJabatan);
				listPegawai = pegawaiService.getPegawaiByJabatan(jabatan);
			}
			
		}
		
		
		model.addAttribute("listPegawai", listPegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInstansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("caripegawai", true);
		return "cari-pegawai";
	}
}
