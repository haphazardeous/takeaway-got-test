package co.vcos.takeawaycodingchallenge.model;

import java.util.Objects;

public class PlayRequest {
    private Integer number;

    public PlayRequest() {}

    public PlayRequest(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayRequest that = (PlayRequest) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
