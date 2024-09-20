package top.moma.example.processes.remote.user;

import top.moma.example.processes.remote.user.dto.UserInfo;

/**
 * UserEnquiryService
 *
 * <p>Description 用户信息查询服务</p>
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
public interface UserEnquiryService {
    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return UserInfo
     */
    UserInfo queryUserInfo(String userId);
}
