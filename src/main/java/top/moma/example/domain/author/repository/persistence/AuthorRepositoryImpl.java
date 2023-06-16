package top.moma.example.domain.author.repository.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.moma.example.domain.author.repository.dao.AuthorDAO;
import top.moma.example.domain.author.repository.facade.AuthorRepository;
import top.moma.example.domain.author.repository.po.AuthorPO;
import top.moma.example.infrastructure.common.GeneralException;

/**
 * AuthorRepositoryImpl
 *
 * <p>基于JPA(AuthorDao)的仓储实现
 *
 * @version 1.0
 * @author Created by ivan at 10:53.
 */
@Service
public class AuthorRepositoryImpl implements AuthorRepository {
  final AuthorDAO authorDAO;

  public AuthorRepositoryImpl(AuthorDAO authorDAO) {
    this.authorDAO = authorDAO;
  }

  @Override
  public List<AuthorPO> list(AuthorPO queryParam) {
    return this.authorDAO.findAll(querySpecification(queryParam));
  }

  @Override
  public AuthorPO findById(Long id) {
    return authorDAO.findById(id).orElseThrow(() -> new GeneralException("Author Not Found"));
  }

  @Override
  public void insert(AuthorPO authorPO) {
    authorDAO.save(authorPO);
  }

  @Override
  public void update(AuthorPO authorPO) {
    authorDAO.save(authorPO);
  }

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
