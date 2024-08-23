package UMC6.GrowPalette.domain.keyword;

import UMC6.GrowPalette.common.enums.Keyword;
import UMC6.GrowPalette.domain.keyword.service.KeywordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keywords")
@RequiredArgsConstructor
public class KeywordController {
    private final KeywordServiceImpl keywordService;

    @GetMapping
    public List<Keyword> getAllKeywords() {
        return keywordService.getAllKeywords();
    }

    @PostMapping("/select")
    public void selectKeywords(@RequestBody KeywordRequest request) {
        keywordService.validateKeywords(request);
    }

}
