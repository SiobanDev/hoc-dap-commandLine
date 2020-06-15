package fr.houseofcode.dap.commandLine.mgw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** @author mgw **/
public class ServerUtils {
    //TODO MGW by Djer |JavaDoc| En g�n�rale on ne pr�cise pas de "author" sur un attribut/constantes (surtout si c'est le m�me que la classe). Tu pourrais indiquer � quoi sert cette constantes (le nommage �tant claire un simple "User agent sent to the server" serait suffisant).
    /** @author mgw **/
    private static final String USER_AGENT = "Mozilla/5.0";

    //TODO MGW by Djer |JavaDoc| Ta description et ton return n'est pas juste, cette m�thode ne renvoie PAS l'URL, elle **utilise** l'URL. En g�n�ral on �vite de pr�ciser les d�tails interne du code, sinon il faut continuellement mettre � jours la JavaDoc. "get the next event's from the server" serait plus juste.
    /**
     * get the next event's user's url.
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url //TODO MGW by Djer |JavaDoc| Dans ta JavaDoc n'indique pas "comment" c'est fait, mais "ce que" ca fait. "userkey to identify User" serait mieux. Donne des indicatio nsur el comment **uniquement** si l'appelant de la méthode **doit** le prendre en compte. Ici que le userkey soit utilis� dans **l'URL** n'est pas important pour l'appelant.
     * @throws IOException if the sent or received message is broken.
     */
    public String nextEvent(final String userKey) throws IOException {
        //TODO MGW by Djer |IDE| Supprime les TO-DO automatique une fois que tu les as trait�s.
        // TODO Auto-generated method stub
        String event = callServer("/calendar/nextEvent", userKey);
        return event;
    }

    /**
     * get the labels' user's url. //TODO MGW by Djer |JavaDoc| Ne renvoie pas une URL (cf commentaires m�thode "nextEvent(...)".
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url //TODO MGW by Djer |JavaDoc| N'indique pas le "comment", mais le "quoi" (cf commentaires m�thode "nextEvent(...)".
     * @throws IOException if the sent or received message is broken.
     */
    public String getLabels(final String userKey) throws IOException {
        //TODO MGW by Djer |IDE| Supprime les TO-DO automatique une fois que tu les as trait�s.
        // TODO Auto-generated method stub
        String labels = callServer("/email/labels", userKey);
        return labels;
    }

    /**
     * get the unread emails' user's url. //TODO MGW by Djer |JavaDoc| Ne renvoie pas une URL (cf commentaires m�thode "nextEvent(...)".
     * @return the url with the next event of the defined user if it exists
     * @param userKey allows a value for the user's parameter added to the url //TODO MGW by Djer |JavaDoc| N'indique pas le "comment", mais le "quoi" (cf commentaires m�thode "nextEvent(...)".
     * @throws IOException if the sent or received message is broken.
     */
    public String getUnreadedMail(final String userKey) throws IOException {
        //TODO MGW by Djer |IDE| Supprime les TO-DO automatique une fois que tu les as trait�s.
        // TODO Auto-generated method stub
        String unreadedMail = callServer("/email/nbUnread", userKey);
        return unreadedMail;
    }

    /**
     * get the next event. //TODO MGW by Djer |JavaDoc| Cette description n'est plus juste.
     * @return the url with the next event of the defined user if it exists //TODO MGW by Djer |JavaDoc| Ce return n'est plus juste.
     * @param url provide the service's url
     * @param userKey allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     */
    public String callServer(final String url, final String userKey) throws IOException {
        // HTTP GET request

        URL obj = new URL("http://localhost:8081" + url + "?userKey=" + userKey);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        //TODO MGW by Djer |Log4J| Evite les SysOut, ici une Log en debug serait mieux. En plus tu as d�ja configur�/utilis� Log4J dans ton Launcher.
        System.out.println("\nSending 'GET' request to URL : " + "http://localhost:8080" + url + "\n");
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //TODO MGW by Djer |Log4J| Evite les SysOut, ici une Log en debug serait mieux. En plus tu as d�ja configur�/utilis� Log4J dans ton Launcher.
        //print result
        System.out.println(response.toString());

        return response.toString();

    }

}
