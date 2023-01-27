package com.springframework.backendassignment.helpers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.springframework.backendassignment.model.Product;
import com.springframework.backendassignment.model.Supplier;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.cglib.core.Local;
import org.springframework.web.multipart.MultipartFile;
import com.springframework.backendassignment.model.InventoryData;


public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    static Map<String, Product> productMap = new HashMap<>();
    static Map<String, Supplier> supplierMap = new HashMap<>();

    public static List[] csvToInventories(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<InventoryData> inventoryDataList = new ArrayList<InventoryData>();
            List<Product> productsList = new ArrayList<>();
            List<Supplier> suppliersList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Product product;
//                product.setCode(csvRecord.get(0));
                String code = csvRecord.get(0);
                if (productMap.containsKey(code)) {
                    product = productMap.get(code);
                } else {
                    product = new Product(
                            csvRecord.get(0),
                            csvRecord.get("name"),
                            csvRecord.get("company")
                    );
                    productMap.put(product.getCode(), product);
                }


                Supplier supplier;
                String supplierName = csvRecord.get("supplier");
                if (supplierMap.containsKey(supplierName)) {
                    supplier = supplierMap.get(supplierName);
                } else {
                    supplier = new Supplier(csvRecord.get("supplier"));
                    supplierMap.put(supplier.getSupplierName(), supplier);
                }


                InventoryData inventoryData = new InventoryData(
                        product,
                        csvRecord.get("batch"),
                        Integer.parseInt(csvRecord.get("stock")),
                        Integer.parseInt(csvRecord.get("deal")),
                        Integer.parseInt(csvRecord.get("free")),
                        Float.parseFloat(csvRecord.get("mrp")),
                        Float.parseFloat(csvRecord.get("rate")),
                        supplier
                );
                if (csvRecord.get("exp").matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
                    inventoryData.setExpiry(new SimpleDateFormat("dd-MM-yyyy").parse(csvRecord.get("exp")));
                }
                supplier.getInventoryData().add(inventoryData);
                inventoryData.setSupplier(supplier);

                productsList.add(product);
                suppliersList.add(supplier);
                inventoryDataList.add(inventoryData);
            }

            return new List[]{productsList, suppliersList, inventoryDataList};


        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
