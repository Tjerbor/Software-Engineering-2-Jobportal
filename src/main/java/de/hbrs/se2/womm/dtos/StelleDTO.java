package de.hbrs.se2.womm.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class StelleDTO implements AbstractDTO {
    private Long stelleId;
    private String stelleTitel;
    private String stelleOrt;
    private String stelleBeschreibung;
    private String stelleWebsite;
    private UnternehmenDTO stelleUnternehmen;

}
