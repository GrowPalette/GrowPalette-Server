package UMC6.GrowPalette.domain.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Page<Activity> findAllByOrderByCreatedAtDescActivityIdDesc(Pageable pageable);
    Page<Activity> findAllByTitleContainingIgnoreCaseOrDetailTitleContainingIgnoreCaseOrDetailContentContainingIgnoreCaseOrderByCreatedAtDescActivityIdDesc(String title, String detailTitle, String detailContent, Pageable pageable);
}


