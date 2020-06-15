package fr.houseofcode.dap.commandLine.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** @author mgw **/
public final class CmdLineLauncher {

    //TODO MGW by Djer |Command Line| Ton singleton n'est pas utile, car il n'est pas utils�  : Java lance juste "main(...)" et cette m�thode n'utilise pas ton singleton.
    //Ce n'est pas i�al de "tout cod� dans du code static (comme main(...)" c'est pour cela que l'on a la classe "Serverutils".
    //Si tu veux encore limiter le code il te faudrait une autre classe (par exemmple "Appli") qui contiendrait tout ton code qui est actuellement dans la m�thdoe "main(...)" dans une m�thode "start" (par exemple).
    //la m�thdoe Main de ton Launcher ne ferait que cr�er une instance de "Appli" puis executer la m�thdoe "start()".
    // On trovue souvent sur internet des "Launcher" avec cette m�thdoe "start" dans le m�me fichier, ce qui est une bonne fa�on de faire, mais n'aide pas � comprendre le fonctionnement (surtout quand on d�bute).
    /** Singleton. */
    private static CmdLineLauncher instance;

    /**
     * is an empty private constructor (cf.Singleton pattern).
     **/
    private CmdLineLauncher() {
        LOG.debug("CmdLineLauncher's Constructor");
    }

    /**
     * create only one new instance of CmdLineLauncher (Singleton).
     * @return the only object CmdLineLauncher (cf. Singleton).
     **/
    public static CmdLineLauncher getInstance() {
        if (instance == null) {
            instance = new CmdLineLauncher();
        }
        return instance;
    }

    //TODO MGW by Djer |JavaDoc| Ce commentaire n'est pas juste.
    /**
    * @param args
    */
    //TODO MGW by Djer |POO| Cette constante devrait �tre en d�but de classe. Ordre attendu : constantes, attributs, intialisateurs statics, constrcuteurs, m�thodes m�tier, m�thodes utilitaires (toString, hashCode,..), getter/setters
    private static final Logger LOG = LogManager.getLogger();

    /**
    * @param args is the external parameters.
    * @throws IOException if the sent or received message is broken.
    * @throws GeneralSecurityException if there's a security failure.
    */

    public static void main(final String[] args) throws IOException, GeneralSecurityException {
        ServerUtils su = new ServerUtils();
        LOG.debug("Debut du main avec comme arguments :  " + args);

        //TODO MGW by Djer |Command Line| Pour Eviter un "ArrayOutOfBoundException" tu devrais v�rifier qu'il y a le bon nombre de param�tres (+message d'erreur utilsiateur)
        String choixUserKey = args[0];
        String nbemails = su.getUnreadedMail(choixUserKey);
        String displayLabel = su.getLabels(choixUserKey);
        String allEvents = su.nextEvent(choixUserKey);

        int n = Integer.parseInt(args[1]);
        switch (n) {
        case 1:
            LOG.debug("Connexion de l'utilisateur à son calendrier : ");
            System.out.println("Tiens toi pret pour ton prochain event  ^^\n" + allEvents);
            break;

        case 2:
            LOG.debug("Connexion de l'utilisateur à son comtpe email : ");
            System.out.println("Vous avez : " + nbemails + " email(s) non lu(s) \n");
            break;

        case 3:
            LOG.debug("Connexion de l'utilisateur à sa classe de label :");
            System.out.println(displayLabel);
            break;

        case 4:
            LOG.debug("Affichage de toutes les données de l'utilisateur");
            System.out.println("Tiens toi pret pour ton prochain events  ^^\n" + allEvents);
            System.out.println("Vous avez : " + nbemails + " email(s) non lu(s) \n");
            System.out.println("Voici la liste de vos labels" + displayLabel + " message \n");
            break;

        default:
            System.out.println("Vous n'avez rien à afficher !");
        }
    }

}
