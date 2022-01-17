package at.exceptions;

/**
 * Класс содержит методы для работы с ожиданием
 */
public abstract class WaitUtil {

    /**
     * Жесткое ожидание
     *
     * @param millis Миллисекунды
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}