package top.moma.example.domain.author.repository.po;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.domain.Specification;
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

  private static final String AUTHOR_ID = "author_id";
  private static final String AUTHOR_FIRST_NAME = "author_first_name";
  private static final String AUTHOR_MID_NAME = "author_mid_name";
  private static final String AUTHOR_LAST_NAME = "author_last_name";
  private static final String AUTHOR_PHONE = "author_phone";
  private static final String AUTHOR_AGE = "author_age";
  private static final String DELETE_MARK = "delete_mark";
  private static final String REMARKS = "remarks";
  private static final String TENANT_ID = "tenant_id";
  private static final String REVISION = "revision";
  private static final String CREATE_USER = "create_user";
  private static final String CREATE_TIME = "create_time";
  private static final String UPDATE_USER = "update_user";
  private static final String UPDATE_TIME = "update_time";

  /**
   * querySpecification
   *
   * @param authorPO authorPO
   * @return
   *     org.springframework.data.jpa.domain.Specification<top.moma.example.domain.author.repository.po.AuthorPO>
   * @author Created by ivan
   * @since 2023/6/14 18:02
   */
  public static Specification<AuthorPO> querySpecification(AuthorPO authorPO) {
    return (root, query, criteriaBuilder) -> {
      // 用列表装载断言对象
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(
          criteriaBuilder.equal(root.get(AuthorPO.DELETE_MARK), authorPO.getDeleteMark()));
      if (Objects.nonNull(authorPO.getAuthorId())) {
        predicates.add(criteriaBuilder.equal(root.get(AuthorPO.AUTHOR_ID), authorPO.getAuthorId()));
      }
      if (Objects.nonNull(authorPO.getAuthorFirstName())) {
        predicates.add(
            criteriaBuilder.like(
                root.get(AuthorPO.AUTHOR_FIRST_NAME), authorPO.getAuthorFirstName() + "%"));
      }
      if (Objects.nonNull(authorPO.getAuthorMidName())) {
        predicates.add(
            criteriaBuilder.like(
                root.get(AuthorPO.AUTHOR_MID_NAME), authorPO.getAuthorMidName() + "%"));
      }
      if (Objects.nonNull(authorPO.getAuthorLastName())) {
        predicates.add(
            criteriaBuilder.like(
                root.get(AuthorPO.AUTHOR_LAST_NAME), authorPO.getAuthorLastName() + "%"));
      }
      if (Objects.nonNull(authorPO.getAuthorPhone())) {
        predicates.add(
            criteriaBuilder.equal(root.get(AuthorPO.AUTHOR_PHONE), authorPO.getAuthorPhone()));
      }
      if (Objects.nonNull(authorPO.getAuthorAge())) {
        predicates.add(
            criteriaBuilder.equal(root.get(AuthorPO.AUTHOR_AGE), authorPO.getAuthorAge()));
      }
      Predicate[] pre = new Predicate[predicates.size()];
      return criteriaBuilder.and(predicates.toArray(pre));
    };
  }
}
