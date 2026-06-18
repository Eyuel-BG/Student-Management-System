
---

## Student Management System

A simple Java console app to manage student records.  
You can **add**, **search**, **update**, **delete**, and **view** students.  
It also saves/loads data in different file formats.

---

## How to Run

1. **Compile** both files:
   ```bash
   javac Main.java Student.java

2. **Run** the application:
   ```bash
   java Main


## Menu Options

| Option | Description |
| --- | --- |
| **1. Add** | Add a new student (ID, Name, Dept, GPA) |
| **2. Search** | Find a student by ID |
| **3. Update** | Change a student's details |
| **4. Delete** | Remove a student by ID |
| **5. ShowAll** | List all students |
| **6. Report** | Show total, average, max, min GPA |
| **7. SaveTxt** | Save as text file (`students.txt`) |
| **8. LoadTxt** | Load from text file |
| **9. SaveBin** | Save as binary file (`students.dat`) |
| **10. LoadBin** | Load from binary file |
| **11. SaveObj** | Save as serialized object (`students.ser`) |
| **12. LoadObj** | Load from serialized object |
| **13. Backup** | Copy `students.txt` to `data/backup/` |
| **14. FileInfo** | Show file details (size, path, modified date) |
| **0. Exit** | Quit the program |

---

## Project Files

```text
.
├── Main.java          # Main program (menu + logic)
├── Student.java       # Student class (ID, name, dept, GPA)
└── data/              # Created automatically
    ├── students.txt   # Text file (CSV format)
    ├── students.dat   # Binary file
    └── students.ser   # Serialized object file

```

---

## Example

```text
Options: 1.Add 2.Search 3.Update 4.Delete 5.ShowAll 6.Report ...
Choice: 1
Enter ID: 101
Enter Name: Dinknesh
Enter Dept: CS
Enter GPA: 3.8
Added!

```

