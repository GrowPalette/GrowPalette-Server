package UMC6.GrowPalette.domain.keyword;


import UMC6.GrowPalette.common.enums.Keyword;

import java.util.List;

public class KeywordRequest {
    private List<Keyword> selectedKeywords;

    public List<Keyword> getSelectedKeywords() {
        return selectedKeywords;
    }

    public void setSelectedKeywords(List<Keyword> selectedKeywords) {
        this.selectedKeywords = selectedKeywords;
    }
}
