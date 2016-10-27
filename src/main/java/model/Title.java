package model;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Title {

    @Getter
    private final String value;

    public static Title of(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("[value] must not be blank!");
        }
        return new Title(value.trim());
    }

    @Override
    public String toString() {
        return value;
    }

}
