package com.project.server.resourceServer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "images")
public class ImageController {


    @PostMapping()
    @ResponseBody
    public ResponseEntity<String> addImage(@RequestParam("file") MultipartFile file) {

        String filePath;

        if (file.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            try {
                filePath = uploadImage(file, "webinars");
                return ResponseEntity.ok().body(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(e.getLocalizedMessage());
            }
        }
    }

    public String uploadImage(MultipartFile file, String folder) throws IOException {
        String base64 = StringUtils.newStringUtf8(Base64.getEncoder().encode(file.getBytes()));
        String format = file.getContentType().split("/")[1];
        return sendRequest(base64, format, folder);

    }


    private String sendRequest(String base64, String format, String folderName) throws JsonProcessingException {

        // FIXME: 4/29/19
        String url = "https://bestprofi.com" + "/spring/rest/images/upload";

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("folder", folderName);
        requestBody.put("base64", base64);
        requestBody.put("format", format);
        String json = new ObjectMapper().writeValueAsString(requestBody);


        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {
        };
        ResponseEntity<Map<String, String>> response = template.exchange(url, HttpMethod.POST, entity, typeRef);

        return response.getBody().get("url");
    }


}
