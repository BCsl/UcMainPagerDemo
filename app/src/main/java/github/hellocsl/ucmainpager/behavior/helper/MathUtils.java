package github.hellocsl.ucmainpager.behavior.helper;

/**
 * Copy from Android design library
 * Created by chensuilun on 16/7/24.
 */
class MathUtils {

    static int constrain(int amount, int low, int high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

    static float constrain(float amount, float low, float high) {
        return amount < low ? low : (amount > high ? high : amount);
    }

}

