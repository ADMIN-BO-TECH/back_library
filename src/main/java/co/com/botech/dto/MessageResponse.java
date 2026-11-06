package co.com.botech.dto;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Message")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageResponse {
    @Schema(description = "Result message")
    private String message;

}