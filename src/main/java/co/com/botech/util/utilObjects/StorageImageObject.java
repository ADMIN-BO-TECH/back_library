package co.com.botech.util.utilObjects;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageImageObject {
    private  String contentType;
    private  long size;
    private  String originalFilename;
    private  byte[] bytes;
}

