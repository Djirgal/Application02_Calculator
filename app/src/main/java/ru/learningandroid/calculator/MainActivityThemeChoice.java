package ru.learningandroid.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class MainActivityThemeChoice extends AppCompatActivity {

    private final static String KEY = "CALCULATOR";

    private RadioButton radioButton1, radioButton2;
    private byte rbToCheck;//1 - radioButton1, 2 - radioButton2

    // Имя настроек
    private static final String SHARED_PREF = "CHOICE";

    // Имя параметра в настройках
    private static final String APP_THEME = "APP_THEME";
    //ourCodeStyle:
    protected static final int LIGHT_THEME = 0;//соответствует AppLightTheme
    protected static final int DARK_THEME = 1;//соответствует AppDarkTheme

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Устанавливать тему надо только до установки макета активити
        setTheme(getAppThemeByMode());
        //установка макета с заранее выбранным стилем
        setContentView(R.layout.activity_main_theme_choice);


        initMyRadioButtons();

        //установка завершения активити по кнопке
        Button btnReturn = findViewById(R.id.endActivity);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Метод finish() завершает активити
                finish();
            }
        });

        //установка соответствющего радио-баттона
        setRadioButtonChecked();
        initThemeChooser();
    }


    public void setRadioButtonChecked() {
        switch (rbToCheck){
            case 1:
                radioButton1.setChecked(true);
            case 2:
                radioButton2.setChecked(true);
            default:
                radioButton1.setChecked(false);
                radioButton1.setChecked(false);
        }
    }

    private void initMyRadioButtons() {

        radioButton1 = findViewById(R.id.radioButtonLightStyle);
        radioButton2 = findViewById(R.id.radioButtonDarkStyle);

        initRadioButton(findViewById(R.id.radioButtonLightStyle), LIGHT_THEME);
        initRadioButton(findViewById(R.id.radioButtonDarkStyle), DARK_THEME);

    }

    private void initRadioButton(View button, final int codeStyle){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                switch (rb.getId()) {
                    case R.id.radioButtonLightStyle:
                        setTheme(codeStyleToStyleId(getCodeStyle(LIGHT_THEME)));
                        break;
                    case R.id.radioButtonDarkStyle:
                        setTheme(codeStyleToStyleId(getCodeStyle(DARK_THEME)));
                        break;
                    default:
                        setTheme(codeStyleToStyleId(getCodeStyle(LIGHT_THEME)));
                }
                // сохраним настройки
                setAppTheme(codeStyle);
                // пересоздадим активити, чтобы тема применилась
                recreate();

            }
        });
    }
    private int getAppThemeByMode() {//возвращает id стиля и сохраняет в rbToCheck какой радио-баттон должен быть установлен
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're using the light theme
                rbToCheck=1;
                return codeStyleToStyleId(getCodeStyle(LIGHT_THEME));
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're using dark theme
                rbToCheck=2;
                return codeStyleToStyleId(getCodeStyle(DARK_THEME));
            default:
                return codeStyleToStyleId(getCodeStyle(LIGHT_THEME));
        }
    }

    private int getAppTheme(int codeStyle) {

        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int ourCodeStyle){
        switch(ourCodeStyle){
            case LIGHT_THEME:
                return R.style.MyLightTheme;
            case DARK_THEME:
                return R.style.MyDarkTheme;
            default:
                return R.style.MyLightTheme;
        }
    }

    private void initThemeChooser() {
        initRadioButton(findViewById(R.id.radioButtonLightStyle),
                LIGHT_THEME);
        initRadioButton(findViewById(R.id.radioButtonDarkStyle),
                DARK_THEME);
        RadioGroup rg = findViewById(R.id.radioButtons);
        ((MaterialRadioButton)rg.getChildAt(getCodeStyle(LIGHT_THEME))).setChecked(true);
    }

}