package msa.board.view.repository;

import msa.board.view.entity.ArticleViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleViewCountBackUpRepository extends JpaRepository<ArticleViewCount, Long> {


}
