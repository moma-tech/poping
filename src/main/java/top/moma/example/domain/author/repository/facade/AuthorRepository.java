package top.moma.example.domain.author.repository.facade;

import java.util.List;
import top.moma.example.domain.author.repository.po.AuthorPO;

/**
 * AuthorRepository
 *
 * @version 1.0
 * @author Created by ivan at 17:29.
 */
public interface AuthorRepository {

  /**
   * list
   *
   * @return java.util.List<top.moma.example.domain.author.repository.po.AuthorPO>
   * @author Created by ivan
   * @since 2023/6/14 17:30
   */
  List<AuthorPO> list(AuthorPO queryParam);

  /**
   * findById
   *
   * @param id id
   * @return top.moma.example.domain.author.repository.po.AuthorPO
   * @author Created by ivan
   * @since 2023/6/14 17:30
   */
  AuthorPO findById(Long id);

  /**
   * insert
   *
   * @param authorPO authorPO
   * @author Created by ivan
   * @since 2023/6/14 17:30
   */
  void insert(AuthorPO authorPO);

  /**
   * update
   *
   * @param authorPO authorPO
   * @author Created by ivan
   * @since 2023/6/14 17:30
   */
  void update(AuthorPO authorPO);
}
