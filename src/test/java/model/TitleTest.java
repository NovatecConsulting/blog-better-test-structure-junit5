package model;

import static java.lang.reflect.Modifier.isPrivate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.expectThrows;

import java.lang.reflect.Constructor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("[Unit Test] Title Value Object")
public class TitleTest {

    @Test
    @DisplayName("title's value is used as toString()")
    void valueUsedForToString() {
        Title title = Title.of("My Title");
        assertThat(title.toString()).isEqualTo("My Title");
    }

    @Test
    @DisplayName("titles can't be initialized directly")
    void titleCantBeInitializedDirectly() {
        for (Constructor<?> constructor : Title.class.getConstructors()) {
            int modifiers = constructor.getModifiers();
            assertThat(isPrivate(modifiers)).isTrue();
        }
    }

    @Nested
    @DisplayName("titles can't be blank")
    public class TitleCantBeBlank {

        @Test
        @DisplayName("null")
        void nullValue() {
            titleMustNotBeBlank(null);
        }

        @Test
        @DisplayName("empty")
        void emptyValue() {
            titleMustNotBeBlank("");
        }

        @Test
        @DisplayName("space")
        void spaceValue() {
            titleMustNotBeBlank(" ");
        }

        @Test
        @DisplayName("tab")
        void tabValue() {
            titleMustNotBeBlank("\t");
        }

        @Test
        @DisplayName("line break")
        void lineBreakValue() {
            titleMustNotBeBlank("\n");
        }

        void titleMustNotBeBlank(String value) {
            assertThat(expectThrows(IllegalArgumentException.class, () -> {
                Title.of(value);
            })).hasMessage("[value] must not be blank!");
        }

    }

    @Nested
    @DisplayName("titles are trimmed")
    public class TitleAreTrimmed {

        @Test
        @DisplayName("spaces")
        void spaces() {
            Title title = Title.of(" trim me ");
            assertThat(title.getValue()).isEqualTo("trim me");
        }

        @Test
        @DisplayName("tabs")
        void tabs() {
            Title title = Title.of("\ttrim me\t");
            assertThat(title.getValue()).isEqualTo("trim me");
        }

        @Test
        @DisplayName("line breaks")
        void lineBreaks() {
            Title title = Title.of("\ntrim me\n");
            assertThat(title.getValue()).isEqualTo("trim me");
        }

    }

    @Nested
    @DisplayName("equality is based on value")
    public class Equality {

        @Test
        @DisplayName("titles with same value are equal")
        void titlesWithSameValuesAreEqual() {
            Title foo1 = Title.of("foo");
            Title foo2 = Title.of("foo");
            assertThat(foo1).isEqualTo(foo2);
        }

        @Test
        @DisplayName("titles with different values are not equal")
        void titlesWithDifferentValuesAreNotEqual() {
            Title foo = Title.of("foo");
            Title bar = Title.of("bar");
            assertThat(foo).isNotEqualTo(bar);
        }

    }

}
