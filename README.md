# UGProgram Date Age Converter

### Mini Coding Project - Java

**Institution:** UG Program, UCE, OU
**Effort Level:** Beginner to Intermediate
**Estimated Time:** 15 hours
**Submission Date:** 27-Oct-2024

---

## 📘 Project Overview

This Java command-line program accepts either a **Date of Birth (DOB)** or an **Age** and a **reference date**, then calculates the corresponding **Age** or **DOB**.

It demonstrates basic date manipulation, input validation, and formatted output using Java's `LocalDate` and `Period` classes.

---

## ⚙️ Features

* Accepts **either DOB or AGE** as input.
* Calculates **Age** as of a specific date or today.
* Calculates **DOB** if Age is provided.
* Supports multiple **date formats** (Indian, International, USA styles).
* Handles delimiters: `-`, `/`, or `.`
* Detects and rejects invalid dates (e.g., `29-02-2023`).

---

## 🧩 Input Format (Command-Line Arguments)

The program takes **4 arguments** in order:

| Parameter | Description       | Example                            |
| --------- | ----------------- | ---------------------------------- |
| 1         | DOB or AGE input  | `DOB=28-02-2001` or `AGE=19-10-27` |
| 2         | Reference date    | `27-02-2024`                       |
| 3         | Date format token | `DD-MM-YYYY` or `YYYY/MM/DD`       |
| 4         | Delimiter         | `-`, `/`, or `.`                   |

---

## 🧠 Examples

### Example 1 – Calculate Age from DOB

```bash
javac UGProgram_DateAgeConverter.java
java UGProgram_DateAgeConverter "DOB=28-02-2001" "27-02-2024" "DD-MM-YYYY" "-"
```

**Output:**

```
Age is 23 years, 0 months, 0 days
```

### Example 2 – Calculate DOB from Age

```bash
java UGProgram_DateAgeConverter "AGE=19-10-27" "27-10-2024" "DD-MM-YYYY" "-"
```

**Output:**

```
DOB is 30-11-2004
```

---

## 🚫 Error Handling

The program validates inputs and prints descriptive error messages for:

* Incorrect number of arguments
* Invalid or mismatched date formats
* Invalid calendar dates (e.g., 30-13-2022)
* Negative age components
* DOB later than reference date

---

## 🧮 Supported Date Formats

* `DD<dlc>MM<dlc>YYYY`  → Indian style (e.g., 27-10-2024)
* `YYYY<dlc>MM<dlc>DD`  → International style (e.g., 2024-10-27)
* `MM<dlc>DD<dlc>YYYY`  → US style (e.g., 10-27-2024)

Where `<dlc>` = Delimiter (`-`, `/`, `.`)

---

## 🗂️ Project Structure

```
UGProgram_DateAgeConverter.java
README.md
```

---

## 💡 How to Run

1. Open a terminal in the project folder.
2. Compile the Java file:

   ```bash
   javac UGProgram_DateAgeConverter.java
   ```
3. Run with the appropriate arguments:

   ```bash
   java UGProgram_DateAgeConverter "DOB=28-02-2001" "27-02-2024" "DD-MM-YYYY" "-"
   ```

---

## 👨‍💻 Author Notes

* If any required data or assumptions are missing, document them in the submission notes.
* Ensure input and output formats strictly follow the given structure for consistency.

---

## 📄 License

This project is for **educational purposes** under the UG Program (UCE, OU). Free for academic use.
