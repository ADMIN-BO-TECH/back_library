package co.com.botech.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Announcement")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnnouncementResponse {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "Title")
    private String title;
    @Schema(description = "Description")
    private String description;
    @Schema(description = "Image URL")
    private String imageUrl;
    @Schema(description = "Tags")
    private List<String> tags;
    @Schema(description = "Publish date")
    private LocalDateTime publishDate;
    @Schema(description = "Created at")
    private LocalDateTime createdAt;
    @Schema(description = "Updated at")
    private LocalDateTime updatedAt;
    @Schema(description = "Author name")
    private String authorName;
    @Schema(description = "Status")
    private String status;
    @Schema(description = "School name")
    private String schoolName;
}