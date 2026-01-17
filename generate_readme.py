#!/usr/bin/env python3
"""
Script to automatically generate README.md with current file counts.
This ensures the README is always up-to-date without manual editing.
"""

import os
from pathlib import Path

def count_files_in_directory(directory):
    """Count the number of files in a directory."""
    try:
        return len([f for f in os.listdir(directory) if os.path.isfile(os.path.join(directory, f))])
    except (FileNotFoundError, PermissionError):
        return 0

def generate_readme():
    """Generate README.md with dynamic file counts."""
    
    # Get the repository root directory
    repo_root = Path(__file__).parent
    
    # Count files in each directory
    arrays_count = count_files_in_directory(repo_root / "arrays")
    strings_count = count_files_in_directory(repo_root / "strings")
    algorithms_count = count_files_in_directory(repo_root / "algorithms")
    recursion_count = count_files_in_directory(repo_root / "recursion")
    math_count = count_files_in_directory(repo_root / "math")
    bit_manipulation_count = count_files_in_directory(repo_root / "bit-manipulation")
    basics_count = count_files_in_directory(repo_root / "basics")
    oops_count = count_files_in_directory(repo_root / "oops")
    
    # README content with dynamic counts
    readme_content = f"""# DSA in Java

This repository contains Java implementations of Data Structures and Algorithms that I'm learning as part of my DSA course.

## Repository Structure

The repository is organized into the following directories:

### 📁 **arrays/** ({arrays_count} files)
Array-related problems and solutions including:
- Array operations (rotation, reversal, searching)
- Stock buy/sell problems
- Kadane's algorithm
- Subarray problems
- Two-pointer techniques
- LeetCode problems (Next Permutation, Three Sum, Top K Frequent Elements, Valid Sudoku, etc.)

### 📁 **strings/** ({strings_count} files)
String manipulation problems including:
- Palindrome checks
- Anagram detection
- Longest substring problems
- String validation

### 📁 **algorithms/** ({algorithms_count} files)
Core algorithms including:
- Searching algorithms (Linear Search, Binary Search)
- Sorting algorithms (Bubble Sort, Selection Sort, Insertion Sort, Counting Sort)
- Divide and Conquer (Merge Sort, Quick Sort)

### 📁 **recursion/** ({recursion_count} files)
Recursion and backtracking problems including:
- Factorial, Fibonacci
- Subset generation
- Permutations
- Tower of Hanoi

### 📁 **math/** ({math_count} files)
Mathematical problems including:
- Number conversions (Binary ↔ Decimal)
- Prime numbers
- Power calculations
- Factorial

### 📁 **bit-manipulation/** ({bit_manipulation_count} file{"s" if bit_manipulation_count != 1 else ""})
Bit manipulation techniques and operations

### 📁 **basics/** ({basics_count} files)
Basic programming concepts:
- Pattern printing
- Switch statements
- Basic conditions

### 📁 **oops/** ({oops_count} file{"s" if oops_count != 1 else ""})
Object-Oriented Programming examples

## Purpose

To practice and build a strong foundation in DSA using Java for coding interviews and problem-solving.

## Note

Work in progress — more topics and problems will be added as I learn.

---

*This README is automatically generated. To update, run `python generate_readme.py` or push changes to trigger the GitHub Action.*
"""
    
    # Write to README.md
    readme_path = repo_root / "README.md"
    with open(readme_path, 'w', encoding='utf-8') as f:
        f.write(readme_content)
    
    print(f"✓ README.md generated successfully!")
    print(f"  - arrays: {arrays_count} files")
    print(f"  - strings: {strings_count} files")
    print(f"  - algorithms: {algorithms_count} files")
    print(f"  - recursion: {recursion_count} files")
    print(f"  - math: {math_count} files")
    print(f"  - bit-manipulation: {bit_manipulation_count} file(s)")
    print(f"  - basics: {basics_count} files")
    print(f"  - oops: {oops_count} file(s)")

if __name__ == "__main__":
    generate_readme()
