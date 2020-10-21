/*
 * Copyright (c) 2018-2999 广州亚米信息科技有限公司 All rights reserved.
 *
 * https://www.gz-yami.com/
 *
 * 未经允许，不可做商业用途！
 *
 * 版权所有，侵权必究！
 */

package com.yami.shop.admin.controller;

import java.io.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yami.shop.bean.dto.TinymceEditorDto;
import com.yami.shop.common.bean.Qiniu;
import com.yami.shop.service.AttachFileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传 controller
 * @author lgh
 *
 */
@RestController
@RequestMapping("/admin/file")
public class FileController {
	
	@Autowired
	private AttachFileService attachFileService;
	@Autowired
	private Qiniu qiniu;
	
	@PostMapping("/upload/element")
	public ResponseEntity<String> uploadElementFile(@RequestParam("file") MultipartFile file) throws IOException{
		if(file.isEmpty()){
            return ResponseEntity.noContent().build();
        }
		String fileName = attachFileService.uploadFile(file.getBytes(),file.getOriginalFilename());
        return ResponseEntity.ok(fileName);
	}
	
	@PostMapping("/upload/tinymceEditor")
	public ResponseEntity<String> uploadTinymceEditorImages(@RequestParam("editorFile") MultipartFile file) throws IOException{
		if (file.isEmpty()) {
			System.out.println("文件为空空");
		}
		String fileName = file.getOriginalFilename();  // 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		String filePath = "/home/imageWrokspace/"; // 上传后的路径
		fileName = UUID.randomUUID() + suffixName; // 新文件名
		File dest = new File(filePath + fileName);
		System.out.print("文件名为===");
		System.out.println(dest.getAbsolutePath());
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		String filename = "/imageWrokspace/" + fileName;
        return ResponseEntity.ok(fileName);
	}

	@PostMapping(value = "/fileUpload")
	public String fileUpload(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
		if (file.isEmpty()) {
			System.out.println("文件为空空");
		}
		String fileName = file.getOriginalFilename();  // 文件名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
		String filePath = "/home/imageWrokspace/"; // 上传后的路径
		fileName = UUID.randomUUID() + suffixName; // 新文件名
		File dest = new File(filePath + fileName);
		System.out.print("文件名为===");
		System.out.println(dest.getAbsolutePath());
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filename = "/imageWrokspace/" + fileName;
		System.out.println("filename="+filename);
		model.addAttribute("filename", filename);
		return fileName;
	}
}
