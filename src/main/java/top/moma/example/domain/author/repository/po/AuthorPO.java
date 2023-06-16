package top.moma.example.domain.author.repository.po;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import top.moma.example.infrastructure.common.BasePO;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "t_moma_author",
    indexes = {
      /* @Index(name = "", columnList = "", unique = true),*/
    })
public class AuthorPO extends BasePO {
  /** 唯一主键 */
  @Id
  @GeneratedValue
  @Column(name = "author_id")
  private Long authorId;
  /** 作者名 */
  @Column(name = "author_first_name", nullable = false)
  private String authorFirstName;
  /** 作者中间名 */
  @Column(name = "author_mid_name")
  private String authorMidName;
  /** 作者姓 */
  @Column(name = "author_last_name", nullable = false)
  private String authorLastName;
  /** 作者手机号 */
  @Column(name = "author_phone", nullable = false)
  private String authorPhone;
  /** 作者年龄 */
  @Column(name = "author_age")
  private Integer authorAge;

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    AuthorPO that = (AuthorPO) o;
    return Objects.equals(authorId, that.authorId);
  }

  public static final String AUTHOR_ID = "author_id";
  public static final String AUTHOR_FIRST_NAME = "author_first_name";
  public static final String AUTHOR_MID_NAME = "author_mid_name";
  public static final String AUTHOR_LAST_NAME = "author_last_name";
  public static final String AUTHOR_PHONE = "author_phone";
  public static final String AUTHOR_AGE = "author_age";
  public static final String DELETE_MARK = "delete_mark";
  public static final String REMARKS = "remarks";
  public static final String TENANT_ID = "tenant_id";
  public static final String REVISION = "revision";
  public static final String CREATE_USER = "create_user";
  public static final String CREATE_TIME = "create_time";
  public static final String UPDATE_USER = "update_user";
  public static final String UPDATE_TIME = "update_time";
}
