# 🚀 AlgoLib – Data Structures & Algorithms in Java

[![Build Status](https://github.com/itspritthakkar/TheAlgorithms-Java/actions/workflows/maven.yml/badge.svg)](https://github.com/itspritthakkar/TheAlgorithms-Java/actions/workflows/maven.yml) [![Qodana](https://github.com/itspritthakkar/TheAlgorithms-Java/actions/workflows/qodana_code_quality.yml/badge.svg)](https://github.com/itspritthakkar/TheAlgorithms-Java/actions/workflows/qodana_code_quality.yml) [![codecov](https://codecov.io/gh/itspritthakkar/TheAlgorithms-Java/branch/main/graph/badge.svg)](https://codecov.io/gh/itspritthakkar/TheAlgorithms-Java) [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

---

## 📖 About
AlgoLib is a **Java library for practicing Data Structures and Algorithms (DSA)**.  
It includes clean implementations, demos, and **JUnit 5 test cases** for:

- ✅ Singly Linked List
- ✅ Doubly Linked List
- ✅ Stacks (Array, ArrayList, LinkedList)
- ✅ Sorting Algorithms (Bubble, Selection, Insertion, Merge, Quick)
- ✅ Fibonacci (recursive, formula, matrix exponentiation)
- ✅ Binary Exponentiation of permutations
- ...and more 🚀

All demos have been converted into **automated tests** for reproducibility.

---

## 🛠️ Tech Stack
- **Java 21**
- **Maven** (build & dependency management)
- **JUnit 5** + **AssertJ** (testing)
- **GitHub Actions** (CI/CD)
- **Qodana** (static code analysis)
- **JaCoCo + Codecov** (test coverage reporting)

---

## 📦 Getting Started

### 1️⃣ Clone the repo
```bash
git clone https://github.com/itspritthakkar/TheAlgorithms-Java.git
cd TheAlgorithms-Java
```

### 2️⃣ Build & Run Tests
Run all tests:
```bash
mvn test
```

Run tests with coverage (generates `target/site/jacoco` report):
```bash
mvn verify
```

---

## ✅ Running Tests in GitHub

This project uses **GitHub Actions**:

- **Maven Workflow** → runs `mvn verify` on every push/PR.
- **Qodana Workflow** → runs JetBrains static analysis for code quality.

You can see the results in the **Actions tab** of this repo.

---

## 📊 Code Coverage
Code coverage is powered by **JaCoCo** + **Codecov**.

To generate locally:
```bash
mvn clean verify
```
Then open:
```
target/site/jacoco/index.html
```

On GitHub, Codecov tracks and shows a badge:  
![codecov](https://codecov.io/gh/itspritthakkar/TheAlgorithms-Java/branch/main/graph/badge.svg)

---

## 📂 Project Structure
```
src/
 ├── main/java/com/algolib/...     # Core implementations
 └── test/java/com/algolib/...     # JUnit test cases (demos converted into tests)
```

---

## 🧪 Example Test
```java
@Test
void deleteAtIndexFromMiddle() {
    SinglyLinkedList<Integer> sl = new SinglyLinkedList<>(new Integer[]{1, 2, 3, 4, 5});
    int deleted = sl.deleteAtIndex(2);
    assertThat(deleted).isEqualTo(3);
    assertThat(sl.toList()).containsExactly(1, 2, 4, 5);
}
```

---

## 📈 Roadmap
- [ ] Add Graph algorithms
- [ ] Add Dynamic Programming demos
- [ ] Add LeetCode-style challenges
- [ ] Integrate advanced benchmarking

---

## 📜 License
This project is licensed under the [MIT License](LICENSE).

---

✨ **Happy Coding!** ✨  
*"DSA mastery through clean code and tests."*  
