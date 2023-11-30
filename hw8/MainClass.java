import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Disk
    // extends Thread
{
    static final int NUM_SECTORS = 2048;
    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
    static final int DISK_DELAY = 800;
    Disk()
    {
    }
    void write(int sector, StringBuffer data)  // call sleep
    {
    }
    void read(int sector, StringBuffer data)   // call sleep
    {
    }
}

class Printer
    // extends Thread
{
    Printer(int id)
    {
    }

    void print(StringBuffer data)  // call sleep
    {
    }
}

class PrintJobThread
    extends Thread
{
    StringBuffer line = new StringBuffer(); // only allowed one line to reuse for read from disk and print to printer

    PrintJobThread(String fileToPrint)
    {
    }

    public void run()
    {
    }
}

class FileInfo
{
    int diskNumber;
    int startingSector;
    int fileLength;
}

class DirectoryManager
{
    // private Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();

    DirectoryManager()
    {
    }

    void enter(StringBuffer fileName, FileInfo file)
    {
    }

    FileInfo lookup(StringBuffer fileName)
    {
        return null;
    }
}

class ResourceManager
{
}

class DiskManager
{
}

class PrinterManager
{
}

class UserThread
    extends Thread
{
    FileInputStream inputStream;
    BufferedReader myReader;
    UserThread(int id) // my commands come from an input file with name USERi where i is my user id
    {
        try{
            inputStream = new FileInputStream("USER" + id);
        }
        catch(Exception e){
            e.getMessage();
        }
    }

    public void run()
    {
        try{
            myReader = new BufferedReader(new InputStreamReader(inputStream));
            String[] tokens;
            for(String line; (line = myReader.readLine()) != null; ){
                tokens = line.split(" ");
                switch(tokens[0]){
                    case ".save":
                        
                        break;
                    case ".end":
                        break;
                    case ".print":
                        break;
                    default:
                        //System.out.println("Unknown CMD");
                        break;
                }
                //writes out all the user input file here
                //System.out.println(line);
            }
        }
        catch(Exception e){e.getMessage();}
    }
}


public class MainClass
{
    static UserThread[] users;
    static Printer[] printers;
    static Disk[] disks;
    public static void main(String args[])
    {
        initArrays(args);
        for (int i=0; i<args.length; ++i)
            System.out.println("Args[" + i + "] = " + args[i]);

        System.out.println("*** 141 OS Simulation ***");
        startUserThreads(users);
    }

    private static void initArrays(String args[]){
        users = new UserThread[Integer.parseInt(args[0])];
        for(int i = 0; i < users.length; i++){
            users[i] = new UserThread(i);
        }
        printers = new Printer[Integer.parseInt(args[1])];
        for (int i = 0 ; i < printers.length; i++ ){
            printers[i] = new Printer(i); 
        }
        disks = new Disk[Integer.parseInt(args[2])];
        for (int i = 0 ; i < disks.length; i++){
            disks[i] = new Disk();
        }
    }

    private static void startUserThreads(UserThread[] users){
        for(int i = 0 ; i < users.length; i++){
            users[i].start();
        }
    }
}
