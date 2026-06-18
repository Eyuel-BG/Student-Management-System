import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Student> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new File("data").mkdir(); 
        new File("data/backup").mkdir();
        
        try {
            File txtData = new File("data/students.txt");
            if (txtData.exists()) {
                Scanner fs = new Scanner(txtData);
                while(fs.hasNextLine()) {
                    String line = fs.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] p = line.split(",");
                    if (p.length == 4) {
                        list.add(new Student(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3])));
                    }
                }
                fs.close();
            }
        } catch (Exception e) {
            
        }

        System.out.println("--- Student System Starting ---");
        if (!list.isEmpty()) {
            System.out.println("Loaded " + list.size() + " existing student(s) from previous session.");
        }
        
        while (true) {
            System.out.println("\nOptions: 1.Add 2.Search 3.Update 4.Delete 5.ShowAll 6.Report");
            System.out.println("7.SaveTxt 8.LoadTxt 9.SaveBin 10.LoadBin 11.SaveObj 12.LoadObj");
            System.out.println("13.Backup 14.FileInfo 0.Exit");
            System.out.print("Choice: ");
            
            try {
                int c = Integer.parseInt(sc.nextLine()); 
                if (c == 0) break;
                
                if (c == 1) {
                    System.out.print("Enter ID: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Name: "); String name = sc.nextLine();
                    System.out.print("Enter Dept: "); String dept = sc.nextLine();
                    System.out.print("Enter GPA: "); double gpa = Double.parseDouble(sc.nextLine());
                    list.add(new Student(id, name, dept, gpa));
                    System.out.println("Added!");
                }
                else if (c == 2) {
                    System.out.print("Search ID: "); int id = Integer.parseInt(sc.nextLine());
                    for (Student s : list) {
                        if (s.id == id) System.out.println(s.id + " | " + s.name + " | " + s.dept + " | " + s.gpa);
                    }
                }
                else if (c == 3) {
                    System.out.print("Update ID: "); int id = Integer.parseInt(sc.nextLine());
                    for (Student s : list) {
                        if (s.id == id) {
                            System.out.print("New Name: "); s.name = sc.nextLine();
                            System.out.print("New Dept: "); s.dept = sc.nextLine();
                            System.out.print("New GPA: "); s.gpa = Double.parseDouble(sc.nextLine());
                            System.out.println("Updated!");
                        }
                    }
                }
                else if (c == 4) {
                    System.out.print("Delete ID: "); int id = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).id == id) {
                            list.remove(i);
                            System.out.println("Deleted.");
                            break; 
                        }
                    }
                }
                else if (c == 5) {
                    for (Student s : list) {
                        System.out.println(s.id + " | " + s.name + " | " + s.dept + " | " + s.gpa);
                    }
                }
                else if (c == 6) {
                    if (list.isEmpty()) { System.out.println("Empty"); continue; }
                    double sum = 0, max = 0, min = 10;
                    for (Student s : list) {
                        sum += s.gpa;
                        if (s.gpa > max) max = s.gpa;
                        if (s.gpa < min) min = s.gpa;
                    }
                    System.out.println("Total: " + list.size() + " Avg: " + (sum / list.size()) + " Max: " + max + " Min: " + min);
                }
                else if (c == 7) {
                    PrintWriter pw = new PrintWriter(new FileWriter("data/students.txt"));
                    for (Student s : list) pw.println(s.id + "," + s.name + "," + s.dept + "," + s.gpa);
                    pw.close();
                    System.out.println("Saved text.");
                }
                else if (c == 8) {
                    Scanner fs = new Scanner(new File("data/students.txt"));
                    list.clear();
                    while(fs.hasNextLine()) {
                        String[] p = fs.nextLine().split(",");
                        list.add(new Student(Integer.parseInt(p[0]), p[1], p[2], Double.parseDouble(p[3])));
                    }
                    fs.close();
                    System.out.println("Loaded text.");
                }
                else if (c == 9) {
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream("data/students.dat"));
                    dos.writeInt(list.size());
                    for (Student s : list) { 
                        dos.writeInt(s.id); dos.writeUTF(s.name); dos.writeUTF(s.dept); dos.writeDouble(s.gpa); 
                    }
                    dos.close();
                    System.out.println("Saved binary.");
                }
                else if (c == 10) {
                    DataInputStream dis = new DataInputStream(new FileInputStream("data/students.dat"));
                    list.clear();
                    int count = dis.readInt();
                    for(int i = 0; i < count; i++) {
                        list.add(new Student(dis.readInt(), dis.readUTF(), dis.readUTF(), dis.readDouble()));
                    }
                    dis.close();
                    System.out.println("Loaded binary.");
                }
                else if (c == 11) {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/students.ser"));
                    oos.writeObject(list); oos.close();
                    System.out.println("Saved object.");
                }
                else if (c == 12) {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/students.ser"));
                    list = (ArrayList<Student>) ois.readObject(); ois.close();
                    System.out.println("Loaded object.");
                }
                else if (c == 13) {
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data/students.txt"));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("data/backup/students_backup.txt"));
                    int b; while((b = bis.read()) != -1) bos.write(b);
                    bis.close(); bos.close();
                    System.out.println("Backup created.");
                }
                else if (c == 14) {
                    File f = new File("data/students.txt");
                    if (f.exists()) {
                        System.out.println("File: " + f.getName());
                        System.out.println("Path: " + f.getAbsolutePath());
                        System.out.println("Size: " + f.length() + " bytes");
                        System.out.println("Modified: " + new Date(f.lastModified()));
                    } else {
                        System.out.println("File doesn't exist yet.");
                    }
                }
                else {
                    System.out.println("Bad option");
                }
            } catch(Exception e) {
                System.out.println("Error happened: " + e.getMessage());
            }
        }
    }
}
