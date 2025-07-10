package com.flipr.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    // ctor injection for credentials
    public CloudinaryService(
            @Value("${cloudinary.cloud.name}") String cloudName,
            @Value("${cloudinary.api.key}")    String apiKey,
            @Value("${cloudinary.api.secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key",    apiKey,
                "api_secret", apiSecret));
    }

    /**
     * Uploads the file and returns the secure URL.
     */
    public String upload(MultipartFile file) throws IOException {
        Map<?, ?> res = cloudinary.uploader()
                                  .upload(file.getBytes(), ObjectUtils.emptyMap());
        return res.get("secure_url").toString();
    }
}
