import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.lang.StringBuffer;

class Disk
    // extends Thread
{
    static final int NUM_SECTORS = 2048;
    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];
    //CHANGE THIS VALUE FOR GRADESCOPE
    static final int DISK_DELAY = 0;
    //
    private int nextFreeSector = 0;
    private int currBeginSector = 0;
    private int currFileSz = 0;
    //init all the string buffers
    Disk()
    {
    }
    //read and write just copy the data from the string buffer
    //write takes from stringbuffer data into disk
    void write(int sector, StringBuffer data)  // call sleep
    {
        nextFreeSector++;
        currFileSz++; 
        System.out.println(data);
        sectors[sector] = new StringBuffer(data);
    }
    //deep copies the data from disk into stringbuffer data 
    void read(int sector, StringBuffer data)   // call sleep
    {
    }

    //
    void copyData(StringBuffer data){
        
    }

    int getNextFreeSector(){
        return nextFreeSector;
    }
    int getCurrFileSz(){
        return currFileSz;
    }
    int getCurrBeginSector(){
        return currBeginSector;
    }
    void resetCurrFileSz(){
        currFileSz = 0;
    }
    void updateCurrBeginSector(){
        currBeginSector = nextFreeSector;
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
    String fName;
    
    PrintJobThread(String fileToPrint)
    {
        fName = fileToPrint;
    }

    public void run()
    {
        int currPrinter = MainClass.printManage.request();
        //if continue we have a printer
        //read a sector from disk and send to printer here
        
    }
}

class FileInfo
{
    FileInfo(int diskNum, int startSector, int fileLen){
        this.diskNumber = diskNum;
        this.startingSector = startSector;
        this.fileLength = fileLen;
    }
    int diskNumber;
    int startingSector;
    int fileLength;
}

//can convert to strings here
//on END call directory manager so we know how long the file iss
class DirectoryManager
{
    private Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();

    DirectoryManager()
    {
    }
    //user thread will call enter
    void enter(StringBuffer fileName, FileInfo file)
    {
        T.put(fileName.toString(), file);
    }

    //printjobthread will call lookup
    FileInfo lookup(StringBuffer fileName)
    {
        return T.get(fileName.toString());
    }
}

class ResourceManager
{
    boolean isFree[];
    ResourceManager(int numberOfItems) {
        isFree = new boolean[numberOfItems];
        for (int i=0; i<isFree.length; ++i){
            isFree[i] = true;
        }
    }
    synchronized int request() {
        while (true) {
            for (int i = 0; i < isFree.length; ++i){
                if ( isFree[i] ) {
                    isFree[i] = false;
                    return i;
                }
            }
            try{
                this.wait();
            }
            catch(InterruptedException e) {e.printStackTrace();}
        }
    }   
    synchronized void release( int index ) {
        isFree[index] = true;
        this.notify(); // let a blocked thread run
    }
}
//diskmanager contain the directory manager
class DiskManager extends ResourceManager
{
    //ALSO KEEPS TRACK OF NEXT SECTOR, NOT SURE OF IMPLEMENTATION SAVE FOR HW9
    private DirectoryManager dirManager = new DirectoryManager();
    DiskManager(int numDisks){
        super(numDisks);
    }
    DirectoryManager getDirManager(){
        return dirManager;
    }
    public void tester(){
    }
}

//derived from resource manager
class PrinterManager extends ResourceManager 
{
    PrinterManager(int numPrinters){
        super(numPrinters);
    }

}
class UserThread
    extends Thread
{
    FileInputStream inputStream;
    BufferedReader myReader;
    //consider removing not sure where to put the return of diskmanager
    int usedDisk;
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
        }
        catch(Exception e){e.getMessage();}
        
        //possibly need to account for if user inputs commands out of order
        try{
            String fName = "";

            //CURRERNTLY HARD CODED -- NEED TO FIX THIS ASAP, CURR DISK MAY NOT NECESSARILY BE 0 FOR FUTURE THREADS
            int currDisk = 0;
            //****************** consider setting it to OOB value to catch exception
            for(String line; (line = myReader.readLine()) != null; ){
                if(line.startsWith(MainClass.SAVE_COMMAND)){
                    fName = line.substring(MainClass.SAVE_COMMAND.length()+1); 
                    currDisk = MainClass.diskManage.request();
                }
                else if(line.startsWith(fName)){
                    StringBuffer lines = new StringBuffer(line);

                    //currently out of order, we're having the UserThread sleep instead of the disk
                    this.sleep(MainClass.disks[currDisk].DISK_DELAY);

                    MainClass.disks[currDisk].write(MainClass.disks[currDisk].getNextFreeSector(), lines);

                }
                else if(line.startsWith(MainClass.END_COMMAND)){
                    FileInfo currFile = new FileInfo(currDisk, MainClass.disks[currDisk].getCurrBeginSector(), MainClass.disks[currDisk].getCurrFileSz());
 
                    userDataPrinter(currDisk);
                 
                    //CREATE CONFIG
                    MainClass.diskManage.getDirManager().enter(new StringBuffer(fName), currFile);
                    MainClass.disks[currDisk].resetCurrFileSz();
                    MainClass.disks[currDisk].updateCurrBeginSector();
                    //
                    MainClass.diskManage.release(currDisk);
                    System.out.println("Beginning of new sector: " + MainClass.disks[currDisk].getCurrBeginSector());

                }
                else if(line.startsWith(MainClass.PRINT_COMMAND)){
                    fName = line.substring(MainClass.PRINT_COMMAND.length()+1);
                    PrintJobThread currPThread = new PrintJobThread(fName);
                    currPThread.start();
                    break;
                }

            }
        }
        catch(Exception e){e.getMessage();}
    }
    public void userDataPrinter(int currDisk){
            System.out.println("Beginning sector before write: " + MainClass.disks[currDisk].getCurrBeginSector());
            System.out.println("NextFreeSector after write: " + MainClass.disks[currDisk].getNextFreeSector());
            System.out.println("Size of current file: " + MainClass.disks[currDisk].getCurrFileSz());
    }
}


// System.out.println("ignore");
// switch(tokens[0]){
//     case ".save": 
//         //once thread unblocks can move forward anyway
//         //if thread blocks we stay here
//         //can write now
//         int currDisk = MainClass.diskManage.request();
//         break;
//     case ".end":
//         break;
//     case ".print":
//         break;
//     default:
//         //System.out.println("Unknown CMD");
//         break;
// }
 //writes out all the user input file here
 //System.out.println(line);

public class MainClass
{
    public static final String SAVE_COMMAND = ".save";
    public static final String PRINT_COMMAND = ".print";
    public static final String END_COMMAND = ".end";
    
    static UserThread[] users;
    static Printer[] printers;
    static Disk[] disks;
    static DiskManager diskManage;
    static PrinterManager printManage;

    public static void main(String args[])
    {
        initArrays(args);
        for (int i=0; i<args.length; ++i)
            System.out.println("Args[" + i + "] = " + args[i]);

        System.out.println("*** 141 OS Simulation ***");
        
        initManagers(args);
        
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
    //inputs available amount of hardware (boolean array)
    private static void initManagers(String args[]){
        diskManage = new DiskManager(Integer.parseInt(args[1]));
        printManage = new PrinterManager(Integer.parseInt(args[2]));
    }
}
