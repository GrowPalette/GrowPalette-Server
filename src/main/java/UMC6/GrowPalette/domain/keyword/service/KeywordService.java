package UMC6.GrowPalette.domain.keyword.service;

import UMC6.GrowPalette.common.enums.Keyword;
import UMC6.GrowPalette.domain.keyword.KeywordRequest;

import java.util.List;

public interface KeywordService {
    List<Keyword> getAllKeywords();
    void validateKeywords(KeywordRequest request);
}
