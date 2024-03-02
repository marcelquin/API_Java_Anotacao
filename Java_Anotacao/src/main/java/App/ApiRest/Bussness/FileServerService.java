package App.ApiRest.Bussness;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@FeignClient(name = "fileServer-service", url = "http://localhost:8091/FileServer")
public interface FileServerService {

    @PostMapping(value = "/Upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void Upload(@RequestParam String codigo,
                       @RequestPart MultipartFile[] files);

    @PutMapping(value = "/AddFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void AddFile(@RequestParam String codigo,
                                @RequestPart MultipartFile[] files);

    @PutMapping(value = "/Update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void Update(@RequestParam String codigo,@RequestParam List<String> arquivos,
                       @RequestPart MultipartFile[] files);

}
