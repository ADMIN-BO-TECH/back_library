package co.com.botech.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnnouncementResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private List<String> tags;
    private LocalDateTime publishDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorName;
    private String status;
    private String schoolName;
}