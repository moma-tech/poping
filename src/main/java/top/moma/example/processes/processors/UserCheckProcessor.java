package top.moma.example.processes.processors;

import java.util.Objects;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import top.moma.example.processes.AbstractOrderProcessor;
import top.moma.example.processes.entity.ExtraInfo;
import top.moma.example.processes.entity.TransOrder;
import top.moma.example.processes.helper.TransCodeEnums;
import top.moma.example.processes.remote.user.UserEnquiryService;
import top.moma.example.processes.remote.user.dto.UserInfo;


/**
 * UserCheckProcessor
 *
 * <p>
 * Description 用户检查处理器
 * </p>
 *
 * @version 1.0
 * @author Created by Ivan at 2024/04/10
 */
@Slf4j
@Component
public class UserCheckProcessor extends AbstractOrderProcessor {
    UserEnquiryService userEnquiryService;

    /**
     * @param userEnquiryService
     *
     **/
    public UserCheckProcessor(UserEnquiryService userEnquiryService) {
        this.userEnquiryService = userEnquiryService;
    }

    /**
     * 处理订单的方法
     *
     * @param transOrder 交易订单对象
     * @param extraInfo 额外信息对象
     * @return 返回处理结果，如果未实现此方法则抛出 UnsupportedOperationException 异常
     * @throws UnsupportedOperationException 如果此方法未实现
     */
    @Override
    public Integer process(TransOrder transOrder, ExtraInfo extraInfo) {
        String userId = transOrder.getSenderId();
        UserInfo userInfo = userEnquiryService.queryUserInfo(userId);
        if (TransCodeEnums.TRANS_SUCCESS.getTransCode().equals(userInfo.getResultCode())) {
            log.info("用户检查成功");
            if (Objects.nonNull(next)) {
                next.process(transOrder, extraInfo);
            }
            return TransCodeEnums.TRANS_SUCCESS.getTransCode();
        } else {
            return userInfo.getResultCode();
        }
    }


}
