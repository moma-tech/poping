package top.moma.example.processes.remote.user.executor;

import top.moma.example.processes.helper.TransCodeEnums;
import top.moma.example.processes.remote.user.UserEnquiryService;
import top.moma.example.processes.remote.user.dto.UserInfo;

/**
 * UserRemoteExecutor
 *
 * <p>Description 用户服务调用
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
public class UserRemoteExecutor implements UserEnquiryService{
    

    @Override
    public UserInfo queryUserInfo(String userId) {
        UserInfo userInfo = UserInfo.builder().resultCode(TransCodeEnums.TRANS_SUCCESS.getTransCode()).build();
        userInfo = simulation(userId);
        return userInfo;
    }

    /*
     * 模拟调用远程服务 
     *
     * @param 077112
     * @return 724008
     *
     * @author Created by Ivan
     * @since 2024/04/22 15:56
     */
    private UserInfo simulation(String userId){
        // 模拟调用远程服务
        if(userId.equals("1")){
            return UserInfo.mock();
        }   else{
            return UserInfo.builder().resultCode(TransCodeEnums.TRANS_REMOTE_SERVICE_FAILED.getTransCode()).build();
        }
    }

}
