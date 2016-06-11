package WBSEFinal.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;

/**
 * Returns weather data in json. Provider: Yahoo! weather
 *
 * Attributes:
 *
 */
public class WeatherFetcher extends HttpServlet {

    private static final String YQLHEADER = "https://query.yahooapis.com/v1/public/yql?q=";    // Header for querying Yahoo! Weather data.

    /* Types of query */
    private static final String FORECAST = "item.forecast";
    private static final String AIR = "wind, atmosphere";

    /* Constants */
    private static final double PRESSCONV = 33.8638985;  // Presure mesurement converter.

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String func = request.getParameter("func");
        if(func == null) func = ""; // Avoid NullPointer exception.
        
        switch (func) {
            
            case "forecast":
                
                response.getWriter().println(processQuery(FORECAST, "taipei"));
                break;
                
            
            case "air":

                response.getWriter().println(getAir("taipei"));
                break;
                
            default:
                
                response.getWriter().println("{}");
                break;
        }

    }

    /**
     * Process Yahoo! Weather query.
     *
     * @param type query type ex. forecast
     * @param location location ex. taipei
     * @return String query result
     */
    private String processQuery(String type, String location) throws UnsupportedEncodingException, MalformedURLException, IOException {

        /* Build raw query string */
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append(type);
        sb.append(" from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"");
        sb.append(location);
        sb.append("\") and u='c'");

        String fullQuery = YQLHEADER + java.net.URLEncoder.encode(sb.toString(), "UTF-8") + "&format=json";

        /* Get query result */
        java.net.URL queryUrl = new java.net.URL(fullQuery);
        java.io.InputStream is = queryUrl.openStream();

        JsonObject result;   // Result in Json format
        try (JsonReader resultReader = Json.createReader(new java.io.InputStreamReader(is))) {
            result = resultReader.readObject();
        }

        return result.getJsonObject("query").getJsonObject("results").toString();
    }

    /**
     * Get information of wind and pressure.
     *
     * @param location location ex. taipei
     * @return information of wind and pressure
     * @throws IOException
     */
    private String getAir(String location) throws IOException {

        /* Wind */
        double windDirection;
        double windSpeed;   // Km/h

        /* Air */
        double pressure;    // mBar

        JsonObject resTemp;

        try (JsonReader resReader = Json.createReader(new java.io.StringReader(processQuery(AIR, location)))) {
            /* Get query result */
            resTemp = resReader.readObject();
        }

        windDirection = Double.valueOf(resTemp.getJsonObject("channel")
                .getJsonObject("wind")
                .getString("direction"));  // Get wind direction

        windSpeed = Double.valueOf(resTemp.getJsonObject("channel")
                .getJsonObject("wind")
                .getString("speed"));   // Get wind speed (km/h)

        pressure = Math.round(Double.valueOf(resTemp.getJsonObject("channel")
                .getJsonObject("atmosphere")
                .getString("pressure")) / PRESSCONV);   // Get pressure (mbar)

        /* Build result json */
        JsonObject result = Json.createObjectBuilder()
                .add("direction", windDirection)
                .add("speed", windSpeed)
                .add("pressure", pressure)
                .build();

        return result.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
