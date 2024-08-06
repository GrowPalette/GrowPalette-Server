package UMC6.GrowPalette.domain.goal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    Page<Goal> findAllByOrderByCreatedAtDescGoalIdDesc(Pageable pageable);
    Page<Goal> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDescGoalIdDesc(String title, String content, Pageable pageable);
}
