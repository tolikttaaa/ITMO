package server;

import commands.FileHandler;
import mainClasses.MoominManager;
import moominClasses.Moomin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    private Vector<Moomin> moomins;
    private ServerSocket serverSocket;
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port, 0, InetAddress.getLocalHost());

        MoominManager manager = new MoominManager();
        if (manager.getMoomins().addAll(FileHandler.readFile("save.json"))) {
            System.out.println("Collection changed.");
        } else {
            System.out.println("Collection didn't changed.");
        }

        moomins = manager.getMoomins();

        System.out.println("Server started");
        System.out.println("IP: " + serverSocket.getLocalSocketAddress());
    }

    private void listen() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            ClientThread clientThread = new ClientThread(socket, moomins);
            //clientThread.start();
            clientThread.run();
        }
    }

    public static void main(String[] args) {
        int port = 1234;

        //By arguments
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            //By input stream
            Scanner scanner = new Scanner(System.in);
            System.out.print("Port: ");
            port = scanner.nextInt();
        }

        try {
            Server server = new Server(port);
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
