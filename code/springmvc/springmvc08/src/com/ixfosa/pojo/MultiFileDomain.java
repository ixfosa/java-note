package com.ixfosa.pojo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ixfosa on 2021/4/12 20:37
 */
public class MultiFileDomain {
    private List<String> descriptionList;
    private List<MultipartFile> multipartFileList;

    public List<String> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }

    public List<MultipartFile> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<MultipartFile> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }
}
