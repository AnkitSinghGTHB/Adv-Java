import os
import re
import subprocess

solutions_dir = r"c:\Users\ankit\Downloads\Adv Java\Question_Bank_Solutions"
md_files = [f for f in os.listdir(solutions_dir) if f.endswith('.md')]

all_good = True
for md_file in md_files:
    file_path = os.path.join(solutions_dir, md_file)
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Extract Java code blocks
    java_blocks = re.findall(r'```java(.*?)```', content, re.DOTALL)
    if not java_blocks:
        continue
        
    for idx, block in enumerate(java_blocks):
        # find public class name
        match = re.search(r'public\s+class\s+(\w+)', block)
        if match:
            class_name = match.group(1)
        else:
            print(f"No public class found in a block in {md_file}, skipping compilation.")
            continue
                
        java_file_path = os.path.join(solutions_dir, f"{class_name}.java")
        with open(java_file_path, 'w', encoding='utf-8') as f:
            f.write(block.strip()) # strip to remove leading newlines
            
        # compile
        result = subprocess.run(["javac", java_file_path], capture_output=True, text=True)
        if result.returncode != 0:
            print(f"Error compiling {class_name} from {md_file}:")
            print(result.stderr)
            all_good = False
        
        # cleanup
        try:
            os.remove(java_file_path)
            class_file_path = os.path.join(solutions_dir, f"{class_name}.class")
            if os.path.exists(class_file_path):
                os.remove(class_file_path)
            # Need to also clean up other class files generated
            for f_name in os.listdir(solutions_dir):
                if f_name.endswith(".class"):
                    os.remove(os.path.join(solutions_dir, f_name))
        except Exception as e:
            pass
        
if all_good:
    print("All Java code blocks compiled successfully!")
