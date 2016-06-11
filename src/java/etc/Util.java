/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

/**
 *
 * @author H-Alice
 */
public class Util {

    /**
     * Convert Yahoo! weather code to string.
     *
     * @param code
     * @return
     */
    public static String codeToWeather(int code) {

        switch (code) {

            /* Sunny day */
            case 32:
            case 34:
            case 36:
            case 25:

                return "sunny";

            /* Clear night */
            case 31:
            case 33:

                return "clear night";

            /* Partly coludy */
            case 29:

                return "partly cloudy night";

            case 30:
            case 44:

                return "partly cloudy day";

            /* Cloudy */
            case 26:
            case 27:
            case 28:

                return "cloudy";

            /* Rain */
            case 3:
            case 4:
            case 5:
            case 6:
            case 10:
            case 11:
            case 12:
            case 35:
            case 37:
            case 38:
            case 39:
            case 40:
            case 45:
            case 47:

                return "rain";

            /* Windy */
            case 0:
            case 1:
            case 2:
            case 23:
            case 24:

                return "windy";

            /* Foggy */
            case 19:
            case 20:
            case 21:
            case 22:

                return "foggy";

            /* Drizzle */
            case 9:

                return "drizzle";

            /* Snow */
            case 7:
            case 8:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 41:
            case 42:
            case 43:
            case 46:

                return "snow";

            /* N/A */
            case 3200:
            default:

                return "sunny"; // Give it a sun if n/a.

        }

    }

}
