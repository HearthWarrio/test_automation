package ru.yandex;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigureProperties {
    protected static FileInputStream fileInputStream;
    // 1. в таком случае лучше переменную пропертей еще и final делать и сразу инициализировать (new Properties()). также есть прекрасные библиотечки, 
    // упрощающие в разы управление пропертями: http://owner.aeonbits.org/, например. или Apache configuration, но она сложнее в использовании. ее killer-feature - возможность перезаписи
    // конфига прямо из работающего приложения, но это не везде нужно.
    // 2. поля классов в большинстве случаев можно делать приватными. protected тебе потребуется только, если ты соберешься наследовать этот класс
    protected static Properties PROPERTIES;
    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");

            PROPERTIES = new Properties();

            PROPERTIES.load(fileInputStream);

        }

        catch (IOException e) {
// 3. обработка исключения?
            e.printStackTrace();

        }

        finally {
// 4. в 7 джаве появилась конструкция try-with-resources, ее использовать удобнее и безопаснее
            if (fileInputStream != null)

                try {

                    fileInputStream.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
        }
    }


    public static String getProperty(String key) {

        return PROPERTIES.getProperty(key);
    }
}
