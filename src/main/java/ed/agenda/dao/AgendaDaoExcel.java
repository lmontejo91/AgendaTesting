package ed.agenda.dao;

import ed.agenda.entidades.Contacto;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import ed.agenda.excepciones.ContactoNoEncontradoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class AgendaDaoExcel implements AgendaDao {

	static String PROJECT_PATH = System.getProperty("user.dir");

	static String SRC_PATH = "/resources/datos.xlsx";

	@Override
	public boolean crearContactoPersona(ContactoPersona contacto) {
		Pattern pat = Pattern.compile("[a-zA-Z]{5,15}");
		Matcher mat = pat.matcher(contacto.getNombre());
		if (!mat.matches()) {
			return false;
		}

		// load the workbook
		InputStream inp;
		try {
			inp = new FileInputStream(PROJECT_PATH + SRC_PATH);

			Workbook wb = WorkbookFactory.create(inp);
			inp.close();

			// make some changes
			Sheet sh = wb.getSheetAt(0);
			Row r = sh.createRow(sh.getPhysicalNumberOfRows());
			Cell c = r.createCell(0);
			c.setCellValue("persona");
			c = r.createCell(1);
			c.setCellValue(contacto.getNombre());
			c = r.createCell(2);
			c.setCellValue(contacto.getTelefono());
			c = r.createCell(3);
			c.setCellValue(contacto.getIdEmpresa());
			c = r.createCell(4);
			c.setCellValue(contacto.getCumpleanos());

			// overwrite the workbook with changed workbook
			FileOutputStream fileOut = new FileOutputStream(PROJECT_PATH + SRC_PATH);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean crearContactoEmpresa(ContactoEmpresa contacto) {
		Pattern pat = Pattern.compile("[a-zA-Z]{3,20}");
		Matcher mat = pat.matcher(contacto.getNombre());
		
		Pattern pat2 = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mat2 = pat.matcher(contacto.getPagweb());
		if (!mat.matches() && !mat2.matches()) {
			return false;
		}
		InputStream inp;
		try {
			inp = new FileInputStream(PROJECT_PATH + SRC_PATH);

			Workbook wb = WorkbookFactory.create(inp);
			inp.close();

			// make some changes
			Sheet sh = wb.getSheetAt(0);
			Row r = sh.createRow(sh.getPhysicalNumberOfRows());
			Cell c = r.createCell(0);
			c.setCellValue("empresa");
			c = r.createCell(1);
			c.setCellValue(contacto.getNombre());
			c = r.createCell(2);
			c.setCellValue(contacto.getTelefono());
			c = r.createCell(3);
			c.setCellValue(contacto.getId());
			c = r.createCell(4);
			c.setCellValue(contacto.getPagweb());

			// overwrite the workbook with changed workbook
			FileOutputStream fileOut = new FileOutputStream(PROJECT_PATH + SRC_PATH);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean borrarContactoPorPosicion(int rowIndex) {
		InputStream inp;
		try {
			inp = new FileInputStream(PROJECT_PATH + SRC_PATH);

			Workbook wb = WorkbookFactory.create(inp);
			inp.close();

			// make some changes
			Sheet sheet = wb.getSheetAt(0);

			int lastRowNum = sheet.getLastRowNum();

			
			int filasSubir = rowIndex -lastRowNum;
			if (rowIndex >= 0 && rowIndex < lastRowNum) {
				
				
				sheet.removeRow(sheet.getRow(rowIndex));
			}

			// overwrite the workbook with changed workbook
			FileOutputStream fileOut = new FileOutputStream(PROJECT_PATH + SRC_PATH);
			wb.write(fileOut);
			fileOut.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		shift();
		return true;
	}

	@Override
	public Contacto obtenerContactoPorPosicion(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contacto obtenerContactoPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	
    public static void shift(){
        int firstColumn;
        int endColumn;
        boolean isRowEmpty = true;
        try{
            FileInputStream is=new FileInputStream(PROJECT_PATH + SRC_PATH);

			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(0);
            //sheet.setDisplayGridlines(false);

            //block to set column bounds
            Iterator<Row> iter = sheet.rowIterator();
            firstColumn = (iter.hasNext() ? Integer.MAX_VALUE : 0);
            endColumn = 0;
            while (iter.hasNext()) {
                Row row = iter.next();
                short firstCell = row.getFirstCellNum();
                if (firstCell >= 0) {
                    firstColumn = Math.min(firstColumn, firstCell);
                    endColumn = Math.max(endColumn, row.getLastCellNum());
                }
            }

            // main logic block
            for (int i = 0; i< sheet.getLastRowNum(); i++) {
                if (sheet.getRow(i) != null) {
                    isRowEmpty = true;
                    Row row = sheet.getRow(i);
                    for (int j = firstColumn; j < endColumn; j++) {
                        if (j >= row.getFirstCellNum() && j < row.getLastCellNum()) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                if (!cell.getStringCellValue().equals("")) {
                                    isRowEmpty = false;
                                    break;
                                }
                            }
                        }
                    }
                    //if empty
                    if (isRowEmpty) {
                        System.out.println("Found empty row on: " + row.getRowNum());
                        sheet.shiftRows(row.getRowNum() + 1, sheet.getLastRowNum(), -1);
                        i--;
                    }
                }
                // if row is null
                else{
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                    i--;
                }
            }
            //Writing output to the same file.
            FileOutputStream fileOut = new FileOutputStream(PROJECT_PATH + SRC_PATH);
            wb.write(fileOut);
            fileOut.close();
            System.out.println("Successfully wrote the content in the file");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void listarContactos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarContactoPorNombre(String nombre) throws ContactoNoEncontradoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ContactoPersona> mostrarTrabajadoresEmpresa(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void anadirTrabajadorAEmpresa(ContactoEmpresa ce, ContactoPersona cp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
