import PyPDF2
import os
import glob

def extract_text(pdf_path, txt_path):
    print(f"Extracting {pdf_path} to {txt_path}")
    try:
        with open(pdf_path, 'rb') as file:
            reader = PyPDF2.PdfReader(file)
            text = ""
            for page in reader.pages:
                page_text = page.extract_text()
                if page_text:
                    text += page_text + "\n\n"
        with open(txt_path, 'w', encoding='utf-8') as file:
            file.write(text)
    except Exception as e:
        print(f"Error extracting {pdf_path}: {e}")

if __name__ == '__main__':
    pdf_files = glob.glob("*.pdf")
    os.makedirs("extracted_texts", exist_ok=True)
    for pdf in pdf_files:
        txt_name = os.path.join("extracted_texts", os.path.basename(pdf).replace('.pdf', '.txt'))
        extract_text(pdf, txt_name)
