package UMC6.GrowPalette.apiPayload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReasonDto {
    private final boolean isSuccess;
    private final String code;
    private final String message;
    public boolean getIsSuccess(){return isSuccess;}

}