import sys

def extract_pdf(pdf_path, txt_path):
    try:
        import fitz # PyMuPDF
        doc = fitz.open(pdf_path)
        with open(txt_path, 'w', encoding='utf-8') as f:
            for page in doc:
                f.write(page.get_text())
        print(f"Extracted {pdf_path} using PyMuPDF")
        return True
    except ImportError:
        pass
    except Exception as e:
        print(f"PyMuPDF failed on {pdf_path}: {e}")

    try:
        import PyPDF2
        with open(pdf_path, 'rb') as file:
            reader = PyPDF2.PdfReader(file)
            with open(txt_path, 'w', encoding='utf-8') as f:
                for page in reader.pages:
                    text = page.extract_text()
                    if text:
                        f.write(text + "\n")
        print(f"Extracted {pdf_path} using PyPDF2")
        return True
    except ImportError:
        print("Neither PyMuPDF nor PyPDF2 is installed.")
    except Exception as e:
        print(f"PyPDF2 failed on {pdf_path}: {e}")
        
    return False

if __name__ == '__main__':
    pdfs = ['Adv Java M1.pdf', 'Adv Java M2.pdf', 'Adv Java M3.pdf']
    for pdf in pdfs:
        extract_pdf(pdf, pdf.replace('.pdf', '.txt'))
