//package com.example.authentication.authentication;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class AuthServer {
//    private static final Logger LOGGER = Logger.getLogger(AuthServer.class.getName());
//
//    public static void main(String[] args) {
//        startRestServer();
//    }
//
//    private static void startRestServer(){
//        ServletContextHandler context = new
//                ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//        Server jettyServer = new Server(8091);
//        jettyServer.setHandler(context);
//        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//        // Tells the Jersey Servlet which REST service/class to load.
//        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
//                authserver.controllers.AuthController.class.getCanonicalName());
//
//        try {
//            jettyServer.start();
//            LOGGER.log(Level.INFO,"Rest server started on port 8091!");
//            jettyServer.join();
//        } catch (Exception e){
//            LOGGER.log(Level.SEVERE,e.toString(), e);
//        }
//    }
//}
