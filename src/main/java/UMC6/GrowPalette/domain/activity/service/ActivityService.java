package UMC6.GrowPalette.domain.activity.service;

import UMC6.GrowPalette.domain.activity.Activity;
import UMC6.GrowPalette.domain.activity.dto.ActivityRequestDto;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ActivityService {
    Activity createActivity(ActivityRequestDto.CreateActivityDto activity);

    Activity createDetailActivity(Long activityId, ActivityRequestDto.CreateDetailDto request);

    Activity updateActivity(Long activityId, ActivityRequestDto.UpdateDto activity);

    void deleteActivity(Long activityId);

    Activity getActivity(Long activityId);

    Page<Activity> getAllActivitiesBySearch(int page, int size, Optional<String> optSearch);
}
