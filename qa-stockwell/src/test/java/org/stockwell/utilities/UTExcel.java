package org.stockwell.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UTExcel {

    public String getCellData(String filePath, String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(colNum);
            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                default:
                    return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<String> getRowData(String filePath, String sheetName, int rowNum) {
        List<String> rowData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return rowData;
            }

            for (Cell cell : row) {
                rowData.add(getCellValue(cell));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowData;
    }

    public List<String> getColumnData(String filePath, String sheetName, int colNum) {
        List<String> columnData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            for (Row row : sheet) {
                Cell cell = row.getCell(colNum);
                if (cell != null) {
                    columnData.add(getCellValue(cell));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

    public List<String> getColumnDataByHeader(String filePath, String sheetName, String headerName) {
        List<String> columnData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            Row headerRow = sheet.getRow(0); // Assuming the first row is the header row
            if (headerRow == null) {
                throw new IllegalArgumentException("Header row is missing.");
            }

            int colIndex = -1;
            for (Cell cell : headerRow) {
                if (headerName.equalsIgnoreCase(cell.getStringCellValue())) {
                    colIndex = cell.getColumnIndex();
                    break;
                }
            }

            if (colIndex == -1) {
                throw new IllegalArgumentException("Header '" + headerName + "' not found.");
            }

            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) { // Skip header
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    Cell cell = row.getCell(colIndex);
                    columnData.add(getCellValue(cell));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return columnData;
    }

    private String getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                default:
                    return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getRowCount(String filePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            int rowCount = 0;
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    rowCount++;
                }
            }
            return rowCount;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getColumnCount(String filePath, String sheetName, int rowNum) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                return -1;
            }
            return row.getLastCellNum();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int[] findCellByValue(String filePath, String sheetName, String value) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    for (int colNum = 0; colNum < row.getLastCellNum(); colNum++) {
                        Cell cell = row.getCell(colNum);
                        if (cell != null) {
                            if (getCellValue(cell).equals(value)) {
                                return new int[] { rowNum, colNum };
                            }
                        }
                    }
                }
            }
            return new int[] { -1, -1 };
        } catch (IOException e) {
            e.printStackTrace();
            return new int[] { -1, -1 };
        }
    }

    public void setCellData(String filePath, String sheetName, int rowNum, int colNum, String value) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with the name '" + sheetName + "' not found.");
            }

            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            Cell cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);

            // Save the changes
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getSheetNames(String filePath) {
        List<String> sheetNames = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                sheetNames.add(workbook.getSheetName(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheetNames;
    }
}
