package ru.learningandroid.calculator;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

public enum Theme {

    ONE(R.style.MyLightTheme, R.string.light, "light"),
    TWO(R.style.MyDarkTheme, R.string.dark, "dark");

    @StyleRes
    private final int theme;

    @StringRes
    private final int name;

    private String key;

    Theme(int theme, int name, String key) {
        this.theme = theme;
        this.name = name;
        this.key = key;
    }

    public int getTheme() {
        return theme;
    }

    public int getName() {
        return name;
    }

    public String getKey() {
        return key;
    }
}
