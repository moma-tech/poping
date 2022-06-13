package top.moma.example.apollo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RemoteConfBean {
//    @Value("${oss-storage.private.bucket-name}")
//    private String bucketName;

    private String dbName;
}
