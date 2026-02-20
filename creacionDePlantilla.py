import shutil
from docx import Document
import os
from docx.shared import Pt
from docx.enum.text import WD_ALIGN_PARAGRAPH
from datetime import datetime

def reemplazar_nombre_y_fecha_en_tabla(plantilla_path, nombre, salida_path):
    try:
        # Cargar la plantilla
        doc = Document(plantilla_path)
        
        # Obtener la fecha de hoy en formato "dd/mm/yyyy"
        fecha_hoy = datetime.now().strftime("%d/%m/%Y")
        
        # Asumiendo que la tabla en la que quieres insertar los nombres es la primera en el documento
        tabla = doc.tables[0]
        
        # Insertar el nombre en la celda correspondiente (fila 2, columna 1)
        celda_proceso_a_validar = tabla.cell(1, 1)  # Ajuste: segunda fila (index 1), primera columna (index 0)
        celda_proceso_a_validar.text = nombre
        
        # Insertar la fecha de hoy en la celda correspondiente (fila 2, columna 3)
        celda_fecha_de_prueba = tabla.cell(4, 4)  # Ajuste: segunda fila (index 1), tercera columna (index 2)
        celda_fecha_de_prueba.text = fecha_hoy

        # Centrar el texto en la celda de fecha
        for parrafo in celda_fecha_de_prueba.paragraphs:
            parrafo.alignment = WD_ALIGN_PARAGRAPH.CENTER

        # Guardar el documento modificado con el nombre del archivo de salida
        doc.save(salida_path)
        print(f'Documento guardado: {salida_path}')
    
    except Exception as e:
        print(f'Error al procesar {nombre}: {e}')

def procesar_nombres(archivo_nombres, plantilla_path, salida_dir, feature_file):
    try:
        # Leer nombres desde el archivo de texto
        with open(archivo_nombres, 'r') as file:
            nombres = file.read().splitlines()
        
        # Crear el directorio de salida si no existe
        if not os.path.exists(salida_dir):
            os.makedirs(salida_dir)

        # Procesar cada nombre y generar un documento
        for nombre in nombres:
            salida_path = os.path.join(salida_dir, f'{nombre}.docx')
            reemplazar_nombre_y_fecha_en_tabla(plantilla_path, nombre, salida_path)

        # Copiar el archivo .feature al directorio de salida
        if os.path.exists(feature_file):
            shutil.copy(feature_file, salida_dir)
            print(f'Archivo {feature_file} copiado a {salida_dir}')
        else:
            print(f'Archivo .feature no encontrado en {feature_file}')
    
    except Exception as e:
        print(f'Error al leer el archivo de nombres o crear el directorio: {e}')

if __name__ == "__main__":
    archivo_nombres = 'CreacionDePlantillas/nombresTC.txt'  # Archivo de texto con nombres
    plantilla_path = 'CreacionDePlantillas/plantillaDePruebas.docx'  # Plantilla de Word
    feature_file = 'CreacionDePlantillas/TestcCases.feature'  # Archivo .feature
    salida_dir = '/Users/JORGITO/Documents/Testing - JTORR70/Proyecto Actual/TTO/VINCULACIONES/TTO-2858'  # Directorio para guardar los documentos
    procesar_nombres(archivo_nombres, plantilla_path, salida_dir, feature_file)

