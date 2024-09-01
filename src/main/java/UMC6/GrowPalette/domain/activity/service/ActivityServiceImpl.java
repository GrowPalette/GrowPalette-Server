package UMC6.GrowPalette.domain.activity.service;

import UMC6.GrowPalette.domain.activity.Activity;
import UMC6.GrowPalette.domain.activity.ActivityConverter;
import UMC6.GrowPalette.domain.activity.ActivityRepository;
import UMC6.GrowPalette.domain.activity.dto.ActivityRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    @Override
    public Activity createActivity(ActivityRequestDto.CreateActivityDto request) {
        Activity activity = ActivityConverter.toActivityCreate(request);
        return activityRepository.save(activity);
    }

    @Override
    public Activity createDetailActivity(Long activityId, ActivityRequestDto.CreateDetailDto request) {
        Activity createDetActivity = activityRepository.findById(activityId).get();
        createDetActivity.createDetailActivity(request);
        return createDetActivity;
    }
    @Override
    public Activity updateActivity(Long activityId, ActivityRequestDto.UpdateDto request) {
        Activity updateActivity = activityRepository.findById(activityId).get();
        updateActivity.updateActivity(request);
        return updateActivity;
    }

    @Override
    public void deleteActivity(Long activityId) {
        Activity deleteActivity = activityRepository.findById(activityId).get();
        activityRepository.delete(deleteActivity);
    }

    @Override
    public Activity getActivity(Long activityId) {
        return activityRepository.findById(activityId).get();
    }

    @Override
    public Page<Activity> getAllActivitiesBySearch(int page, int size, Optional<String> optSearch) {
        PageRequest request = PageRequest.of(page, size);

        // 검색어가 존재한다면
        if (optSearch.isPresent()) {
            String search = optSearch.get();
            // title에 검색어 포함하는 (대소문자 구분 X) 활동들을 최신 순으로 페이징 조회
            return activityRepository.findAllByTitleContainingIgnoreCaseOrDetailTitleContainingIgnoreCaseOrDetailContentContainingIgnoreCaseOrderByCreatedAtDescActivityIdDesc(search, search, search, request);
        }

        return activityRepository.findAllByOrderByCreatedAtDescActivityIdDesc(request);
    }
}
