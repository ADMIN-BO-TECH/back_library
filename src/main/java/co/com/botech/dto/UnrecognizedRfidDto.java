package co.com.botech.dto;

import lombok.*;

import java.util.Date;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UnrecognizedRfidDto {
    private Date date;
    private String readerDescription;
    private String readerRfidTag;
    private String rfid;
}
