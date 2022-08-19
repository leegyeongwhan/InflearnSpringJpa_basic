package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Period {
    private LocalDateTime starDate;
    private LocalDateTime endDate;




    public LocalDateTime getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDateTime starDate) {
        this.starDate = starDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
