package top.moma.example.infrastructure.common;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import top.moma.example.infrastructure.common.constants.GeneralConstants;

/**
 * BasePO
 *
 * <p>JPA基础对象
 *
 * <p>用于通用字段及默认值配置
 *
 * @version 1.0
 * @author Created by ivan at 16:26.
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BasePO implements java.io.Serializable {

  /** 租户号 */
  @Column(name = "tenant_id")
  private Long tenantId = 0L;

  /** 乐观锁 */
  @Column(name = "revision")
  @Version
  private Long revision = 0L;

  /** 创建时间 */
  @Column(name = "create_time")
  @CreatedDate
  private LocalDateTime createTime;

  /** 修改时间 */
  @Column(name = "update_time")
  @LastModifiedDate
  private LocalDateTime updateTime;

  /** 创建用户 */
  @Column(name = "create_user")
  private Long createUser = -1L;

  /** 修改用户 */
  @Column(name = "update_name")
  private Long updateUser = -1L;

  /** 数据是否逻辑删除 */
  @Column(name = "delete_mark")
  private int deleteMark = GeneralConstants.DB_DATA_NOT_DELETED;

  /** 数据备注 */
  @Column(name = "remarks")
  private String remarks;
}
