package com.mycompany.tennis.core.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @Utilisation
 * Pour utiliser cette classe, vous pouvez appeler HibernateUtil.getSessionFactory() à partir de n'importe quelle partie de votre application où vous avez besoin d'une SessionFactory.
 * Vous pouvez ensuite utiliser cette SessionFactory pour obtenir des objets Session et interagir avec la base de données Hibernate.
 * @Remarque
 * Assurez-vous que le fichier hibernate.cfg.xml est bien configuré avec les paramètres appropriés pour votre base de données et votre environnement.
 * Cette classe est un moyen courant de gérer SessionFactory dans une application Hibernate, mais il existe d'autres méthodes de configuration et de gestion des sessions, en fonction des besoins et des préférences de l'application.
 */
public class HibernateUtil {
	
	/** 
	 * SessionFactory
	 * SessionFactory est une interface dans Hibernate qui représente une usine de sessions, utilisée pour obtenir des objets Session.
	 * Une SessionFactory est créée une seule fois pour toute l'application, généralement au démarrage de l'application.
	 */
    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * buildSessionFactory()
     * @implNote
     * Cette méthode privée est chargée de créer une instance de SessionFactory.
     * Elle utilise une configuration Hibernate définie dans un fichier hibernate.cfg.xml pour construire la SessionFactory.
     * En cas d'échec lors de la création de la SessionFactory, elle génère une exception.
     */
    private static SessionFactory buildSessionFactory() {
    	
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     * getSessionFactory()
     * @implNote
     * Cette méthode publique fournit l'instance de SessionFactory créée par buildSessionFactory().
     * C'est la méthode que les autres classes de l'application utilisent pour obtenir une instance de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    /**
     * getCurrentSession()
     * @implNote
     * La méthode getCurrentSession() permet d'obtenir la session actuelle associée au thread en cours. 
     */
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    /**
     ** getSession()
     * @implNote La méthode getSession() ouvre et retourne une nouvelle session à chaque appel. 
     * Cela garantit que chaque opération dans votre application utilise une session distincte 
     * et évite les problèmes liés à la persistance des objets dans une session pendant une longue période.
     * @return Session
     * 
     */
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
    /**
     * closeSession(Session session)
     * @implNote La méthode closeSession() vérifie d'abord si la session est ouverte avant de la fermer, 
     * pour éviter une IllegalStateException.
     * @param session
     */
    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
