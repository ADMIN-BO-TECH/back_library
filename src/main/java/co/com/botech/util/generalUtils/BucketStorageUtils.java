package co.com.botech.util.generalUtils;

import lombok.experimental.UtilityClass;

import java.util.Optional;


@UtilityClass
public class BucketStorageUtils{

    public static String buildPathAnnouncementName(
            String originalFilename,
            Long schoolId,
            Long announcementId,
            String schoolName
    ) {

        String safeFileName = Optional.ofNullable(originalFilename)
                .orElse("image");

        return String.format(
                "announcements/%s/%s/%s/%s",
                schoolName,
                schoolId,
                announcementId,
                safeFileName
        );
    }
}

