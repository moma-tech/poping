package top.moma.example.domain.author.repository.persistence;

import java.util.List;
import org.springframework.stereotype.Service;
import top.moma.example.domain.author.repository.dao.AuthorDAO;
import top.moma.example.domain.author.repository.facade.AuthorRepository;
import top.moma.example.domain.author.repository.po.AuthorPO;
import top.moma.example.infrastructure.common.GeneralException;

/**
 * AuthorRepositoryImpl
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
    return this.authorDAO.findAll(AuthorPO.querySpecification(queryParam));
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
}
