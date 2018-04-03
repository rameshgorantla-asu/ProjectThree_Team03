package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.StringBuilder;
import java.util.Random;

/**
* Server is an embeddeble socket based server. It accepts one client at a time.
* A conversation with the server is initiated by opening a request and sending
* a newline delimited string containing an integer. This integer is the "channel"
* directive that all clients must provide.
*
* The server produces values at a rate according to its own frequency (say, four seconds).
* If client wish to consume at their own frequency then they must buffer the connection'
* and consume accordingly.
*/
public class Server implements Runnable {

    private int port;
    private volatile boolean running;

    /**
    * Server class constructor.
    *
    * @param port the port to which this server will bind.
    */ 
    public Server(int port) {
        this.port = port;
        this.running = false;
    }

    /**
    * Binds to the port provided in the constructor and enters into an
    * event loop
    * 
    * If not called via a Thread object, this method will block indefinitely.
    */
    private void startServer() {
        Socket request;
        PrintWriter response;
        ServerSocket server;
        try {
            server = new ServerSocket(this.port);
        } catch (Exception e) {
            ServerConsole.getInstance().print("Failed to start the server on port " + new Integer(this.port).toString());
            ServerConsole.getInstance().print(e.toString());
            return;
        }
        this.running = true;
        ServerConsole.getInstance().print("Server is started on port " + new Integer(this.port).toString());
        while (this.running) {
            try {
                request = server.accept();
                response = new PrintWriter(request.getOutputStream(), true); // true for auto-flush on println
            } catch (Exception e) {
                ServerConsole.getInstance().print("Failed to accept connection.");
                continue;
            }
            try {
                this.serviceClient(request, response);
            } catch (Exception e) {
                ServerConsole.getInstance().print("Failed to service client.");
                ServerConsole.getInstance().print(e.toString());
                try {
                    response.println("ERROR: " + e.toString());
                } catch (Exception err) {
                    // give up trying to get the client feedback.
                }
            } finally {
                try {
                    request.close();
                } catch (Exception e) {
                    ServerConsole.getInstance().print("Failed to close client connection.");
                }
            }
        }
        ServerConsole.getInstance().print("Server is Stopped");
        try {
            server.close();
        } catch (Exception e) {
            ServerConsole.getInstance().print(e.toString());
        }
    }

    /**
    * Services a single, persistent, client. Blocking.
    *
    * @param request the raw socket connection to the client.
    * @param response a writer to the client. 
    */
    private void serviceClient(Socket request, PrintWriter response) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        int channel = Integer.parseInt(in.readLine());
        Random rand = new Random();
        while (this.running) {
            if (in.ready() && "stop".equals(in.readLine())) {
                break;
            }
            StringBuilder integers = new StringBuilder();
            for (int i = 0; i < channel; i++) {
                integers.append(new Integer(rand.nextInt(ServerConstants.DEFAULT_MAX - ServerConstants.DEFAULT_MIN + 1) + ServerConstants.DEFAULT_MIN).toString());
                if (i < channel - 1) {
                    // Don't append a trailing space on the last entry.
                    integers.append(" ");
                }
            }
            String resp = integers.toString();
            response.println(resp);
            ServerConsole.getInstance().print("Sent response: " + resp);
            Thread.sleep(1000/ServerConstants.DEFAULT_FREQ);
        }
        ServerConsole.getInstance().print("Stopping number server");
    }

    /**
    * Passthrough function for honoring the Runnable interface in case
    * client code wishes to embed this server rather than using
    * the server's event loop.
    */
    @Override
    public void run() {
        startServer();
    }

    /**
    * Stops a running server. Safe to call even if the server is not running.
    */
    public synchronized void stop() {
        this.running = false;
    }

    /**
    * Convenience function for client code that wish to embed this
    * server into their application.
    *
    * @return    A Server object that is the handle to the running thread.
    */
    public static Server createServerThread() {
        Server server = new Server(ServerConstants.PORT_NUMBER);
        new Thread(server).start();
        return server;
    }

}
