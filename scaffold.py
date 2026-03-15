import os
import re

input_file = r"c:\Users\ankit\Downloads\Adv Java\Question Bank Advance java.txt"
output_dir = r"c:\Users\ankit\Downloads\Adv Java\Question_Bank_Solutions"

# Create output directory
os.makedirs(output_dir, exist_ok=True)

with open(input_file, 'r', encoding='utf-8') as f:
    lines = f.readlines()

questions = {}
current_q = None
current_text = []

# Regex to match start of a question (e.g., "1.", "2 .", "12 ", etc.)
q_start_re = re.compile(r'^(\d+)\s*\.?\u200b?\s*(.*)')

# Parse the text file to extract questions
for line in lines:
    line_clean = line.strip()
    if not line_clean:
        continue
    
    # Skip the "Question Bank Advance java" header line
    if "Question Bank Advance java" in line_clean:
        continue

    match = q_start_re.match(line_clean)
    if match:
        q_num = int(match.group(1))
        # Valid question numbers are between 1 and 36 based on the PDF
        if 1 <= q_num <= 36:
            if current_q is not None:
                questions[current_q] = '\n'.join(current_text).strip()
            current_q = q_num
            current_text = [match.group(2)]
        else:
            if current_q is not None:
                current_text.append(line_clean)
    elif current_q is not None:
        current_text.append(line_clean)

# Don't forget the last question
if current_q is not None:
    questions[current_q] = '\n'.join(current_text).strip()

total_q = 36

# Scaffold the individual Markdown files
for q_num in range(1, total_q + 1):
    q_text = questions.get(q_num, f"Problem text for Question {q_num} could not be extracted perfectly. Please refer to the PDF.")
    
    # Generate navigation links
    prev_link = f"[< Previous Question](Q{q_num-1}.md)" if q_num > 1 else "[< Previous Question]()"
    next_link = f"[Next Question >](Q{q_num+1}.md)" if q_num < total_q else "[Next Question >]()"
    nav_bar = f"{prev_link} | [Home / README](../README.md) | {next_link}"
    
    md_content = f"""{nav_bar}

# Question {q_num}

**{q_text}**

---

### Solution

// TODO: Write solution here.

---
{nav_bar}
"""
    
    with open(os.path.join(output_dir, f"Q{q_num}.md"), 'w', encoding='utf-8') as f:
        f.write(md_content)

print(f"Scaffolding complete. Generated {total_q} Markdown files in {output_dir}.")

# Generate README.md
readme_content = "# Advanced Java - Question Bank Solutions\n\n"
readme_content += "This repository contains the solutions to the Advanced Java Question Bank. Click on any question to view its solution.\n\n"

for q_num in range(1, total_q + 1):
    if q_num in questions:
        # Create a short summary of the question text
        summary_raw = questions[q_num].replace('\n', ' ')
        summary = (summary_raw[:80] + "...") if len(summary_raw) > 80 else summary_raw
        readme_content += f"- **[Question {q_num}](Question_Bank_Solutions/Q{q_num}.md)**: {summary}\n"
    else:
        readme_content += f"- **[Question {q_num}](Question_Bank_Solutions/Q{q_num}.md)**: Solution placeholder\n"

with open(r"c:\Users\ankit\Downloads\Adv Java\README.md", 'w', encoding='utf-8') as f:
    f.write(readme_content)

print("Generated README.md at the root directory.")
