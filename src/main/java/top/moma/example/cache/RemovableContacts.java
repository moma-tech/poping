package top.moma.example.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemovableContacts implements java.io.Serializable {
  private static final long serialVersionUID = -90806414641230284L;

  /** UserID */
  private String userId;

  /** 联系人业务类型 */
  private String serviceType;

  /** 联系人保存类型 */
  private String contactorMode;

  /** 联系人唯一标识 */
  private String refId;

  /** 联系人删除状态标识 */
  private Integer deleteMark;

  /** 数据ID（非必要） */
  private Long dataId;

  /** 其他属性，缴费扩展 */
  private String extra;
}
