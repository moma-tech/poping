package top.moma.example.domain.author.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.moma.example.domain.author.repository.po.AuthorPO;

public interface AuthorDAO
    extends JpaRepository<AuthorPO, Long>, JpaSpecificationExecutor<AuthorPO> {}
