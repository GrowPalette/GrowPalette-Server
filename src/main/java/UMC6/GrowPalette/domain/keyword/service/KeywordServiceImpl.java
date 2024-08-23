package UMC6.GrowPalette.domain.keyword.service;

import UMC6.GrowPalette.common.enums.Keyword;
import UMC6.GrowPalette.domain.keyword.KeywordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    public List<Keyword> getAllKeywords() {
        return List.of(Keyword.values());
    }
    public void validateKeywords(KeywordRequest request) {
        if (request.getSelectedKeywords() == null || request.getSelectedKeywords().size() > 5) {
            throw new IllegalArgumentException(("키워드는 최대 5개까지 선택 가능합니다."));
        }
    }
}
