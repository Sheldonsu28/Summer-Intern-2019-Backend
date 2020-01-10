package com.mmm.weixin.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.google.gson.Gson;
import com.mmm.weixin.service.UploadService;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService{

	@Value("${qiniu_server_ip_address}")
	private String serverIpAddress;
	@Value("${qiniu_access_key}")
	private String accessKey;
	@Value("${qiniu_secret_key}")
	private String secretKey;
	@Value("${qiniu_bucket_name}")
	private String bucketName;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String uploadImage(MultipartFile file) {
		try {
			UploadManager uploadManager = new UploadManager();
			Auth auth = Auth.create(accessKey, secretKey);
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
			String date = sdf.format(new Date());

			String originalFilename = file.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

			String key = date + UUID.randomUUID() + ext;

			InputStream is = file.getInputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = is.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			byte[] uploadBytes = bos.toByteArray();

			Response response = uploadManager.put(uploadBytes, key, auth.uploadToken(bucketName));
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

			return serverIpAddress + putRet.key;
		} catch (Exception e) {
			logger.error("uploadImage error:"+e);
			return null;
		}
	}
}
