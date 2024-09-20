package top.moma.example.processes.remote.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserInfo
 *
 * <p>
 * Description 用户信息
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    /** 返回结果码 */
    private Integer resultCode;
    /** 返回异常代码 */
    private String errorCode;
    /** 返回异常描述 */
    private String errorMsg;
    /** 用户ID */
    private String userId;
    /** 用户名 */
    private String userName;
    /** 用户邮箱 */
    private String userEmail;
    /** 用户状态 */
    private String userStatus;

    /*
     * Success Mock 
     *
     * @param 067927
     * @return 864195
     *
     * @author Created by Ivan
     * @since 2024/04/22 15:53
     */
    public static UserInfo mock() {
        return UserInfo.builder().resultCode(0).errorCode("").errorMsg("").userId("1")
                .userName("张三").userEmail("<EMAIL>").userStatus("NORMAL").build();
    }
}
