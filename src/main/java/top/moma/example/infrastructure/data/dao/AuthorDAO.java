package top.moma.example.infrastructure.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.moma.example.domain.author.repository.po.AuthorPO;

/**
 * AuthorDAO
 *
 * @version 1.0
 * @author Created by ivan at 10:53.
 */
public interface AuthorDAO
    extends JpaRepository<AuthorPO, Long>, JpaSpecificationExecutor<AuthorPO> {}
