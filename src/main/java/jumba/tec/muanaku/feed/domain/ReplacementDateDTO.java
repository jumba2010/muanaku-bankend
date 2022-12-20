package jumba.tec.muanaku.feed.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReplacementDateDTO {

   @NotNull
   private Integer numberOfBags;

   @NotNull
   private Long batchId;

   @NotNull
   private Long companyId;


}
