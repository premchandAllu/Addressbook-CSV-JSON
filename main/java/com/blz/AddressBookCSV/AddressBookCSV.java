package com.blz.AddressBookCSV;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBookCSV {
	private static final String ADDRESSBOOK_CSV_FILE = "C:\\Users\\premc\\eclipse-workspace\\AddressBook_CSV_JSON\\src\\AddressbookCSV.csv";

	public static void main(String[] args) throws Exception {
		AddressBookCSV addressBookCSV = new AddressBookCSV();
		try {
			addressBookCSV.writeCSVData();
			addressBookCSV.readCSVData();
		} catch (IOException e) {
			System.out.println("Exception is - " + e);
		}
	}

	public void writeCSVData() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (Writer writer = Files.newBufferedWriter(Paths.get(ADDRESSBOOK_CSV_FILE));) {
			StatefulBeanToCsvBuilder<Contact> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<Contact> beanWriter = builder.build();
			List<Contact> personsList = new ArrayList<>();
			personsList.add(new Contact("Premchand", "Allu", "Tuni", "East Godavari", "AP",533401,"7660078866",
					"prem@gmail.com"));
			personsList.add(new Contact("Bhanuchand", "Allu", "Madhura Nagar", "Hyderabad", "Telangana",500009, "9494782626","bhanu@gmail.com"));
			beanWriter.write(personsList);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readCSVData() throws Exception {
		try (Reader reader = Files.newBufferedReader(Paths.get(ADDRESSBOOK_CSV_FILE));
				CSVReader csvReader = new CSVReader(reader);) {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("First Name - " + nextRecord[0]);
				System.out.println("Last NAme - " + nextRecord[1]);
				System.out.println("Address - " + nextRecord[2]);
				System.out.println("City - " + nextRecord[3]);
				System.out.println("State - " + nextRecord[4]);
				System.out.println("Zip - " + nextRecord[5]);
				System.out.println("Phone - " + nextRecord[6]);
				System.out.println("email - " + nextRecord[7]);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
